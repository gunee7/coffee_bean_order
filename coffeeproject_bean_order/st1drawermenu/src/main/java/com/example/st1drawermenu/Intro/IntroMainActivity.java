package com.example.st1drawermenu.Intro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.st1drawermenu.LoginPackage.LoginActivity;
import com.example.st1drawermenu.MainActivity;
import com.example.st1drawermenu.R;

public class IntroMainActivity extends AppCompatActivity {

    private ImageView intro_bean;
    private ImageView intro_takeout;
    private ImageView intro_syrupsause;
    private ImageView intro_desert;

    private final String GO_COFFEE = "select intro";


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_main);

        intro_bean     = findViewById(R.id.intro_bean    );
        intro_bean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = GoMainActivity();
                i.putExtra(GO_COFFEE,1);
                startActivity(i); //새창 띄우기
                finish();
            }
        });

        intro_takeout   = findViewById(R.id.intro_takeout );
        intro_takeout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = GoMainActivity();
                i.putExtra(GO_COFFEE,2);
                startActivity(i); //새창 띄우기
                finish();
            }
        });

        intro_syrupsause = findViewById(R.id.intro_syrupsause);
        intro_syrupsause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = GoMainActivity();
                i.putExtra(GO_COFFEE,3);
                startActivity(i); //새창 띄우기
                finish();
            }
        });

        intro_desert   = findViewById(R.id.intro_desert  );
        intro_desert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = GoMainActivity();
                i.putExtra(GO_COFFEE,3);
                startActivity(i); //새창 띄우기
                finish();
            }
        });

    }

    @NonNull
    private Intent GoMainActivity() {
        Toast.makeText(getApplicationContext(), "점주님 환영합니다.", Toast.LENGTH_SHORT).show();
        return new Intent(getApplicationContext(), LoginActivity.class);
    }
}







