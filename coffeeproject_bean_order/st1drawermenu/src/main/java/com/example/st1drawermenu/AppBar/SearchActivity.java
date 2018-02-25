package com.example.st1drawermenu.AppBar;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.st1drawermenu.R;
import com.example.st1drawermenu.board.HttpBoard;
import com.example.st1drawermenu.board.ModelArticle;
import com.example.st1drawermenu.board.SiteAsyncTask;

public class SearchActivity extends AppCompatActivity {

    private EditText edt_title   ;
    private EditText edt_content;
    private Button   btn_summit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        edt_title     = findViewById( R.id.edt_Title   );
        edt_content   = findViewById( R.id.edt_content);
        btn_summit    = findViewById( R.id.btn_summit );

        btn_summit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edt_title.getText().toString();
                String content = edt_content.getText().toString();

                ModelArticle article = new ModelArticle();
                article.setTitle( title );
                article.setContent( content );
                article.setBoardcd( "free");

                new insertArticleTask(SearchActivity.this).execute( article );
            }
        });

    }
    private class insertArticleTask extends SiteAsyncTask< ModelArticle, Integer, Integer > {

        public insertArticleTask(Context context) {
            super(context);
        }
        @Override
        protected Integer doInBackground(ModelArticle... modelArticles) {

            Integer result = new HttpBoard().insertArticle( modelArticles[0] );

            return result;
        }

        @Override
        protected void onPostExecute(Integer check) {
            super.onPostExecute(check);

            if (check >= 1){
                Toast.makeText(SearchActivity.this, "글쓰기 완료", Toast.LENGTH_SHORT).show();
                finish();
            }
            else {
                Toast.makeText(SearchActivity.this, "다시 입력해 주세요", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
