package com.obe.quranid2.module.note.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.obe.quranid2.R;
import com.obe.quranid2.module.note.adapter.NoteAdapter;
import com.obe.quranid2.module.note.adapter.OnTodoClickListener;
import com.obe.quranid2.module.note.fragments.BottomSheetFragment;
import com.obe.quranid2.module.note.model.SharedViewModel;
import com.obe.quranid2.module.note.model.Task;
import com.obe.quranid2.module.note.model.TaskViewModel;

public class NoteActivity extends AppCompatActivity implements OnTodoClickListener {
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private TextView tvEmptyTask;
    private ImageView ivEmptyTask;
    BottomSheetFragment bottomSheetFragment;
    private SharedViewModel sharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle("Catatan Harian");

        bottomSheetFragment = new BottomSheetFragment();
        ConstraintLayout constraintLayout = findViewById(R.id.bottomSheet);
        BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior = BottomSheetBehavior.from(constraintLayout);
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.STATE_HIDDEN);

        initRv();
        loadData();
        initFab();
    }

    private void loadData() {
        TaskViewModel taskViewModel = new ViewModelProvider.AndroidViewModelFactory(
                NoteActivity.this.getApplication())
                .create(TaskViewModel.class);

        sharedViewModel = new ViewModelProvider(this)
                .get(SharedViewModel.class);

        taskViewModel.getAllTasks().observe(this, tasks -> {
            adapter = new NoteAdapter(tasks, this);
            if (adapter.getItemCount() == 0){
                ivEmptyTask.setVisibility(View.VISIBLE);
                tvEmptyTask.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }else {
                recyclerView.setVisibility(View.VISIBLE);
                ivEmptyTask.setVisibility(View.GONE);
                tvEmptyTask.setVisibility(View.GONE);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void initRv() {
        ivEmptyTask = findViewById(R.id.iv_empty);
        tvEmptyTask = findViewById(R.id.tv_empty_task);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initFab() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> showBottomSheetDialog());
    }

    private void showBottomSheetDialog() {
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSwipeLeft(this);
    }

    @Override
    public void onTodoClick(Task task) {
        sharedViewModel.selectItem(task);
        sharedViewModel.setIsEdit(true);
        showBottomSheetDialog();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onTodoRadioButtonClick(Task task) {
        Log.d("Click", "onTodoClick: "+ task.getTask());
        final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Hapus, "+task.getTask()+"?", Snackbar.LENGTH_LONG);
        snackbar.setAction("Ya", view -> {
            TaskViewModel.delete(task);
            Toast.makeText(this, "Catatan: "+task.getTask()+", berhasil dihapus", Toast.LENGTH_SHORT).show();
        });
        snackbar.show();
        adapter.notifyDataSetChanged();
    }
}