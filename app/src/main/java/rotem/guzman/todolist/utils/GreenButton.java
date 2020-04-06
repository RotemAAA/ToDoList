package rotem.guzman.todolist.utils;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.AppCompatButton;
import rotem.guzman.todolist.R;
import rotem.guzman.todolist.activities.AddTaskActivity;
import rotem.guzman.todolist.activities.MainActivity;
import rotem.guzman.todolist.database.DataManager;
import rotem.guzman.todolist.model.Task;

public class GreenButton extends AppCompatButton {
    public GreenButton(Context context) {
        super(context);
        init();
    }

    public GreenButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GreenButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setBackgroundColor(getResources().getColor(R.color.green));
        setTextColor(getResources().getColor(R.color.black));

        setOnClickListener(v->showDoneTasks());
    }

    private List<Task> showDoneTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.addAll(DataManager.findTaskByStatus("done"));
        MainActivity.sortTasks(tasks);
        return tasks;
    }

}
