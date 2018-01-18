package me.theawesomegem.testapp.video;

import me.theawesomegem.testapp.BasePresenter;
import me.theawesomegem.testapp.BaseView;

/**
 * Created by TheAwesomeGem on 1/15/2018.
 */

public interface VideoContract {

    interface View extends BaseView<Presenter> {
        void start(String name, String url);
        void play();
        void pause();
    }

    interface Presenter extends BasePresenter {

    }
}
