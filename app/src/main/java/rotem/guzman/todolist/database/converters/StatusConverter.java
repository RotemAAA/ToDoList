package rotem.guzman.todolist.database.converters;

import androidx.room.TypeConverter;
import rotem.guzman.todolist.model.Status;

public class StatusConverter {

    @TypeConverter
    public static Status stringToStatus(String statusValue){
        return Status.getStatus(statusValue);
    }

    @TypeConverter
    public static String statusToString (Status status) {
        return status.state;
    }

    }
