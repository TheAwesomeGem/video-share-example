package me.theawesomegem.testapp.dashboard.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import me.theawesomegem.testapp.dashboard.DashboardPresenter;
import me.theawesomegem.testapp.data.model.VideoModel;
import me.theawesomegem.testapp.data.repo.VideoRepository;

/**
 * Created by TheAwesomeGem on 1/23/2018.
 */

public class VideoLoader extends AsyncTaskLoader<List<VideoModel>>{
    private final DashboardPresenter presenter;
    private final String name;
    private final String genre;
    private final VideoRepository videoRepository;

    public VideoLoader(Context context, DashboardPresenter presenter, String name, String genre) {
        super(context);

        this.presenter = presenter;
        this.name = name;
        this.genre = genre;
        this.videoRepository = presenter.getVideoRepository();
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<VideoModel> loadInBackground() {
        List<VideoModel> videoList;

        if(this.name.isEmpty())
            videoList = videoRepository.loadAllByGenre(genre);
        else
            videoList = videoRepository.loadAllByNamesAndGenre(name, genre);

        return videoList;
    }

    @Override
    public void deliverResult(List<VideoModel> data) {
        super.deliverResult(data);
    }
}
