package me.theawesomegem.testapp.dashboard;

import android.app.Fragment;
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
import java.util.List;

import me.theawesomegem.testapp.R;
import me.theawesomegem.testapp.dashboard.adapter.VideoTileAdapter;
import me.theawesomegem.testapp.data.model.VideoModel;

/**
 * Created by TheAwesomeGem on 1/15/2018.
 */

public class DashboardFragment extends Fragment implements DashboardContract.View {

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
        gridVideos.setAdapter(new VideoTileAdapter(inflater, new ArrayList<>()));

        return root;
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
}
