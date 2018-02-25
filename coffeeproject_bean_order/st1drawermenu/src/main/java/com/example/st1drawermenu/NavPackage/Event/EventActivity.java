package com.example.st1drawermenu.NavPackage.Event;

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

import com.example.st1drawermenu.Cart.CartActivity;
import com.example.st1drawermenu.MainActivity;
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

public class EventActivity extends AppCompatActivity {

    private ImageView btn_back;
    private ListView eventListView;
    private EventListAdapter adapter;
    private List<Event> eventList;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        eventListView = (ListView) findViewById(R.id.eventListView);
        eventList = new ArrayList<Event>();
        adapter = new EventListAdapter(getApplicationContext(), eventList);
        eventListView.setAdapter(adapter);

        final LinearLayout event = findViewById(R.id.event);
        btn_back = findViewById( R.id.btn_back );
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( /* context */ EventActivity.this  ,  /* class 이름 */ MainActivity.class);
                startActivity ( i );
            }
        });

        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "http://givetest.cafe24.com/usermain/EventList.php";
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
                String eventContent, eventName, eventDate;
                while (count < jsonArray.length()){

                    JSONObject object = jsonArray.getJSONObject(count);
                    eventContent = object.getString("eventContent");
                    eventName = object.getString("eventName");
                    eventDate = object.getString("eventDate");
                    Event event = new Event(eventContent, eventName, eventDate);
                    eventList.add(event);
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



