package firebase.example.com.calcul;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by Administrator on 2018-03-13.
 */

public abstract class BaseActivity extends AppCompatActivity {

    Context mContext = this;

    public abstract void bindViews(); // 클릭 등의 이벤트 처리 담당
    public abstract void setupEvents(); // 상황에 따른 데이터를 표시 용도 (내정보 => 조경진에 대한 정보)
    public abstract void setValues();  // findViewID를 치워두는 용도

    // 추상화
    //1.클래스
    //-> 객체와 되는것을 방지 (동물,인간등 대분류)
    //2.메소드
        // --> 상속받은 자식 클래스가 반드시 구현해야함.(의무부여)->빼먹으면 안되는 것들


}
