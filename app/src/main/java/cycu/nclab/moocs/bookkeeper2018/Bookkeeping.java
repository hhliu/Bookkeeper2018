package cycu.nclab.moocs.bookkeeper2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Bookkeeping extends AppCompatActivity {

    final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookkeeping_constraint_layout);

        Log.i(TAG, "enter onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "enter onStart()");
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "enter onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "enter onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "enter onPause()");
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "enter onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "enter onRestart()");
    }
}
