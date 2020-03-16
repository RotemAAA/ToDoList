package rotem.guzman.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    ArrayList <Task> taskArrayList;

    public TaskAdapter(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_task_cell, parent, false);


        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task =taskArrayList.get(position);
        holder.inCellTv.setText(task.getTask());



    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView inCellTv;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            inCellTv =itemView.findViewById(R.id.in_cell_task_tv);
        }
    }
}
