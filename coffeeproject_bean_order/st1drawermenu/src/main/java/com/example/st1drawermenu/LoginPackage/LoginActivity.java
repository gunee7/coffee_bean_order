package com.example.st1drawermenu.LoginPackage;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent; // 직접 타이핑

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.st1drawermenu.Intro.IntroMainActivity;
import com.example.st1drawermenu.MainActivity;
import com.example.st1drawermenu.R;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity  {

    // 위젯선언
    private ImageView btn_xmark;
    private AlertDialog dialog;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText)findViewById(R.id.passwordText);
        final Button loginButton = (Button) findViewById(R.id.loginButton);

        btn_xmark = (ImageView) findViewById(R.id.btn_xmark);
        btn_xmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( getApplicationContext()  ,  /* class 이름 */ MainActivity.class);
                startActivity(i); //새창 띄우기
            }
        });
        // 위젯 설정.
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userId = idText.getText().toString();
                String userPassword = passwordText.getText().toString();

                Response.Listener<String> responseLister = new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                dialog = builder.setMessage("로그인에 성공했습니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();

                                // Preference 객체 얻기. 파일이름은 Settings.xml
                                pref = getSharedPreferences( CommonCode.FILE_PREFERECE, MODE_PRIVATE);
                                // Preference에 id와 pw 저장.
                                SharedPreferences.Editor setter = pref.edit();
                                setter.putString( CommonCode.LOGIN_ID, idText.getText().toString());
                                //setter.putString("LOGIN_PW", edtPW.getText().toString());
                                setter.putBoolean( CommonCode.LOGIN_STATUS, true);
                                setter.apply(); // 저장.

                                //메인화면으로 정보를 보낸다.
                                // 결과 반환 코드 작성.
                                Intent data = new Intent();
                                // Intent 에 반환값 저장.
                                data.putExtra(CommonCode.LOGIN_STATUS, true);
                                // 결과반환.
                                setResult(RESULT_OK, data);

                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                LoginActivity.this.startActivity(i);
                                finish();


                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                dialog = builder.setMessage("계정을 다시 확인하세요.")
                                        .setNegativeButton("다시시도", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userId, userPassword, responseLister);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;

        }

    }
}

