package me.theawesomegem.testapp.dashboard.task;

import android.os.AsyncTask;

import java.util.List;

import me.theawesomegem.testapp.dashboard.DashboardPresenter;
import me.theawesomegem.testapp.data.model.VideoModel;
import me.theawesomegem.testapp.data.repo.VideoDao;

/**
 * Created by TheAwesomeGem on 1/15/2018.
 */

public class GetVideoTask extends AsyncTask<Void, Void, Void> {

    private final DashboardPresenter presenter;
    private final String name;
    private final String genre;
    private final VideoDao videoDao;

    public GetVideoTask(DashboardPresenter presenter, String name, String genre)
    {
        this.presenter = presenter;
        this.name = name;
        this.genre = genre;
        this.videoDao = presenter.getDatabase().videoDao();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        List<VideoModel> videoList;

        if(this.name.isEmpty())
            videoList = videoDao.loadAllByGenre(genre);
        else
            videoList = videoDao.loadAllByNamesAndGenre(name, genre);

        presenter.updateVideos(videoList);

        return null;
    }
}
