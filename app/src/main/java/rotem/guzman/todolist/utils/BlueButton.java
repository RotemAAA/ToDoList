package rotem.guzman.todolist.utils;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.AppCompatButton;
import rotem.guzman.todolist.R;
import rotem.guzman.todolist.activities.AddTaskActivity;
import rotem.guzman.todolist.activities.MainActivity;
import rotem.guzman.todolist.database.DataManager;
import rotem.guzman.todolist.model.Task;

public class BlueButton extends AppCompatButton {
    public BlueButton(Context context) {
        super(context);
        init();
    }

    public BlueButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BlueButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        setBackgroundColor(getResources().getColor(R.color.blue));
        setTextColor(getResources().getColor(R.color.white));

        setOnClickListener(v->showInProgressTasks());
    }

    private List<Task> showInProgressTasks() {
//        Intent intent = new Intent(getContext() , AddTaskActivity.class);
//        getContext().startActivity(intent);
        List<Task> tasks = new ArrayList<>();
        tasks.addAll(DataManager.findTaskByStatus("in progress"));
        MainActivity.sortTasks(tasks);
        return tasks;
    }
}
