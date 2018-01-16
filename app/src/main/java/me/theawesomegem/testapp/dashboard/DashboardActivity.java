package me.theawesomegem.testapp.dashboard;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.theawesomegem.testapp.R;
import me.theawesomegem.testapp.data.database.AppDatabase;

public class DashboardActivity extends AppCompatActivity {

    private DashboardPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
        AppDatabase.class, "video-repo").fallbackToDestructiveMigration().build();

        DashboardFragment dashboardFragment = (DashboardFragment) getFragmentManager().findFragmentById(R.id.fragDashboard);

        presenter = new DashboardPresenter(dashboardFragment, db);
    }
}
