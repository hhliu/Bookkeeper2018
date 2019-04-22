package cycu.nclab.moocs.bookkeeper2018;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    final String TAG = this.getClass().getSimpleName();
    final int demoCase = 1; // 0: click to switch； 1: use handler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "enter onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "enter onStat()");
        switch (demoCase) {
            case 0:
                // 找到此Activity的root View，並增加click監聽
                (this.findViewById(android.R.id.content)).setOnClickListener(this);
                break;
            case 1:
                // 使用Handler自動切換螢幕畫面
                Message msg = myHandler.obtainMessage();    // 從Message pool裡面取一個message出來，
                // 比新建立一個有效率。
                // Ctr+Q 查看指令文件
                myHandler.sendMessageDelayed(msg, 500); // 0.5秒後執行
                break;
        }
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "enter onStop()");
        switch (demoCase) {
            case 0:
                // 找到此Activity的root View，並移除click監聽
                (this.findViewById(android.R.id.content)).setOnClickListener(null);
                break;
            case 1:
                // Do nothing
                break;
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "enter onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "enter onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "enter onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "enter onRestart()");
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

    // ---------------------使用handler的範例------------------------------------------------------

    private Handler myHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            startActivity(new Intent(MainActivity.this, Bookkeeping.class));

            // 增加過場動畫
            overridePendingTransition(android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right);
            MainActivity.this.finish();
        }
    };

    // -------------------------------------------------------------------------------------------

}
