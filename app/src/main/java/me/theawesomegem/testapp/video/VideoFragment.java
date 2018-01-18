package me.theawesomegem.testapp.video;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import me.theawesomegem.testapp.R;

/**
 * Created by TheAwesomeGem on 1/15/2018.
 */

public class VideoFragment extends Fragment implements VideoContract.View {

    private VideoContract.Presenter presenterVideo;

    private VideoView videoView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_video, container, false);

        videoView = root.findViewById(R.id.videoView);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        this.presenterVideo.start();
    }

    @Override
    public void setPresenter(VideoContract.Presenter presenter) {
        this.presenterVideo = presenter;
    }

    @Override
    public void start(String name, String url) {
        //TODO: Video won't play. Dark screen.
        //TODO: Need custom view player. This one is crap.
        videoView.setVideoURI(Uri.parse(url));
        videoView.setMediaController(new MediaController(getActivity()));
        videoView.setZOrderOnTop(true);
        videoView.requestFocus();
        videoView.start();

    }

    @Override
    public void play() {

    }

    @Override
    public void pause() {

    }
}
