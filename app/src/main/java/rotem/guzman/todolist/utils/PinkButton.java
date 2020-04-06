package rotem.guzman.todolist.utils;

import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.AppCompatButton;
import rotem.guzman.todolist.R;
import rotem.guzman.todolist.activities.MainActivity;
import rotem.guzman.todolist.database.DataManager;
import rotem.guzman.todolist.model.Task;

public class PinkButton extends AppCompatButton {
    public PinkButton(Context context) {
        super(context);
        init();
    }

    public PinkButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PinkButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setBackgroundColor(getResources().getColor(R.color.pink));
        setTextColor(getResources().getColor(R.color.black));

        setOnClickListener(v->showAllTasks());
    }

    private List<Task> showAllTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.addAll(DataManager.getMyTasks());
        MainActivity.sortTasks(tasks);
        return tasks;
    }

}
