package rotem.guzman.todolist.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import rotem.guzman.todolist.R;
import rotem.guzman.todolist.activities.AddTaskActivity;
import rotem.guzman.todolist.activities.MainActivity;
import rotem.guzman.todolist.database.DataManager;
import rotem.guzman.todolist.model.Status;
import rotem.guzman.todolist.model.Task;
import rotem.guzman.todolist.utils.TaskUpdateable;

public class TaskFragment extends Fragment {
    private Task task;
    private EditText title, description;
    private RadioGroup statusGroup;
    private int currentTaskPosition;
    Button deleteBtn;


    public static TaskFragment newInstance(int taskId, int position) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putInt("TASK_ID", taskId);
        args.putInt("POSITION", position);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            int taskId = arguments.getInt("TASK_ID");
            task = DataManager.findTaskByID(taskId);
            currentTaskPosition = arguments.getInt("POSITION");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title = view.findViewById(R.id.todo_title);

        Button updateBtn = view.findViewById(R.id.update_btn);
        deleteBtn = view.findViewById(R.id.delete_btn);
//        if (title != null)
//        title.setText(task.getTitle());
//        if (description != null)
//        description.setText(task.getDescription());



        updateBtn.setOnClickListener(v -> {
            String newTitle = title.getText().toString();
            String newDescription = description.getText().toString();
            task.setTitle(newTitle );
            task.setDescription(newDescription);
            DataManager.updateTask(task);
            updateTaskInParent();
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                alertDialog.setTitle("DELETE TASK");
                alertDialog.setMessage("Press ok to DELETE task!");
                alertDialog.setCancelable(false);
                alertDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Your clicked cancel",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setPositiveButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteTask(task, currentTaskPosition);
                        updateTaskInParent();
                        updateInformationUI(task, currentTaskPosition);
                    }
                });
                alertDialog.show();
            }

        });

        description = view.findViewById(R.id.todo_description);

        statusGroup = view.findViewById(R.id.status_group);
        statusGroup.setOnCheckedChangeListener((group, checkedId) -> {
            Status status = Status.WAITING;
            switch (checkedId) {
                case R.id.waiting_status:
                    status = Status.WAITING;
                    break;
                case R.id.in_progress_status:
                    status = Status.IN_PROGRESS;
                    break;
                case R.id.done_status:
                    status = Status.DONE;
                    break;
            }
            task.setStatus(status);
            DataManager.updateTask(task);
            updateTaskInParent();

        });
    }

    private void updateTaskInParent() {
        Context context = getContext();
        if (context instanceof TaskUpdateable) {
            ((TaskUpdateable) context).updateTask(currentTaskPosition);
        }
    }
public void deleteTask(Task task, int position){
        DataManager.deleteTask(task);
        if (DataManager.getMyTasks()!= null) {
            Task task1 = DataManager.getMyTasks().get(0);
       updateTaskInParent();
       updateInformationUI(task1, 0);
}}
    /**
     * updates info infragment
     * @param task
     * @param position
     */
    public void updateInformationUI(Task task, int position) {
        this.task = task;
        this.currentTaskPosition = position;
        if (getView() != null) {
            title.setText( task.getTitle());
            description.setText(task.getDescription());
//            if (task.getTitle() != newTitle && newTitle != null) {
//                task.setTitle(newTitle);
//                title.setText(task.getTitle());
//            }else {
//                title.setText(task.getTitle());
//            }
//            description.setText(task.getDescription());

            //indicate correct status
            @IdRes int id = 0;
            switch (task.getStatus()) {
                case WAITING:
                    id = R.id.waiting_status;
                    break;
                case IN_PROGRESS:
                    id = R.id.in_progress_status;
                    break;
                case DONE:
                    id = R.id.done_status;
                    break;

            }
            statusGroup.check(id);
        }
    }

}