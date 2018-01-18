package me.theawesomegem.testapp.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.theawesomegem.testapp.R;

/**
 * Created by TheAwesomeGem on 1/17/2018.
 */

public class VideoActivity extends AppCompatActivity {

    private VideoContract.Presenter videoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Intent intent = getIntent();

        if(intent == null)
            return;

        Bundle extraData = intent.getExtras();

        if(extraData == null)
            return;

        VideoFragment videoFragment = (VideoFragment) getFragmentManager().findFragmentById(R.id.fragVideo);
        videoPresenter = new VideoPresenter(videoFragment, extraData.getString("videoName"), extraData.getString("videoUrl"));
    }
}
