package rotem.guzman.todolist.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import rotem.guzman.todolist.model.Task;

import static androidx.room.OnConflictStrategy.REPLACE;

@SuppressWarnings("ALL")
@Dao
public interface TaskDao {
    @Query("SELECT * FROM Task")
    List<Task> getAll();

    @Insert(onConflict = REPLACE)
    void insertAll(Task... tasks);

    @Insert(onConflict = REPLACE)
    void insertAll(List<Task> taskList);

    @Query("SELECT * FROM Task WHERE id = :taskId")
    Task getTodoById(int taskId);

    @Update()
    void update(Task task);

    @Delete
    void delete(Task task);


}
