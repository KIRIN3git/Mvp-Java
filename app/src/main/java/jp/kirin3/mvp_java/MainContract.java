package jp.kirin3.mvp_java;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void showTextMsg(String word);
        void changeImage();

    }
    interface Presenter extends BasePresenter {

        void editTask();
        void addCount();
    }
}
