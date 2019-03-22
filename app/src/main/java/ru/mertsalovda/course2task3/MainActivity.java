package ru.mertsalovda.course2task3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
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
    public static final String TAG = "MyTag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        tvStatus = findViewById(R.id.tvStatus);
        progressBar = findViewById(R.id.progressBar);
        // Инициализируем загрузчик с идентификатором
        // Если загрузчик не существует, то он будет создан,
        // иначе он будет перезапущен.
        mLoader = getSupportLoaderManager().initLoader(LOADER_ID,
                savedInstanceState,
                this);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoader.onContentChanged();
                progressBar.setVisibility(View.VISIBLE);
                tvStatus.setText(R.string.loading);
            }
        });
    }
    // Будет вызван, если до этого не существовал
    // Это значит, что при повороте не будет вызываться
    // так как предыдущий загрузчик с данным ID уже был создан ранее
    // Будет также вызван при рестарте через метод LoaderManager.restartLoader()
    @NonNull
    @Override
    public Loader<Boolean> onCreateLoader(int id, @Nullable Bundle bundle) {
        Loader<Boolean> mLoader = null;

        if (id == LOADER_ID) {
            Log.d(TAG, "onCreateLoader");
            mLoader = new MyAsyncTaskLoader(this, bundle);
        }
        return mLoader;
    }
    // Вызовется, когда загрузчик закончит свою работу. Вызывается в основном потоке
    // Может вызываться несколько раз при изменении данных
    // Также вызывается при поворотах
    @Override
    public void onLoadFinished(@NonNull Loader<Boolean> loader, Boolean result) {
        Log.d(TAG, "onLoadFinished");
        if (result){
            progressBar.setVisibility(View.GONE);
            tvStatus.setText(R.string.ready);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Boolean> loader) {
        Log.d(TAG, "onLoaderReset");
    }
}
