package firebase.example.com.calcul;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LottoActivity extends BaseActivity {

    private android.widget.TextView moneyTxt;
    private android.widget.TextView lottoNumTxt01;
    private android.widget.TextView lottoNumTxt02;
    private android.widget.TextView lottoNumTxt03;
    private android.widget.TextView lottoNumTxt04;
    private android.widget.TextView lottoNumTxt05;
    private android.widget.TextView lottoNumTxt06;
    private android.widget.TextView amountTxt;
    private android.widget.Button startBtn;

    long myMoney = 10000000; //초기 자본금 천만원.
    int[] myLottoNumArray = {6, 10, 15, 25, 32, 38};  //내가 찍은 번호
    long earnMoney = 0; // 처음에는 번 돈은 없다.

    int firstCount =0;
    int secondCount =0;
    int thirdCount =0;
    int forthCount =0;
    int fiveCount =0;


    private TextView lottoNumTxt07;
    private TextView lottoBonusNumTxt;
    private TextView firstPlaceCount;
    private TextView secondPlaceCount;
    private TextView thirdPlaceCount;
    private TextView forthPlaceCount;
    private TextView fivePlaceCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotto);

        bindViews();
        setupEvents();
        setValues();

    }


    @Override
    public void setupEvents() {
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Handler().post(buyLottoRun);
            }
        });

    }


    Runnable buyLottoRun = new Runnable() {
        @Override
        public void run() {
            // 돈 1천원을 씀
            if (myMoney > 0) {
                myMoney -= 1000;

                moneyTxt.setText(String.format("%,d", myMoney));

                //로또 번호 생성 => 몇등인지 판정 => 얼마를 벌었는지 표시.
                //1.로또 번호 6개 생성.
                int[] lottoArray = new int[6]; //생성된 숫자들을 저장할 배열.
                //2.보너스 번호 생성
                int bonusNum = 0;
                // Math.random활용
                //규칙 ` 1~45. 중복번호 허용x

                for (int i = 0; i < 7; i++) {
                    //무한루프. =>조건에 맞는 숫자가 나올때까지 무한반복 =>중복된 숫자면 다시 뽑으려고
                    while (true) {
                        int tempNum = (int) (Math.random() * 45 + 1); // 1~45의 숫자중 하나를 뽑음

                        boolean isNumOk = true; // 지금 뽑은 숫자를 써도 되는가? 기본값 OK

                        for (int j = 0; j < 6; j++) {
                            if (lottoArray[j] == tempNum) {
                                isNumOk = false; // 이미 ㅊ첨된 번호라면 사용하면 안된다.
                            }
                        }

                        if (isNumOk) {  // 한번도 중복 경우 x = 추첨번호로 사용해도 좋다.
                            if (i < 6) { // 0~5;정규로또 번호.
                                lottoArray[i] = tempNum;
                            } else { // i == 6 : 보너스 번호
                                bonusNum = tempNum;
                            }
                            break;
                        }


                    }
                }

                // 1등:6개=>1,000,000,000,
                // 2등:
                // 3등:5개=>50,000,000,
                // 4등:4개=>5,000,000,
                // 5등:3개=>50,000,

                int correctCount = 0; // 맞춘 숫자의 갯수 기록록

                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        if (myLottoNumArray[i] == lottoArray[j]) {
                            correctCount++;
                        }
                    }
                }


                if (correctCount == 3) { // 5등 당첨.
                    // 내가 번 돈을 5000원 증가.
                    earnMoney += 5000;
                    fiveCount++;

                } else if (correctCount == 4) {
                    earnMoney += 50000;
                    forthCount++;

                } else if (correctCount == 5) {
                    thirdCount++;

                    boolean is2ndPlace = false; // 2등은 일단 아닌걸로 전제.
                    //보너스번호를 내가 찍었는지?
                    for(int i = 0 ; i <6 ; i++) {
                        if (myLottoNumArray[i] == bonusNum) {
                            is2ndPlace = true; // 내가 보너스 번호를 찍음!
                        }
                    }
                    if (is2ndPlace) {
                        //2등 :4천만원
                        earnMoney += 40000000;
                        secondCount++;
                    } else {
                        // 3등 5백만원
                    }
                    earnMoney += 1500000;
                    thirdCount++;

                } else if (correctCount == 6) {
                    earnMoney += 1000000000;
                    firstCount++;
                }


                //이번주 당첨번호를 출력
                lottoNumTxt01.setText(String.valueOf(lottoArray[0]));
                lottoNumTxt02.setText(String.valueOf(lottoArray[1]));
                lottoNumTxt03.setText(String.valueOf(lottoArray[2]));
                lottoNumTxt04.setText(String.valueOf(lottoArray[3]));
                lottoNumTxt05.setText(String.valueOf(lottoArray[4]));
                lottoNumTxt06.setText(String.valueOf(lottoArray[5]));
                lottoBonusNumTxt.setText(String.valueOf(bonusNum));


                //번돈도 출력
                amountTxt.setText(String.format("%,d원 획득!", earnMoney));

                firstPlaceCount.setText(String.format("1등 : %d회", firstCount));
                secondPlaceCount.setText(String.format("2등 : %d회", secondCount));
                thirdPlaceCount.setText(String.format("3등 : %d회", thirdCount));
                forthPlaceCount.setText(String.format("4등 : %d회", forthCount));
                fivePlaceCount.setText(String.format("5등 : %d회", fiveCount));


                new Handler().post(buyLottoRun);
            }

        }
    };


    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.startBtn = (Button) findViewById(R.id.startBtn);
        this.fivePlaceCount = (TextView) findViewById(R.id.fivePlaceCount);
        this.forthPlaceCount = (TextView) findViewById(R.id.forthPlaceCount);
        this.thirdPlaceCount = (TextView) findViewById(R.id.thirdPlaceCount);
        this.secondPlaceCount = (TextView) findViewById(R.id.secondPlaceCount);
        this.firstPlaceCount = (TextView) findViewById(R.id.firstPlaceCount);
        this.amountTxt = (TextView) findViewById(R.id.amountTxt);
        this.lottoBonusNumTxt = (TextView) findViewById(R.id.lottoBonusNumTxt);
        this.lottoNumTxt07 = (TextView) findViewById(R.id.lottoNumTxt07);
        this.lottoNumTxt06 = (TextView) findViewById(R.id.lottoNumTxt06);
        this.lottoNumTxt05 = (TextView) findViewById(R.id.lottoNumTxt05);
        this.lottoNumTxt04 = (TextView) findViewById(R.id.lottoNumTxt04);
        this.lottoNumTxt03 = (TextView) findViewById(R.id.lottoNumTxt03);
        this.lottoNumTxt02 = (TextView) findViewById(R.id.lottoNumTxt02);
        this.lottoNumTxt01 = (TextView) findViewById(R.id.lottoNumTxt01);
        this.moneyTxt = (TextView) findViewById(R.id.moneyTxt);



    }


}


