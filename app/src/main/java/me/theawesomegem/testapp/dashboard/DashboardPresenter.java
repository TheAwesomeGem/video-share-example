package me.theawesomegem.testapp.dashboard;

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

    public DashboardPresenter(DashboardContract.View view, AppDatabase database)
    {
        this.viewDashboard = view;
        this.database = database;

        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void populateVideos(String name, int genreID) {

        System.out.println("Populating Videos now...");
        VideoModel.VideoGenre genre = VideoModel.VideoGenre.values()[genreID];
        System.out.println("Name:" + name);
        System.out.println("Genre:" + genreID );

        new GetVideoTask(this, name, genre.name()).execute();
    }

    @Override
    public void updateVideos(List<VideoModel> videoList) {
        System.out.println("==++Updating Videos:" + videoList.size());

        viewDashboard.refreshVideos(videoList);
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
