package rotem.guzman.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    RecyclerView taskListRv;

    FloatingActionButton addNewTaskBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskListRv = findViewById(R.id.task_list_rv);

        taskListRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        TaskAdapter adapter = new TaskAdapter(DataManager.getMyTasks());
        taskListRv.setAdapter(adapter);

        taskListRv.addItemDecoration(new DividerItemDecoration(getBaseContext(), DividerItemDecoration.VERTICAL));
        addNewTaskBtn = findViewById(R.id.add_new_task_btn);
        addNewTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);

            }
        });
    }
}
