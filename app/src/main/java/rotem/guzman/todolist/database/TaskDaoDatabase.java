package rotem.guzman.todolist.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import rotem.guzman.todolist.model.Task;

@Database(entities = {Task.class}, version = 2, exportSchema = false)
public abstract class TaskDaoDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
