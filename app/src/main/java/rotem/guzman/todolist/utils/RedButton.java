package rotem.guzman.todolist.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.AppCompatButton;
import rotem.guzman.todolist.R;
import rotem.guzman.todolist.activities.AddTaskActivity;
import rotem.guzman.todolist.activities.MainActivity;
import rotem.guzman.todolist.database.DataManager;
import rotem.guzman.todolist.model.Task;

public class RedButton extends AppCompatButton {
    public RedButton(Context context) {
        super(context);
        init();
    }

    public RedButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RedButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setBackgroundColor(getResources().getColor(R.color.red));
        setTextColor(getResources().getColor(R.color.white));

        setOnClickListener(v->showWaitingTasks());
    }

    public void moveToCompletedTasks(){
        Intent intent = new Intent(getContext(), MainActivity.class);
        getContext().startActivity(intent);
    }

    private List<Task> showWaitingTasks(){

        List<Task> tasks = new ArrayList<>();
        tasks.addAll(DataManager.findTaskByStatus("waiting"));
        MainActivity.sortTasks(tasks);
        return tasks;
    }
}
