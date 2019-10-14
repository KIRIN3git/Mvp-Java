package jp.kirin3.mvp_java.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Task {

    private final String mId;
    private final String mTitle;

    private final boolean mCompleted;


    public Task(@NonNull String id, @Nullable String title,boolean completed){
        mId = id;
        mTitle = title;
        mCompleted = completed;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public boolean isActive() {
        return !mCompleted;
    }

}
