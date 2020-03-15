package rotem.guzman.todolist;

import java.util.ArrayList;

public class DataManager {
    static ArrayList<Task> myTasks = new ArrayList<>();

    public static ArrayList<Task> getMyTasks() {
        return myTasks;
    }

    public static void setMyTasks(ArrayList<Task> myTasks) {
        DataManager.myTasks = myTasks;
    }

    public static void addTask (Task task){

        myTasks.add(task);

    }
}
