package me.theawesomegem.testapp.dashboard.task;

import android.os.AsyncTask;

import java.util.List;

import me.theawesomegem.testapp.dashboard.DashboardPresenter;
import me.theawesomegem.testapp.data.model.VideoModel;
import me.theawesomegem.testapp.data.repo.VideoRepository;

/**
 * Created by TheAwesomeGem on 1/15/2018.
 */

public class GetVideoTask extends AsyncTask<Void, Void, Void> {

    private final DashboardPresenter presenter;
    private final String name;
    private final String genre;
    private final VideoRepository videoRepository;

    public GetVideoTask(DashboardPresenter presenter, String name, String genre)
    {
        this.presenter = presenter;
        this.name = name;
        this.genre = genre;
        this.videoRepository = presenter.getVideoRepository();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        List<VideoModel> videoList;

        if(this.name.isEmpty())
            videoList = videoRepository.loadAllByGenre(genre);
        else
            videoList = videoRepository.loadAllByNamesAndGenre(name, genre);

        presenter.updateVideos(videoList);

        return null;
    }
}
