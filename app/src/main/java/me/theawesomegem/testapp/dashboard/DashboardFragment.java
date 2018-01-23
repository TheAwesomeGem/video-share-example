package me.theawesomegem.testapp.dashboard;

import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.theawesomegem.testapp.R;
import me.theawesomegem.testapp.dashboard.adapter.VideoTileAdapter;
import me.theawesomegem.testapp.dashboard.loader.VideoLoader;
import me.theawesomegem.testapp.data.model.VideoModel;
import me.theawesomegem.testapp.video.VideoActivity;

/**
 * Created by TheAwesomeGem on 1/15/2018.
 */

public class DashboardFragment extends Fragment implements DashboardContract.View {

    public final int VIDEO_LOADER_ID = 1;

    private DashboardContract.Presenter presenterDashboard;

    private EditText txtSearch;
    private Spinner comboGenre;
    private GridView gridVideos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        txtSearch = root.findViewById(R.id.txtSearch);
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenterDashboard.populateVideos(editable.toString(), getSelectedGenreID());
            }
        });

        comboGenre = root.findViewById(R.id.comboGenre);
        comboGenre.setAdapter(new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, VideoModel.VideoGenre.values()));
        comboGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenterDashboard.populateVideos(getSearchText(), getSelectedGenreID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        gridVideos = root.findViewById(R.id.gridVideos);
        gridVideos.setAdapter(new VideoTileAdapter(this, inflater, new ArrayList<>()));

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        this.presenterDashboard.start();
    }

    @Override
    public void setPresenter(DashboardContract.Presenter presenter) {
        this.presenterDashboard = presenter;
    }

    @Override
    public String getSearchText() {
        return txtSearch.getText().toString();
    }

    @Override
    public int getSelectedGenreID() {
        return comboGenre.getSelectedItemPosition();
    }

    @Override
    public void refreshVideos(List<VideoModel> videoList) {
        VideoTileAdapter adapter = (VideoTileAdapter) gridVideos.getAdapter();

        adapter.setVideoList(videoList);
    }

    @Override
    public void openVideo(String name, String url) {
        Activity parentActivty = getActivity();

        Intent videoOpenIntent = new Intent(parentActivty, VideoActivity.class);

        videoOpenIntent.putExtra("videoName", name);
        videoOpenIntent.putExtra("videoUrl", url);

        parentActivty.startActivity(videoOpenIntent);
    }

    @Override
    public void startVideoLoader(String name, String genre) {
        Bundle videoLoaderBundle = new Bundle(2);
        videoLoaderBundle.putString("name", name);
        videoLoaderBundle.putString("genre", genre);

        getLoaderManager().restartLoader(VIDEO_LOADER_ID, videoLoaderBundle, videoLoaderListener);
    }

    private LoaderManager.LoaderCallbacks<List<VideoModel>> videoLoaderListener
            = new LoaderManager.LoaderCallbacks<List<VideoModel>>() {

        @Override
        public Loader<List<VideoModel>> onCreateLoader(int i, Bundle bundle) {
            String name = bundle.getString("name");
            String genre = bundle.getString("genre");

            return new VideoLoader(getActivity(), (DashboardPresenter) presenterDashboard, name, genre);
        }

        @Override
        public void onLoadFinished(Loader<List<VideoModel>> loader, List<VideoModel> videoList) {
            presenterDashboard.updateVideos(videoList);
        }

        @Override
        public void onLoaderReset(Loader<List<VideoModel>> loader) {
            presenterDashboard.updateVideos(Collections.emptyList());
        }
    };
}
