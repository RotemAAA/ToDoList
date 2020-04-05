package rotem.guzman.todolist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import rotem.guzman.todolist.R;
import rotem.guzman.todolist.database.DataManager;
import rotem.guzman.todolist.fragments.TaskFragment;
import rotem.guzman.todolist.model.Task;
import rotem.guzman.todolist.utils.ShowTask;
import rotem.guzman.todolist.utils.TaskAdapter;

public class MainActivity extends AppCompatActivity implements ShowTask {

    TaskAdapter adapter;
    RecyclerView taskListRv;
    List<Task> list = DataManager.getMyTasks();

    Button addNewTaskBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskListRv = findViewById(R.id.task_list_rv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false);
        taskListRv.setLayoutManager(layoutManager);

        adapter = new TaskAdapter(this, DataManager.getMyTasks());

        taskListRv.setAdapter(adapter);

        taskListRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        addNewTaskBtn = findViewById(R.id.add_new_task_btn);
        addNewTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showTask(Task task) {
        FragmentManager manger = getSupportFragmentManager();
        TaskFragment fragment = (TaskFragment) manger.findFragmentByTag("task");
        if (fragment != null) {
            fragment.updateInformationUI(task);
            return;
        } else {
            fragment = TaskFragment.newInstance(task.getId());
//            fragment = new TaskFragment();
            manger.beginTransaction()
                    .add(R.id.container, fragment, "task")
                    //.addToBackStack("fragment") //Adds the replaced fragment to the stack
                    .commit();
            fragment.updateInformationUI(task);
        }
    }

    @Override
    public void updateStatus(int id, String status) {
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.getId() == id) {
                task.setStatus(status);
                DataManager.updateTask(task);
                adapter.updateData(list);
                return;
            }
        }
        //Toast.makeText(this, status.name(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateTitle(int id, String title) {
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.getId() == id) {
                task.setTitle(title);
                DataManager.updateTask(task);
                adapter.updateData(list);
                return;
            }
        }}

    @Override
    public void updateDescription(int id, String description) {
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.getId() == id) {
                task.setDescription(description);
                DataManager.updateTask(task);
                adapter.updateData(list);
                return;
            }}
    }
}
