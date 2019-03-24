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
    private static final String STATUS_KEY = "STATUS_KEY";
    private Boolean bool;
    public static final int TIME = 5;

    public MyAsyncTaskLoader(@NonNull Context context, Bundle bundle) {
        super(context);
        if (bundle != null){
            bool = bundle.getBoolean(STATUS_KEY);
        }
    }

    @Override
    protected void onStartLoading() {
        Log.d(TAG, "MyAsyncTaskLoader onStartLoading");
        super.onStartLoading();

    }

    @Override
    protected void onStopLoading() {
        Log.d(TAG, "MyAsyncTaskLoader onStopLoading");
        super.onStopLoading();
    }

    @Override
    protected void onReset() {
        Log.d(TAG, "MyAsyncTaskLoader onReset");
        super.onReset();
    }

    @Override
    public void onCanceled(@Nullable Boolean data) {
        Log.d(TAG, "MyAsyncTaskLoader onCanceled");
        super.onCanceled(data);
    }

    @Override
    public void deliverResult(@Nullable Boolean data) {
        Log.d(TAG, "MyAsyncTaskLoader deliverResult");
        super.deliverResult(data);
    }

    @Nullable
    @Override
    public Boolean loadInBackground() {
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
        return true;
    }

    @Override
    public void forceLoad() {
        Log.d(TAG, "MyAsyncTaskLoader forceLoad");
        super.forceLoad();
    }
}
