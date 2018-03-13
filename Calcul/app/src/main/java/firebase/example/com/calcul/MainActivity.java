package firebase.example.com.calcul;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    private android.widget.TextView resulttxt;
    private android.widget.Button btn01;
    private android.widget.Button btn02;
    private android.widget.Button btn03;
    private android.widget.Button btnPlus;

    String operator = ""; // 계산해야하는 연산자 저장. +_*/
    boolean needToReset = true; // true면 처음부터 숫자입력, false명 뒤에 이어붙이기.
    int calculNum = 0; // 같이 계산 되어야 하는 값을 저장.


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
        this.btnPlus = (Button) findViewById(R.id.btnPlus);
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

                int result ;


                //계산 해야하는 숫자가 0. ->아직 +를 누르지 않은 상태
                if(needToReset){
                    result = inputVal;
                    needToReset=false;
                }
                else {
                    result = currentVal * 10 + inputVal;
                }
                resulttxt.setText(String.valueOf(result));
            }
        };




        btn01.setOnClickListener(numberEvent);
        btn02.setOnClickListener(numberEvent);
        btn03.setOnClickListener(numberEvent);


        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                operator = "+"; // 이번 연산자는 +임을 기록

                needToReset = true; // +버튼 이후의 숫자는 기존 값을 날려야 한다

                int result = calculNum + Integer.parseInt(resulttxt.getText().toString());//현재 타이핑 된 값 + 기존에 저장한 값 => 화면 출력


                resulttxt.setText(String.valueOf(result));
                
                calculNum = Integer.parseInt(resulttxt.getText().toString());


            }
        });



            //rP

    }

    @Override
    public void setValues() {

    }
}