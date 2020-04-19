package rotem.guzman.todolist.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Date;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import rotem.guzman.todolist.R;
import rotem.guzman.todolist.database.DataManager;
import rotem.guzman.todolist.model.Status;
import rotem.guzman.todolist.model.Task;

public class AddTaskActivity extends AppCompatActivity {

    //Status myStatus;
    final int REQUEST_CODE = 12345;
    EditText myTaskTitleEt, myTaskDescriptionEt;
    String newTaskTitleString;
    String newTaskDescriptionString;
    Button saveBtn;

    RadioGroup statusGroup;
    Status myStatus = Status.WAITING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        myTaskTitleEt = findViewById(R.id.my_task_title_et);
        myTaskDescriptionEt = findViewById(R.id.my_task_description_et);
        saveBtn = findViewById(R.id.save_btn);
        statusGroup = findViewById(R.id.status_group);

        statusGroup.check(R.id.waiting_rb);
        statusGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //  Status status =Status.WAITING;
                Status status = Status.WAITING;

                switch (checkedId) {
                    case R.id.waiting_rb:
                        status = Status.WAITING;
                        break;

                    case R.id.in_progress_rb:
                        status = Status.IN_PROGRESS;
                        break;

                    case R.id.done_rb:
                        status = Status.DONE;
                        break;

                }
                myStatus = status;
            }

        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
                startActivityForResult(intent, REQUEST_CODE);

//                AlertDialog.Builder alert = new AlertDialog.Builder(AddTaskActivity.this);
//                alert.setTitle("SAVE TASK");
//                alert.setMessage("Press ok to save task!");
//                alert.setCancelable(false);
//                alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(AddTaskActivity.this, "Your clicked cancel",
//                                Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
//                        finish();
//                        startActivity(intent);
//                    }
//                });
//                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(AddTaskActivity.this, "Your clicked OK",
//                                Toast.LENGTH_SHORT).show();
//                        Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
//                                startActivityForResult(intent, REQUEST_CODE);
//                            }
//                        }, 3000);
                newTaskTitleString = myTaskTitleEt.getText().toString();
                newTaskDescriptionString = myTaskDescriptionEt.getText().toString();
                Date date = new Date();
                date.setTime(System.currentTimeMillis());
                Task task = new Task(DataManager.getCount(), newTaskTitleString, newTaskDescriptionString, myStatus, date );


                DataManager.addTask(task);
            }
        });
//                alert.show();


//                startActivity(intent);

        ;


    }}

