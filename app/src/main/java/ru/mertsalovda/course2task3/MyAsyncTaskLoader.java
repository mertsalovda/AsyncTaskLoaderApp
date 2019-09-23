package ru.mertsalovda.course2task3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyAsyncTaskLoader extends AsyncTaskLoader<Boolean> {

    public static final String TAG = "LogTAG";
    private Boolean isStart = false;
    public static final int TIME = 5;

    public MyAsyncTaskLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        Log.d(TAG, "MyAsyncTaskLoader onStartLoading");
        super.onStartLoading();
        if(isStart){
            deliverResult(false);
        }
    }

    @Nullable
    @Override
    public Boolean loadInBackground() {
        isStart = true;
        Log.d(TAG, "MyAsyncTaskLoader loadInBackground start");
        int tempTime = TIME;
        while (tempTime != 0) {
            Log.d(TAG, "MyAsyncTaskLoader loadInBackground TIME = " + tempTime);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tempTime--;
        }
        Log.d(TAG, "MyAsyncTaskLoader loadInBackground done");
        isStart = false;
        return true;
    }
}
