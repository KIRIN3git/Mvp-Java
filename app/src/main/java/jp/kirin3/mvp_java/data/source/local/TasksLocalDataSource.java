/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.kirin3.mvp_java.data.source.local;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;


import java.util.ArrayList;
import java.util.List;

import jp.kirin3.mvp_java.data.Task;
import jp.kirin3.mvp_java.data.source.TasksDataSource;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Concrete implementation of a data source as a db.
 */
public class TasksLocalDataSource implements TasksDataSource {

    private static volatile TasksLocalDataSource INSTANCE;

    // Prevent direct instantiation.
    private TasksLocalDataSource() {
    }

    public static TasksLocalDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (TasksLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TasksLocalDataSource();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.w("DEBUG_DATA","TasksLocalDataSource getTasks1");
                    List<Task> tasks = new ArrayList<Task>();

                    tasks.add(new Task("11", "title1",false));
                    tasks.add(new Task("12", "title2",true));

                    Thread.sleep(3000); //ミリ秒

                    if (tasks.isEmpty()) {
                        // This will be called if the table is new or just empty.
                        callback.onDataNotAvailable();
                    } else {
                        callback.onTasksLoaded(tasks);
                    }

                } catch (InterruptedException e) {
                }
            }
        });
        thread.start();

/*
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.w("DEBUG_DATA","getTasks run");
                final List<Task> tasks = new ArrayList<Task>();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {

                        tasks.add(new Task("11", "title",false));
                        if (tasks.isEmpty()) {
                            // This will be called if the table is new or just empty.
                            callback.onDataNotAvailable();
                        } else {
                            callback.onTasksLoadedXXXXX(tasks);
                        }
                    }
                });
            }
        };

        mAppExecutors.diskIO().execute(runnable);

 */
    }
}
