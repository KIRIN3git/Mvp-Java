package jp.kirin3.mvp_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import jp.kirin3.mvp_java.data.source.TasksRepository;
import jp.kirin3.mvp_java.data.source.local.TasksLocalDataSource;
import jp.kirin3.mvp_java.util.ActivityUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MainFragment mainFragment = (MainFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (mainFragment == null) {
            // フラグメントの生成
            mainFragment = MainFragment.newInstance();
            // アクティビティにフラグメントを挿入
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    mainFragment, R.id.contentFrame);
        }

        // レポジトリの生成
        TasksRepository tasksRepository = TasksRepository.getInstance(TasksLocalDataSource.getInstance());

        // プレゼンター生成時にフラグメントとレポジトリを渡している
        new MainPresenter(
                tasksRepository,
                mainFragment
        );
    }
}
