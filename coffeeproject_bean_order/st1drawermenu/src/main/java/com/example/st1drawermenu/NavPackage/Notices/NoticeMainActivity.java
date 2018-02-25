package com.example.st1drawermenu.NavPackage.Notices;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.st1drawermenu.MainActivity;
import com.example.st1drawermenu.NavPackage.Event.EventActivity;
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

public class NoticeMainActivity extends AppCompatActivity {

    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;
    private ImageView btn_back;
    private LinearLayout layout_back;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_main);

        Intent clickbutton = getIntent();
        String click = clickbutton.getStringExtra("scheduleButton");

        btn_back = findViewById( R.id.btn_back );
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( /* context */ NoticeMainActivity.this  ,  /* class 이름 */ MainActivity.class);
                startActivity ( i );
            }
        });

        layout_back = findViewById( R.id.layout_back );
        layout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( /* context */ NoticeMainActivity.this  ,  /* class 이름 */ MainActivity.class);
                startActivity ( i );
            }
        });


        noticeListView = (ListView) findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();
        adapter = new NoticeListAdapter(getApplicationContext(), noticeList);
        noticeListView.setAdapter(adapter);

        final Button scheduleButton = findViewById(R.id.scheduleButton);
        final Button courseButton = findViewById(R.id.courseButton);
        final Button statisticsButton = findViewById(R.id.statisticsButton);
        final LinearLayout notice = findViewById(R.id.notice);

        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.VISIBLE);
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.bg_screen10));
                courseButton.setBackgroundColor(getResources().getColor(R.color.bg_screen1));
                statisticsButton.setBackgroundColor(getResources().getColor(R.color.bg_screen1));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new ScheduleFragment());
                fragmentTransaction.commit();
            }
        });

        courseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.bg_screen1));
                courseButton.setBackgroundColor(getResources().getColor(R.color.bg_screen10));
                statisticsButton.setBackgroundColor(getResources().getColor(R.color.bg_screen1));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new CourseFragment());
                fragmentTransaction.commit();
            }
        });

        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.bg_screen1));
                courseButton.setBackgroundColor(getResources().getColor(R.color.bg_screen1));
                statisticsButton.setBackgroundColor(getResources().getColor(R.color.bg_screen10));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new StatisticsFragment());
                fragmentTransaction.commit();
            }
        });


        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "http://givetest.cafe24.com/usermain/NoticeList.php";
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
                String noticeContent, noticeName, noticeDate;
                while (count < jsonArray.length()){

                    JSONObject object = jsonArray.getJSONObject(count);
                    noticeContent = object.getString("noticeContent");
                    noticeName = object.getString("noticeName");
                    noticeDate = object.getString("noticeDate");
                    Notice notice = new Notice(noticeContent, noticeName, noticeDate);
                    noticeList.add(notice);
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



