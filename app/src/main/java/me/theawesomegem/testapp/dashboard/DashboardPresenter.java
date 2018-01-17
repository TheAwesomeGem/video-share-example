package me.theawesomegem.testapp.dashboard;

import android.content.Context;

import java.util.List;

import me.theawesomegem.testapp.dashboard.task.GetVideoTask;
import me.theawesomegem.testapp.data.database.AppDatabase;
import me.theawesomegem.testapp.data.model.VideoModel;

/**
 * Created by TheAwesomeGem on 1/15/2018.
 */

public class DashboardPresenter implements DashboardContract.Presenter {

    private final DashboardContract.View viewDashboard;
    private final AppDatabase database;

    public DashboardPresenter(DashboardContract.View view, Context context)
    {
        this.viewDashboard = view;
        this.database = AppDatabase.getAppDatabase(context);

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

    public AppDatabase getDatabase() {
        return database;
    }
}
