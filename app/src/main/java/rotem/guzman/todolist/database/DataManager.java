package rotem.guzman.todolist.database;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.room.Room;
import rotem.guzman.todolist.database.converters.DateConverter;
import rotem.guzman.todolist.model.Status;
import rotem.guzman.todolist.model.Task;

public class DataManager {

    public static List<Task> myTasks = new ArrayList<>();
    private static TaskDaoDatabase db;
    private static int count;


    public static int getCount() {
        return count;
    }

    public static int setCount() {

        DataManager.count = db.taskDao().getAll().size();
        return count;
    }

    public static List<Task> getMyTasks() {
        myTasks = db.taskDao().getAll();
        setCount();
        return myTasks;
    }

    public static void createDummyTask() {
        ArrayList<Task> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Date date = new Date();
            date.setTime(System.currentTimeMillis());
            Task task = new Task(i, "title " + i, "description " + i, Status.WAITING, date);
            list.add(task);
        }
        db.taskDao().insertAll(list);
        myTasks = list;
    }

    /**
     * @param context
     */
    public static void initRoom(Context context) {
        db = Room.databaseBuilder(context,
                TaskDaoDatabase.class, "database-task")
                //.fallbackToDestructiveMigration()//Clears the DB every new run
                .allowMainThreadQueries().build();

        //Use first time only
        //createDummyTask();
    }
//    public static void setMyTasks(ArrayList<Task> myTasks) {
//        DataManager.myTasks = myTasks;
//    }

    public static void addTask(Task task) {
        task.setId(count);
        myTasks.add(task);
        db.taskDao().insertAll(myTasks);


        count++;
    }

    public static void updateTask(Task task) {
        db.taskDao().update(task);
    }

    /**
     * Loops through the list of tasks and searches for the tasks by id
     *
     * @param id task id to find
     * @return the task with the specified id or null if not found
     */
    public static Task findTaskByID(int id) {
        for (Task task : db.taskDao().getAll()) {
            if (task.getId() == id) {
                return task;
            }
        }

        return null;
    }

    public static void deleteTask(Task task){
        myTasks.remove(task);
        db.taskDao().delete(task);



        count--;
    }

    public static List<Task> findTaskByStatus(String status) {
//        for (Task task : db.taskDao().getAll()) {
//            if (task.getStatus().equals(status)) {
//                return task;
//            }
//
//        }
        List<Task> tasks = new ArrayList<>();

        for (int i = 0; i < db.taskDao().getAll().size(); i++) {
            if (db.taskDao().getTodoById(i).getStatus().equals(status)){
                tasks.add(db.taskDao().getTodoById(i));
            }
        }
        return tasks;
    }
}
