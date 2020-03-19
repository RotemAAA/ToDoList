package rotem.guzman.todolist;

import android.graphics.Color;
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

        switch (taskArrayList.get(position).getStatus()){
            case WAITING:
                holder.inCellTv.setTextColor(Color.BLUE);
                break;
            case IN_PROGRESS:
                holder.inCellTv.setTextColor(Color.RED);
                break;
            case DONE:
                holder.inCellTv.setTextColor(Color.GREEN);
                break;
            default:
                holder.inCellTv.setTextColor(Color.GRAY);
                break;
        }



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
