package rotem.guzman.todolist.utils;

import rotem.guzman.todolist.model.Task;

public interface OnTaskClickListener {
    void onTaskClicked(Task task, int taskPosition);
}
