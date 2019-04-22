package cycu.nclab.moocs.bookkeeper2018;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CuteDog extends AppCompatActivity {

    ImageView iv;
    private final int IMAGE_ONE = 1001;
    private final int IMAGE_TWO = 1002;
    private final int IMAGE_THREE = 1003;
    private final int interval = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cute_dog);

        uiInit();
    }

    private void uiInit() {
        iv = findViewById(R.id.imageView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Message msg = myHandler.obtainMessage();
        msg.what = IMAGE_TWO;
        myHandler.sendMessageDelayed(msg, 500); // 0.5秒後執行
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private Handler myHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IMAGE_ONE:
                    iv.setImageResource(R.drawable.ic_dog_rotate_right_1);
                    msg = this.obtainMessage();
                    msg.what = IMAGE_TWO;
                    this.sendMessageDelayed(msg, interval);
                    break;
                case IMAGE_TWO:
                    iv.setImageResource(R.drawable.ic_dog_rotate_right_2);
                    msg = this.obtainMessage();
                    msg.what = IMAGE_THREE;
                    this.sendMessageDelayed(msg, interval);
                    break;
                case IMAGE_THREE:
                    iv.setImageResource(R.drawable.ic_dog_rotate_right_3);
                    msg = this.obtainMessage();
                    msg.what = IMAGE_ONE;
                    this.sendMessageDelayed(msg, interval);
                    break;
            }
        }
    };

}
