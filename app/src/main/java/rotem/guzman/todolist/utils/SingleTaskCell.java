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
            case WAITING:
                color = getResources().getColor(R.color.red);
                break;
            case IN_PROGRESS:
                color = getResources().getColor(R.color.blue);
                break;

            case DONE:
                color = getResources().getColor(R.color.green);
                break;
        }
        statusIndicator.setBackgroundColor(color);
        todoTitle.setText(todo.getTitle());
    }
}}
