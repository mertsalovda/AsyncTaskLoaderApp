package ru.mertsalovda.course2task3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import 	android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Boolean> {

    private Button btnStart;
    private TextView tvStatus;
    private ProgressBar progressBar;
    private Loader<Boolean> mLoader;
    public static final int LOADER_ID = 1;
    public static final String TAG = "LogTAG";
    private static final String KEY_BTNSTART = "BTNSTART";
    private static final String KEY_PROGRESSBAR = "PROGRESSBAR";
    private static final String KEY_TVSTATUS = "TVSTATUS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        tvStatus = findViewById(R.id.tvStatus);
        progressBar = findViewById(R.id.progressBar);
        // Инициализируем загрузчик с идентификатором
        mLoader = getSupportLoaderManager().initLoader(LOADER_ID,
                null,
                this);

        if (savedInstanceState != null){
            btnStart.setEnabled(savedInstanceState.getBoolean(KEY_BTNSTART));
            tvStatus.setText(savedInstanceState.getString(KEY_TVSTATUS));
            progressBar.setVisibility(savedInstanceState.getInt(KEY_PROGRESSBAR));
        }

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoader.onContentChanged();
                progressBar.setVisibility(View.VISIBLE);
                btnStart.setEnabled(false);
                tvStatus.setText(R.string.loading);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_BTNSTART, btnStart.isEnabled());
        savedInstanceState.putInt(KEY_PROGRESSBAR, progressBar.getVisibility());
        savedInstanceState.putString(KEY_TVSTATUS, tvStatus.getText().toString());
    }

    @NonNull
    @Override
    public Loader<Boolean> onCreateLoader(int id, @Nullable Bundle bundle) {
        if (id == LOADER_ID) {
            Log.d(TAG, "MainActivity onCreateLoader");
            mLoader = new MyAsyncTaskLoader(this);
        }
        return mLoader;
    }
    @Override
    public void onLoadFinished(@NonNull Loader<Boolean> loader, Boolean result) {
        Log.d(TAG, "MainActivity onLoadFinished result " + result);
        if (result){
            btnStart.setEnabled(true);
            progressBar.setVisibility(View.GONE);
            tvStatus.setText(R.string.ready);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Boolean> loader) {
        Log.d(TAG, "MainActivity onLoaderReset");
    }
}
