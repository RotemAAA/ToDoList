package rotem.guzman.todolist.utils;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import rotem.guzman.todolist.R;
import rotem.guzman.todolist.model.Task;

public class SingleTaskCell extends ConstraintLayout {

    private TextView todoTitle;
    private View statusIndicator;

    public SingleTaskCell(Context context) {
        super(context);
        init();
    }

    public SingleTaskCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SingleTaskCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(),R.layout.single_task_cell,this );
        todoTitle = findViewById(R.id.in_cell_task_tv);
        statusIndicator = findViewById(R.id.status_indicator);
        setBackgroundColor(Color.GRAY);
    }
    public void setTodoInfo(Task todo){

        int color = 0;
        if (todo.getStatus()!= null){
        switch (todo.getStatus()){
            case "waiting":
                color = Color.RED;
                break;
            case "in progress":
                color = Color.BLUE;
                break;

            case "done":
                color = Color.GREEN;
                break;
        }
        statusIndicator.setBackgroundColor(color);
        todoTitle.setText(todo.getTitle());
    }
}}
