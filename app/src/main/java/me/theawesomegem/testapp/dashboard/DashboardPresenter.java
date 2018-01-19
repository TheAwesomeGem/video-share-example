package me.theawesomegem.testapp.dashboard;

import java.util.List;

import me.theawesomegem.testapp.dashboard.task.GetVideoTask;
import me.theawesomegem.testapp.data.model.VideoModel;
import me.theawesomegem.testapp.data.repo.VideoRepository;

/**
 * Created by TheAwesomeGem on 1/15/2018.
 */

public class DashboardPresenter implements DashboardContract.Presenter {

    private final DashboardContract.View viewDashboard;
    private final VideoRepository videoRepository;

    public VideoRepository getVideoRepository() {
        return videoRepository;
    }

    public DashboardPresenter(DashboardContract.View view, VideoRepository videoRepository)
    {
        this.viewDashboard = view;
        this.videoRepository = videoRepository;

        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void populateVideos(String name, int genreID) {
        VideoModel.VideoGenre genre = VideoModel.VideoGenre.values()[genreID];

        new GetVideoTask(this, name, genre.name()).execute();
    }

    @Override
    public void updateVideos(List<VideoModel> videoList) {

        viewDashboard.refreshVideos(videoList);
    }
}
