package rotem.guzman.todolist.database.converters;

import java.util.Date;

import androidx.room.TypeConverter;

public class DateConverter {

    @TypeConverter
    public static Date longToDate(long dateLong){
        long time = dateLong;
        Date date = new Date();
        date.setTime(time);
        return date;
    }

    @TypeConverter
    public static long dateToLong (Date date){
        return date.getTime();
    }
}
