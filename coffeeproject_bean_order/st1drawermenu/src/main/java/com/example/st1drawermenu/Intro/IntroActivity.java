package com.example.st1drawermenu.Intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.st1drawermenu.LoginPackage.LoginActivity;
import com.example.st1drawermenu.MainActivity;
import com.example.st1drawermenu.R;

/**
 * Created by Administrator on 2018-01-31.
 */

public class IntroActivity extends AppCompatActivity {

    private ImageView intro_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        intro_image = findViewById(R.id.intro_image);


        intro_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "give coffee에 오신걸 환영합니다", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i); //새창 띄우기

            }
        });







    }
}