package me.theawesomegem.testapp.video;

/**
 * Created by TheAwesomeGem on 1/15/2018.
 */

public class VideoPresenter implements VideoContract.Presenter {

    private final VideoContract.View viewDashboard;
    private final String videoName;
    private final String videoUrl;

    public VideoPresenter(VideoContract.View view, String videoName, String videoUrl)
    {
        this.viewDashboard = view;
        this.videoName = videoName;
        this.videoUrl = videoUrl;

        view.setPresenter(this);
    }

    @Override
    public void start() {
        viewDashboard.start(videoName, videoUrl);
    }
}
