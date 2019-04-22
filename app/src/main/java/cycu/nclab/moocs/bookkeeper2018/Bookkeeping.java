package cycu.nclab.moocs.bookkeeper2018;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Bookkeeping extends AppCompatActivity implements View.OnClickListener, DatePickerFragment.OnFragmentInteractionListener{

    final String TAG = this.getClass().getSimpleName();

    TextView mDate, mTime;
    Spinner mCategory;

    SimpleDateFormat dbFormat = new java.text.SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
    /*用來存在資料庫裏面的日期與時間格式*/

    SimpleDateFormat df_date = new java.text.SimpleDateFormat("yyyy/MM/dd",
            Locale.getDefault());
    /*用來顯示在手機畫面的日期格式*/
    SimpleDateFormat df_time_am = new java.text.SimpleDateFormat("hh a",
            Locale.US);
    /*用來顯示在手機畫面的時間格式*/

    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookkeeping_constraint_layout);

        Log.i(TAG, "enter onCreate()");
        uiInit();   // 把UI中跟 findViewById相關的整理在一起
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "enter onStart()");
        c = Calendar.getInstance(); // 取得目前日期與時間

        /*
        這行是一個隱式宣告，事實上是new了一個新的Calendar物件。
        關於c = Calendar.getInstance到底要寫在onCreate()還是onStart()各有優劣。
        寫在onCreate()裡面可以讓c的生命週期一直延續到onDestroy()，不需要去recheck c裡面的內容；

        寫在onStart()裡面則是每一次從背景被翻出來就產生一個新的物件，同時就需要對新的物件的內容(初值)
        做檢查與設定。同時這樣並沒有節省記憶體空間，如果我們沒有在onStop()主動釋放掉c的話，這個物件
        還是會被保留在記憶體中，然而在onStart()時建立新物件的時候，舊物件則被Garbage Collector回收。

        但是，在某幾次的經驗，如果APP被放到背景太久，OS有機會會去回收部分APP使用的資源，但又不回收整個
        APP。這會讓APP重新回到onStart()的時候c變成一個未定義的狀態而產生系統錯誤。因此我個人的習慣是
        在onStart()的時候重新整理所有的變數，檢查其數值是否正確。
         */
        setListener();  // 設定監聽器，其實看名字就知道功能，在clean code這本書裡面建議這樣做
        initVarable();  // 初始化變數
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "enter onStop()");
        releaseListener();  // 解除監聽器
        super.onStop();
    }

    private void uiInit() {
        mDate = this.findViewById(R.id.textView7);
        mTime = this.findViewById(R.id.textView8);
        mCategory = this.findViewById(R.id.spinner);
    }

    private void setListener() {
        mDate.setOnClickListener(this);
        mTime.setOnClickListener(this);
    }

    private void releaseListener() {
        mDate.setOnClickListener(null);
        mTime.setOnClickListener(null);
    }

    private void initVarable() {
        // 顯示目前時間
        mDate.setText(df_date.format(c.getTime()));
        mTime.setText(df_time_am.format(c.getTime()));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category, R.layout.my_simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.my_simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        mCategory.setAdapter(adapter);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView7:
                // on Date
                DialogFragment dateFragment = DatePickerFragment.newInstance(c);
                dateFragment.show(getSupportFragmentManager(), "datePicker");


                break;
            case R.id.textView8:
                // on Time
                DialogFragment timeFragment = TimePickerFragment.newInstance(c);
                timeFragment.show(getSupportFragmentManager(), "datePicker");
                break;
            case R.id.spinner:
                Log.d(TAG, "in spinner click");
//                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                        R.array.category, android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//                mCategory.setAdapter(adapter);
                break;
        }
    }

    @Override
    public void onDateSet(Calendar c) {
        mDate.setText(df_date.format(this.c.getTime()));
    }
}
