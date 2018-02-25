package com.example.st1drawermenu.SubuMenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.st1drawermenu.MainActivity;
import com.example.st1drawermenu.NavPackage.Notices.NoticeMainActivity;
import com.example.st1drawermenu.R;

public class QrcodeActivity extends AppCompatActivity {



    private ImageView btn_xmark2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

     btn_xmark2 = findViewById( R.id.btn_xmark2          );

        btn_xmark2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
