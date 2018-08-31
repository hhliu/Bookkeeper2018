package cycu.nclab.moocs.bookkeeper2018;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tx;
    Button bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tx = findViewById(R.id.textView);
        bt = findViewById(R.id.button);

        tx.setOnClickListener(myClick);
        bt.setOnClickListener(myClick);
    }


    void clickButton(View v){
        tx.setTextColor(Color.BLUE);
    }


    private View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.textView:
                    tx.setText(R.string.textView_click);
                    tx.setTextColor(Color.RED);
                    break;
                case R.id.button:
                    tx.setText(R.string.button_click);
                    tx.setTextColor(Color.BLUE);
                    break;
            }

        }
    };
}
