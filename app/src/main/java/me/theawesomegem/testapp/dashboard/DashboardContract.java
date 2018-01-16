package me.theawesomegem.testapp.dashboard;

import java.util.List;

import me.theawesomegem.testapp.BasePresenter;
import me.theawesomegem.testapp.BaseView;
import me.theawesomegem.testapp.data.model.VideoModel;

/**
 * Created by TheAwesomeGem on 1/15/2018.
 */

public interface DashboardContract {

    interface View extends BaseView<Presenter> {
        String getSearchText();
        int getSelectedGenreID();
        void refreshVideos(List<VideoModel> videoList);
    }

    interface Presenter extends BasePresenter {

        void populateVideos(String name, int genreID);
        void updateVideos(List<VideoModel> videoList);
    }
}
