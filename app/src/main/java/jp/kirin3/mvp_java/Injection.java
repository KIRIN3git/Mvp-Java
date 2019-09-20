/*
 * Copyright (C) 2015 The Android Open Source Project
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

package jp.kirin3.mvp_java;

import android.content.Context;

import androidx.annotation.NonNull;


import jp.kirin3.mvp_java.data.source.TasksRepository;
import jp.kirin3.mvp_java.data.source.local.TasksLocalDataSource;

import static com.google.common.base.Preconditions.checkNotNull;


public class Injection {

    public static TasksRepository provideTasksRepository(@NonNull Context context) {

        return TasksRepository.getInstance(TasksLocalDataSource.getInstance());
    }
}