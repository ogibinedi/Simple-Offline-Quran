package com.obe.quranid2.module.note.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.obe.quranid2.R;
import com.obe.quranid2.module.note.model.Task;
import com.obe.quranid2.module.note.util.Utils;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private final List<Task> taskList;
    private final OnTodoClickListener todoClickListener;

    public NoteAdapter(List<Task> taskList, OnTodoClickListener onTodoClickListener) {
        this.taskList = taskList;
        this.todoClickListener = onTodoClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_row, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.setDataTask(taskList.get(position));

        ColorStateList colorStateList = new ColorStateList(new int[][]{
               new int[]{-android.R.attr.state_enabled},
                new int[]{android.R.attr.state_enabled}
        }, new int[]{
                Color.BLACK, // State is disabled
                Utils.priorityColor(task)
        });
        holder.todayChip.setTextColor(Utils.priorityColor(task));
        holder.todayChip.setChipIconTint(colorStateList);
        holder.llHeaderTask.setBackgroundColor(Utils.priorityColor(task));
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CardView cvToDo;
        public LinearLayout llHeaderTask;
        public ImageButton acbRemoveButton;
        public AppCompatTextView task;
        public Chip todayChip;

        OnTodoClickListener onTodoClickListener;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            cvToDo = itemView.findViewById(R.id.cardViewToDo);
            llHeaderTask = itemView.findViewById(R.id.layoutHeader);
            acbRemoveButton = itemView.findViewById(R.id.toto_image_button);
            task = itemView.findViewById(R.id.todo_row_todo);
            todayChip = itemView.findViewById(R.id.todo_row_chip);

            this.onTodoClickListener = todoClickListener;
            itemView.setOnClickListener(this);
            acbRemoveButton.setOnClickListener(this);
        }

        public void setDataTask(Task dataTask){
            String formatted = Utils.formatDate(dataTask.getDueDate());
            task.setText(dataTask.getTask());
            todayChip.setText(formatted);
        }

        @Override
        public void onClick(View view) {
            Task currTask = taskList.get(getAdapterPosition());
            int id = view.getId();
            if (id == R.id.cardViewToDo){
                onTodoClickListener.onTodoClick(currTask);
            }
            if (id == R.id.toto_image_button){
                onTodoClickListener.onTodoRadioButtonClick(currTask);
            }
        }
    }
}
