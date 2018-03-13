package firebase.example.com.calcul;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CalculratorActivity extends BaseActivity {

    private android.widget.TextView resulttxt;
    private android.widget.Button btn01;
    private android.widget.Button btn02;
    private android.widget.Button btn03;
    private android.widget.Button btn04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setValues();
        setupEvents();

    }



    @Override
    public void bindViews() {
        this.btn04 = (Button) findViewById(R.id.btn04);
        this.btn03 = (Button) findViewById(R.id.btn03);
        this.btn02 = (Button) findViewById(R.id.btn02);
        this.btn01 = (Button) findViewById(R.id.btn01);
        this.resulttxt = (TextView) findViewById(R.id.resulttxt);
    }

    @Override
    public void setupEvents() {

        View.OnClickListener numberEvent = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 숫자 버튼(0~9)이 눌리면 발생되어야 하는 일
                Button b = (Button) view;
                Toast.makeText(mContext, b.getText()+"버튼눌림", Toast.LENGTH_SHORT).show();

                int inputVal = Integer.parseInt(b.getText().toString());

                int currentVal = Integer.parseInt(resulttxt.getText().toString());

                int result = currentVal * 10 + inputVal;
                resulttxt.setText(String.valueOf(result));
            }
        };




        btn01.setOnClickListener(numberEvent);
        btn02.setOnClickListener(numberEvent);
        btn03.setOnClickListener(numberEvent);
        btn04.setOnClickListener(numberEvent);




    }

    @Override
    public void setValues() {

    }
}