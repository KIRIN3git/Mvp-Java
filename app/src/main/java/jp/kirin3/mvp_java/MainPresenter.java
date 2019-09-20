package jp.kirin3.mvp_java;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import jp.kirin3.mvp_java.data.Task;
import jp.kirin3.mvp_java.data.source.TasksDataSource;
import jp.kirin3.mvp_java.data.source.TasksRepository;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainPresenter implements MainContract.Presenter {

    // Mainで作成して注入しても良いがここで生成してみる
    private final TasksRepository mTasksRepository;

    private final MainContract.View mMainView;
    private int count = 0;


    public MainPresenter(
                              @NonNull TasksRepository tasksRepository,
                              @NonNull MainContract.View mainView) {
        mTasksRepository = checkNotNull(tasksRepository, "tasksRepository cannot be null!");
        mMainView = checkNotNull(mainView, "taskDetailView cannot be null!");

        // フラグメントにプレゼンターを渡して相互に持ち合う
        mMainView.setPresenter(this);

        loadTasks();
        Log.w("DEBUG_DATA","END");
    }

    private void loadTasks(){
        Log.w("DEBUG_DATA","TasksPresenter getTasks1");
        mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                Log.w("DEBUG_DATA","tasks.get(0).getId() " + tasks.get(0).getId());
            }

            @Override
            public void onDataNotAvailable() {

                Log.w("DEBUG_DATA", "onDataNotAvailable");
            }
        });
    }


    // データを取得したりイジったりする関数をオーバーライドしている
    @Override
    public void start() {
        mMainView.showTextMsg("WORD");
        loadTasks();

    }

    @Override
    public void editTask() {

    }

    @Override
    public void addCount() {
        count++;
        mMainView.changeImage();

    }
}
