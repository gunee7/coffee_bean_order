package com.example.st1drawermenu.NavPackage.Coupon;

import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.st1drawermenu.NavPackage.Notices.CourseFragment;
import com.example.st1drawermenu.NavPackage.Notices.ScheduleFragment;
import com.example.st1drawermenu.NavPackage.Notices.StatisticsFragment;
import com.example.st1drawermenu.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CouponActivity extends AppCompatActivity {

    private ListView couponListView;
    private CouponListAdapter adapter;
    private List<Coupon> couponList;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        couponListView = (ListView) findViewById(R.id.couponListView);
        couponList = new ArrayList<Coupon>();
        adapter = new CouponListAdapter(getApplicationContext(), couponList);
        couponListView.setAdapter(adapter);

        final LinearLayout coupon = findViewById(R.id.coupon);


        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "http://givetest.cafe24.com/usermain/CouponList.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "Wn");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return  stringBuilder.toString().trim();


            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String couponContent, couponName, couponDate;
                while (count < jsonArray.length()){

                    JSONObject object = jsonArray.getJSONObject(count);
                    couponContent = object.getString("couponContent");
                    couponName = object.getString("couponName");
                    couponDate = object.getString("couponDate");
                    Coupon coupon = new Coupon(couponContent, couponName, couponDate);
                    couponList.add(coupon);
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private long lastTimeBackPressed;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastTimeBackPressed < 1500) {
            finish();
            return;
        }
        Toast.makeText(this, "'뒤로' 버튼을 한번 더 눌러 종료합니다.", Toast.LENGTH_SHORT);
        lastTimeBackPressed = System.currentTimeMillis();

    }


}



