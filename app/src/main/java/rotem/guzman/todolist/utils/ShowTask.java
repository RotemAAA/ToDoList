package rotem.guzman.todolist.utils;

import rotem.guzman.todolist.model.Task;

public interface ShowTask {
    void showTask(Task task);

    //void updateStatus(int id, Status status);

    void updateStatus(int id, String status);
    void updateTitle(int id, String title);
    void updateDescription(int id, String title);

}
