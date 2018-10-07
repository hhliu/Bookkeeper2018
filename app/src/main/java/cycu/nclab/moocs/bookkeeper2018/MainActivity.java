package cycu.nclab.moocs.bookkeeper2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, TAG + ": enter onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, TAG + ": enter onStart()");
        // 找到此Activity的root View，並增加click監聽
        (this.findViewById(android.R.id.content)).setOnClickListener(this);
    }

    @Override
    protected void onStop() {
        Log.i(TAG, TAG + ": enter onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, TAG + ": enter onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, TAG + ": enter onPause()");
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, TAG + ": enter onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, TAG + ": enter onRestart()");
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, Bookkeeping.class));

        // 增加過場動畫
        overridePendingTransition(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        // 結束目前的Activity
        this.finish();
    }
}
