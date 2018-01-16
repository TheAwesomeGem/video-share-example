package me.theawesomegem.testapp.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import me.theawesomegem.testapp.data.model.VideoModel;
import me.theawesomegem.testapp.data.repo.VideoDao;

/**
 * Created by TheAwesomeGem on 1/15/2018.
 */

@Database(entities = {VideoModel.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{
    public abstract VideoDao videoDao();
}
