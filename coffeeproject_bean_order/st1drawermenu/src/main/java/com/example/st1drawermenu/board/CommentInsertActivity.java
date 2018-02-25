package com.example.st1drawermenu.board;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.st1drawermenu.R;

public class CommentInsertActivity extends AppCompatActivity {

    private EditText edt_nick;
    private EditText edt_memo;
    private Button   btn_comment_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_insert);

        Intent i = getIntent();
        final int articleno = i.getIntExtra("articleno", 0);

        edt_nick = findViewById( R.id.edt_nick);
        edt_memo = findViewById( R.id.edt_memo);
        btn_comment_insert = findViewById( R.id.btn_comment_insert);

        btn_comment_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nick = edt_nick.getText().toString();
                String memo = edt_memo.getText().toString();
                if (nick.isEmpty()){
                    nick = "이름없음";
                }
                ModelComments comment = new ModelComments();
                comment.setArticleno( articleno );
                comment.setEmail( nick );
                comment.setMemo( memo );

                new CommentInsertTask(CommentInsertActivity.this).execute( comment );

            }
        });
    }
    private class CommentInsertTask extends SiteAsyncTask<ModelComments, Integer, Integer> {

        public CommentInsertTask(Context context) {
            super(context);
        }
        @Override
        protected Integer doInBackground(ModelComments... modelComments) {

            Integer result = new HttpBoard().insertComment( modelComments[0] );
            return result;
        }

        @Override
        protected void onPostExecute(Integer check) {
            super.onPostExecute(check);

            if (check >= 1 ){
                Toast.makeText(getApplicationContext(), "입력 완료", Toast.LENGTH_SHORT).show();
                finish();
            }
            else{
                Toast.makeText(getApplicationContext(), "??????", Toast.LENGTH_SHORT).show();
            }
        }
    }
    }
