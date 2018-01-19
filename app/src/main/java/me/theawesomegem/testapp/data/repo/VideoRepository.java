package me.theawesomegem.testapp.data.repo;

import android.content.Context;

import java.util.List;

import me.theawesomegem.testapp.data.database.AppDatabase;
import me.theawesomegem.testapp.data.model.VideoModel;

/**
 * Created by TheAwesomeGem on 1/18/2018.
 */

public class VideoRepository {
    private final AppDatabase database;
    private final VideoDao videoDao;

    public VideoRepository(Context context)
    {
        this.database = AppDatabase.getAppDatabase(context.getApplicationContext());
        this.videoDao = this.database.videoDao();
    }


    public List<VideoModel> loadAllByGenre(String genre) {
        return videoDao.loadAllByGenre(genre);
    }

    public List<VideoModel> loadAllByNamesAndGenre(String name, String genre) {
        return videoDao.loadAllByNamesAndGenre(name, genre);
    }
}
