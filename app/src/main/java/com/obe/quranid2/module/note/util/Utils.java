package com.obe.quranid2.module.note.util;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.obe.quranid2.module.note.model.Priority;
import com.obe.quranid2.module.note.model.Task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String formatDate(Date date){
        SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        dateFormat.applyPattern("MMM dd, yyyy");
        return dateFormat.format(date);
    }

    public static void hideSoftKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE
        );

        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static int priorityColor(Task task) {
        int color;
        if (task.getPriority() == Priority.HIGH){
            color = Color.argb(200, 201, 21, 23 );
        }else if (task.getPriority() == Priority.MEDIUM){
             color = Color.argb(200, 252, 165, 3 );
        }else {
             color = Color.argb(200, 0, 128, 0 );
        }

        return color;
    }
}
