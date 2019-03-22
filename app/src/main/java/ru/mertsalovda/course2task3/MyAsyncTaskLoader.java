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
    public int time = 5;

    public MyAsyncTaskLoader(@NonNull Context context, Bundle bundle) {
        super(context);
        if (bundle != null){
            bool = bundle.getBoolean(STATUS_KEY);
        }
    }

    @Override
    protected void onStartLoading() {
        Log.d(TAG, "onStartLoading");
        super.onStartLoading();
    }

    @Override
    protected void onStopLoading() {
        Log.d(TAG, "onStopLoading");
        super.onStopLoading();
    }

    @Override
    protected void onReset() {
        super.onReset();
    }

    @Override
    public void onCanceled(@Nullable Boolean data) {
        super.onCanceled(data);
    }

    @Override
    public void deliverResult(@Nullable Boolean data) {
        Log.d(TAG, "deliverResult");
        super.deliverResult(data);
    }

    @Nullable
    @Override
    public Boolean loadInBackground() {
        Log.d(TAG, "loadInBackground start");

        while (time != 0) {
            Log.d(TAG, "loadInBackground time = " + time);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time--;
        }
        Log.d(TAG, "loadInBackground done");
        return true;
    }

    @Override
    public void forceLoad() {
        Log.d(TAG, "forceLoad");
        super.forceLoad();
    }
}
