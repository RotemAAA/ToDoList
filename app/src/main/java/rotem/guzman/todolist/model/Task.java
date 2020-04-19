package rotem.guzman.todolist.model;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import rotem.guzman.todolist.database.converters.DateConverter;
import rotem.guzman.todolist.database.converters.StatusConverter;

@Entity(tableName = "Task")
@TypeConverters({StatusConverter.class, DateConverter.class})
public class Task {

    @PrimaryKey
    private int id;
    private String title;
    private String description;
//    private String status;
    private Date timeTaskSaved;

     private Status status;




    public Task(int id, String title, String description, Status status, Date timeTaskSaved) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.timeTaskSaved = timeTaskSaved;
    }

    public Date getTimeTaskSaved() {
        return timeTaskSaved;
    }

    public void setTimeTaskSaved(Date timeTaskSaved) {
        this.timeTaskSaved = timeTaskSaved;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
