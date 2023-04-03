package com.obe.quranid2.module.note.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.obe.quranid2.module.note.model.Task;
import com.obe.quranid2.module.note.util.TaskRoomDatabase;

import java.util.List;

public class TodoRepository {
    private final TaskDao taskDao;
    private final LiveData<List<Task>> allTasks;

    public TodoRepository(Application application) {
        TaskRoomDatabase database = TaskRoomDatabase.getDatabase(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getTasks();
    }

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }

    public void insert(Task task){
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> taskDao.insertTask(task));
    }

    public LiveData<Task> get(long id){ return taskDao.get(id);}

    public void update(Task task){
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> taskDao.update(task));
    }

    public void delete(Task task){
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> taskDao.delete(task));
    }
}
