package rotem.guzman.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTaskActivity extends AppCompatActivity {

    EditText myTaskEt;
    String newTaskString;
    Button saveBtn;

    final int REQUEST_CODE = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        myTaskEt = findViewById(R.id.my_task_et);
        saveBtn = findViewById(R.id.save_btn);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTaskString = myTaskEt.getText().toString();
                Task task = new Task(newTaskString);
                DataManager.addTask(task);
                Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
//                startActivity(intent);
            }
        });


    }
}
