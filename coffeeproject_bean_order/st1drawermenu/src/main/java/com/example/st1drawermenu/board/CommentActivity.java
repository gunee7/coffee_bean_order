package com.example.st1drawermenu.board;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.st1drawermenu.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentActivity extends AppCompatActivity {

    public static final String ARG_ARTICLENO = "arcticleno";
    private static final String ARG_ARTICLE  = "arcicle";
    private static final String ARG_COMMENTS = "commentlist";

    // 위젯 선언
    private TextView mNo;
    private TextView mTitle;
    private TextView mHit;
    private TextView mContent;
    private TextView mComment;
    private ListView mListview;

    private AdapterComment mAdapter;
    int articleno = 0;

    // http 통신으로 받은 정보 저장 필드
    ModelArticle mArticle;
    List<ModelComments> mDataComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Intent i = getIntent();
        articleno = i.getIntExtra(ARG_ARTICLENO, 0);

        new CommentTask(CommentActivity.this).execute( articleno );
    }

    private class CommentTask extends SiteAsyncTask< Integer, Integer, Map<String, Object>> {

        public CommentTask(Context context) {
            super(context);
        }

        @Override
        protected Map<String, Object> doInBackground(Integer... integers) {

            HttpBoard http = new HttpBoard();
            ModelArticle article = http.getArticle(integers[0]);
            List<ModelComments> comments = http.getCommentList(integers[0]);

            Map<String, Object> map = new HashMap<>();

            map.put(ARG_ARTICLE, article);
            map.put(ARG_COMMENTS, comments);

            return map;
        }

        @Override
        protected void onPostExecute(Map<String, Object> map) {
            super.onPostExecute(map);

            //
            mArticle = (ModelArticle) map.get(ARG_ARTICLE);
            mDataComments = (List<ModelComments>) map.get(ARG_COMMENTS);

            // 위젯 찾기
            mNo = findViewById(R.id.no);
            mHit = findViewById(R.id.hit);
            mTitle = findViewById(R.id.title);
            mContent = findViewById(R.id.content);
            mListview = findViewById(R.id.listview_comment);
            mComment = findViewById( R.id.insertcomment);

            // 위젯 설정( 리스너)
            mNo.setText(mArticle.getArticleno() + "");
            mHit.setText(mArticle.getHit() + "");
            mTitle.setText(mArticle.getTitle());
            mContent.setText(mArticle.getContent());

            // Activity 타이틀 설정.
            setTitle(mArticle.getTitle());

            // adapter 생성
            mAdapter = new AdapterComment(this.mContext, R.layout.activity_comment, mDataComments);

            // adapter 연결
            mListview.setAdapter(mAdapter);

            mComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent gocomment = new Intent( CommentActivity.this, CommentInsertActivity.class);
                    gocomment.putExtra("articleno",articleno);
                    startActivity( gocomment );
                }
            });

        }
    }
}
