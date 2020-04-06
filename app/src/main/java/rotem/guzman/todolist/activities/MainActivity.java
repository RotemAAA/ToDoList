package rotem.guzman.todolist.activities;

import android.content.Intent;
import android.os.Bundle;
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
import rotem.guzman.todolist.utils.OnTaskClickListener;
import rotem.guzman.todolist.utils.TaskUpdateable;
import rotem.guzman.todolist.utils.TaskListenerAdapter;

public class MainActivity extends AppCompatActivity
        implements TaskUpdateable, OnTaskClickListener {

    static TaskListenerAdapter adapter;
    RecyclerView taskListRv;

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

        List<Task> myTasks = DataManager.getMyTasks();
        adapter = new TaskListenerAdapter(this, myTasks);
        taskListRv.setAdapter(adapter);
        adapter.setListener(this);

        if (!myTasks.isEmpty()) {
            showTaskFragmnet(myTasks.get(0), 0);
        }

        taskListRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        addNewTaskBtn = findViewById(R.id.add_new_task_btn);
        addNewTaskBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivity(intent);
        });
    }


    @Override
    public void updateTask(int taskPosition) {
        adapter.updateTask(taskPosition);
    }

    public static void sortTasks(List<Task> list){
        adapter.setList(list);
    }

    private void showTaskFragmnet(Task task, int position) {
        FragmentManager manger = getSupportFragmentManager();
        TaskFragment fragment = (TaskFragment) manger.findFragmentByTag("task");
        if (fragment != null) {
            fragment.updateInformationUI(task,position);
            return;
        }

        fragment = TaskFragment.newInstance(task.getId(), position );
//            fragment = new TaskFragment();
        manger.beginTransaction()
                .add(R.id.container, fragment, "task")
                //.addToBackStack("fragment") //Adds the replaced fragment to the stack
                .commit();
        fragment.updateInformationUI(task, position);
    }


    @Override
    public void onTaskClicked(Task task, int taskPosition) {
        showTaskFragmnet(task,taskPosition);
    }
}
