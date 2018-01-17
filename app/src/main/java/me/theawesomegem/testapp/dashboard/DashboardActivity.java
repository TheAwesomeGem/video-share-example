package me.theawesomegem.testapp.dashboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.theawesomegem.testapp.R;

public class DashboardActivity extends AppCompatActivity {

    private DashboardPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        DashboardFragment dashboardFragment = (DashboardFragment) getFragmentManager().findFragmentById(R.id.fragDashboard);
        presenter = new DashboardPresenter(dashboardFragment, this);
    }
}
