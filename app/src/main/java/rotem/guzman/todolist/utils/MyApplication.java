package rotem.guzman.todolist.utils;

import android.app.Application;

import com.facebook.stetho.Stetho;

import rotem.guzman.todolist.database.DataManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        DataManager.initRoom(this);
    }
}
