package me.theawesomegem.testapp.dashboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.theawesomegem.testapp.R;
import me.theawesomegem.testapp.data.repo.VideoRepository;

public class DashboardActivity extends AppCompatActivity {

    private DashboardContract.Presenter dashboardPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        DashboardFragment dashboardFragment = (DashboardFragment) getFragmentManager().findFragmentById(R.id.fragDashboard);
        VideoRepository videoRepository = new VideoRepository(this);
        dashboardPresenter = new DashboardPresenter(dashboardFragment, videoRepository);
    }
}
