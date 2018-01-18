package me.theawesomegem.testapp.dashboard.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.theawesomegem.testapp.R;
import me.theawesomegem.testapp.dashboard.DashboardContract;
import me.theawesomegem.testapp.data.model.VideoModel;

/**
 * Created by TheAwesomeGem on 1/15/2018.
 */

public class VideoTileAdapter extends BaseAdapter {
    private DashboardContract.View dashboardView;
    private final LayoutInflater inflater;
    private final List<VideoModel> videoList;

    public VideoTileAdapter(DashboardContract.View view, LayoutInflater inflater, List<VideoModel> videoList)
    {
        this.dashboardView = view;
        this.inflater = inflater;
        this.videoList = new ArrayList<>(videoList);
    }

    @Override
    public int getCount() {
        return videoList.size();
    }

    @Override
    public Object getItem(int i) {
        return videoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View videoTileView = inflater.inflate(R.layout.videotile_dashboard, null);
        final VideoModel video = videoList.get(i);

        TextView lblVideoName = videoTileView.findViewById(R.id.lblVideoName);
        lblVideoName.setText(video.getName());

        ImageButton btnVideo = videoTileView.findViewById(R.id.btnVideo);
        btnVideo.setOnClickListener(v -> dashboardView.openVideo(video.getName(), video.getUrl()));

        return videoTileView;
    }

    public void setVideoList(List<VideoModel> videoList) {
        this.videoList.clear();
        this.videoList.addAll(videoList);
    }
}
