package rotem.guzman.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {

    EditText myTaskEt, myStatusEt;
    String newTaskString, stringStatus;
    Status status;
    Button saveBtn;

    final int REQUEST_CODE = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        myTaskEt = findViewById(R.id.my_task_et);
        saveBtn = findViewById(R.id.save_btn);
        myStatusEt = findViewById(R.id.my_status_et);



        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(AddTaskActivity.this);
                alert.setTitle("SAVE TASK");
                alert.setMessage("Press ok to save task!");
                alert.setCancelable(false);
                alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AddTaskActivity.this, "Your clicked cancel",
                                Toast.LENGTH_SHORT).show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
                                finish();
                            }
                        }, 3000);
                    }
                });
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AddTaskActivity.this, "Your clicked OK",
                                Toast.LENGTH_SHORT).show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
                                startActivityForResult(intent, REQUEST_CODE);
                            }
                        }, 3000);
                    }
                });
                alert.show();

                newTaskString = myTaskEt.getText().toString();
                stringStatus = myStatusEt.getText().toString();
                Task task = new Task(newTaskString, Status.getStatus(stringStatus.toLowerCase()));

                DataManager.addTask(task);


//                startActivity(intent);
            }
        });


    }
}
