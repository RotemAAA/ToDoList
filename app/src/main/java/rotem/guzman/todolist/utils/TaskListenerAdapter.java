package rotem.guzman.todolist.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import rotem.guzman.todolist.R;
import rotem.guzman.todolist.model.Task;

public class TaskListenerAdapter extends RecyclerView.Adapter<TaskListenerAdapter.TaskViewHolder>
implements TaskUpdateable {

    private List<Task> list;
    private Context context;
    private OnTaskClickListener listener;

    public void setListener(OnTaskClickListener listener) {
        this.listener = listener;
    }

    public TaskListenerAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    public List<Task> getList() {
        return list;
    }

    public void setList(List<Task> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void updateData(List<Task> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void updateTask(int taskPosition) {
        notifyItemChanged(taskPosition);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_task_cell_layout, parent, false);

        return new TaskViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, final int position) {
        final Task task = list.get(position);

        holder.itemView.setOnClickListener(view -> {
            if (listener !=null) {
                listener.onTaskClicked(task,position);
            }
            Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
        });
        if (task != null)
            holder.cell.setTodoInfo(task);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        SingleTaskCell cell;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            cell = itemView.findViewById(R.id.cell);
        }
    }
}
