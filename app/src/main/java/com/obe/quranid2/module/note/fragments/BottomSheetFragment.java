package com.obe.quranid2.module.note.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.obe.quranid2.R;
import com.obe.quranid2.module.note.model.Priority;
import com.obe.quranid2.module.note.model.SharedViewModel;
import com.obe.quranid2.module.note.model.Task;
import com.obe.quranid2.module.note.model.TaskViewModel;
import com.obe.quranid2.module.note.util.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class BottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    private TextInputEditText enterTodo;
    private ImageButton saveButton;
    private ImageButton calendarButton;
    private ImageButton priorityButton;
    private RadioGroup priorityRadioGroup;
    private RadioButton selectedRadioButton;
    private int selectedButtonId;
    private CalendarView calendarView;
    private Group calendarGroup;
    private Date dueDate;
    Calendar calendar = Calendar.getInstance();
    private SharedViewModel sharedViewModel;
    private boolean isEdit;
    private Priority priority;

    public BottomSheetFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        initWidget(view);
        return view;
    }

    private void initWidget(View view) {
        enterTodo = view.findViewById(R.id.tie_enter_todo);
        calendarButton = view.findViewById(R.id.today_calendar_button);
        priorityButton = view.findViewById(R.id.priority_todo_button);
        priorityRadioGroup = view.findViewById(R.id.radioGroup_priority);

        saveButton = view.findViewById(R.id.save_todo_button);
        calendarView = view.findViewById(R.id.calendar_view);
        calendarGroup = view.findViewById(R.id.calendar_group);

        Chip todayChip = view.findViewById(R.id.today_chip);
        todayChip.setOnClickListener(this);
        Chip tomorrowChip = view.findViewById(R.id.tomorrow_chip);
        tomorrowChip.setOnClickListener(this);
        Chip nextWeekChip = view.findViewById(R.id.next_week_chip);
        nextWeekChip.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (sharedViewModel.getSelectedItem().getValue() != null){
            isEdit = sharedViewModel.getIsEdit();
            Task task = sharedViewModel.getSelectedItem().getValue();
            enterTodo.setText(task.getTask());
            Log.d("MY", "onViewCreated: " + task.getTask());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity())
                .get(SharedViewModel.class);

        saveButton.setOnClickListener(view1 -> {
            String task = Objects.requireNonNull(enterTodo.getText()).toString().trim();
            if (!TextUtils.isEmpty(task) && dueDate != null && priority != null){
                Task myTask = new Task(task, priority, dueDate, Calendar.getInstance().getTime(), false);
                // For updating item we have to check the condition
                if (isEdit){
                    Task updateTask = sharedViewModel.getSelectedItem().getValue();
                    assert updateTask != null;
                    updateTask.setTask(task);
                    updateTask.setDateCreated(Calendar.getInstance().getTime());
                    updateTask.setPriority(priority);
                    updateTask.setDueDate(dueDate);
                    TaskViewModel.update(updateTask);
                    Toast.makeText(getContext(), "Task berhasil di update!", Toast.LENGTH_SHORT).show();
                    sharedViewModel.setIsEdit(false);
                }else {
                    TaskViewModel.insert(myTask);
                    Toast.makeText(getContext(), "Task berhasil di tambah!", Toast.LENGTH_SHORT).show();
                }
                enterTodo.setText("");
                if (this.isVisible()){
                    this.dismiss();
                }
            }else {
                Snackbar.make(saveButton, R.string.empty_field, Snackbar.LENGTH_LONG).show();
            }
        });

        calendarButton.setOnClickListener(view1 -> {
            calendarGroup.setVisibility(
                    calendarGroup.getVisibility() == View.GONE ? View.VISIBLE : View.GONE
            );
            Utils.hideSoftKeyboard(view1);
        });

        calendarView.setOnDateChangeListener((calendarView, year, month, dayOfMonth) -> {
            calendar.clear();
            calendar.set(year, month, dayOfMonth);
            dueDate = calendar.getTime();
        });

        priorityButton.setOnClickListener(view1 -> {

            Utils.hideSoftKeyboard(view1);
            priorityRadioGroup.setVisibility(
                    priorityRadioGroup.getVisibility() == View.GONE ? View.VISIBLE : View.GONE
            );

            priorityRadioGroup.setOnCheckedChangeListener((radioGroup, checkId) -> {
                if (priorityRadioGroup.getVisibility() == View.VISIBLE){
                    selectedButtonId = checkId;

                    selectedRadioButton = view.findViewById(selectedButtonId);

                    if (selectedRadioButton.getId() == R.id.radioButton_high){
                        priority = Priority.HIGH;
                    }else if (selectedRadioButton.getId() == R.id.radioButton_med){
                        priority = Priority.MEDIUM;
                    }else if (selectedRadioButton.getId() == R.id.radioButton_low){
                        priority = Priority.LOW;
                    }else {
                        priority = Priority.LOW;
                    }
                }else {
                    priority = Priority.LOW;
                }
            });
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.today_chip:
                // set data for today
                calendar.add(Calendar.DAY_OF_YEAR, 0);
                dueDate = calendar.getTime();
                Log.d("TIME", "onClick: "+ dueDate.toString());
                break;
            case R.id.tomorrow_chip:
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                dueDate = calendar.getTime();
                Log.d("TIME", "onClick: "+ dueDate.toString());
                // set data for tomorrow
                break;
            case R.id.next_week_chip:
                // set data for next week
                calendar.add(Calendar.DAY_OF_YEAR, 7);
                dueDate = calendar.getTime();
                Log.d("TIME", "onClick: "+ dueDate.toString());
                break;
            default:
                break;
        }
    }
}
