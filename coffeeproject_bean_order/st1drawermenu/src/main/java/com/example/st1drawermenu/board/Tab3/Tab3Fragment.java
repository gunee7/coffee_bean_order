package com.example.st1drawermenu.board.Tab3;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.st1drawermenu.R;
import com.example.st1drawermenu.board.CommentActivity;
import com.example.st1drawermenu.board.HttpBoard;
import com.example.st1drawermenu.board.ModelArticle;
import com.example.st1drawermenu.board.ModelBoard;
import com.example.st1drawermenu.board.SiteAsyncTask;

import java.util.ArrayList;
import java.util.List;

public class Tab3Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private static final String ARG_PARAM1 = "param1";


    private String mParam1;
    private ModelBoard mBoard;
    private String  mSearchWord = "" ; // ToolBar에서 넘겨 받는값.

    private List<ModelArticle> mData = null;
    private ListView     mListView = null;
    private ArrayAdapter<ModelArticle> mAdapter = null;

    private View inflatedView = null;
    private boolean flagGetData = false;
    private boolean mRetry = true ;
    SwipeRefreshLayout mSwipeRefreshLayout = null;


    public Tab3Fragment() {

    }
    public static Tab3Fragment newInstance(String param1) {
        Tab3Fragment fragment = new Tab3Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBoard = getArguments().getParcelable(ARG_PARAM1);
        }
        mData = new ArrayList<>();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflatedView = inflater.inflate(R.layout.fragment_tab3, container, false);
        return inflatedView;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //SwipeRefreshLayout
        mSwipeRefreshLayout = inflatedView.findViewById( R.id.swipe_layout );
        mSwipeRefreshLayout.setOnRefreshListener(this);

        new HttpGetData(getContext()).execute(mData.size(), 20);
    }
    private void setOnCreate() {

        // 위젯 찾기
        mListView = inflatedView.findViewById( R.id.article_listview );

        // adapter 생성
        mAdapter = new Tab3_Adapter( getContext(), R.layout.fragment_tab3_custom, mData);

        // adapter 연결
        mListView.setAdapter( mAdapter );

        // 클릭시 디테일 화면으로
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ArticleDetailActivity 로 출력하기
                ModelArticle item = mData.get( position );

                android.content.Intent i = new android.content.Intent( getContext(), CommentActivity.class );
                i.putExtra( CommentActivity.ARG_ARTICLENO, item.getArticleno() );
                startActivity( i );
            }
        });

        // adapter scroll 리스너 설정
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if( totalItemCount == firstVisibleItem + visibleItemCount ){
                    // 바닥이다.
                    // 데이터 추가.
                    if( flagGetData == false  && mRetry ) {
                        flagGetData = true;

                        // // 1. 네트워크를 통해 데이터 요청
                        // List<ModelItem> items = null;
                        // items = makeData(lists.size(), 20);
                        //
                        // // 2. 통신 완료 후 받은 데이터 작업
                        // lists.addAll( items );
                        // adapter.notifyDataSetChanged();
                        // flagGetData = false;

                        new HttpGetData( getContext() ).execute(mData.size(), 20);
                    }
                }
            }
        });
    }

    @Override
    public void onRefresh() {

        mData.clear();
        new HttpGetData( getContext() ).execute(mData.size(), 20);

        mSwipeRefreshLayout.setRefreshing(false);
    }

    private class HttpGetData extends SiteAsyncTask< Integer,  Integer, List<ModelArticle>  > {

        public HttpGetData(Context context) {
            super(context);
        }

        // 요청 중.
        @Override
        protected List<ModelArticle> doInBackground(Integer... integers) {

            List<ModelArticle> result = new HttpBoard().getArticleList(   "free"
                    , mSearchWord
                    , integers[0] /* 시작값 */
                    , integers[0] + integers[1] );

            return result;
        }

        // 요청 후. 네트워크로 데이터 요청이 완료되었을 때 실행되는 메서드
        // 사용자에게 요청을 알림 제거. ProgreesDialog 제거.
        @Override
        protected void onPostExecute(List<ModelArticle> articles) {
            super.onPostExecute(articles);

            // 2. 통신 완료 후 받은 데이터 작업

            // 3. Activity 화면 설정
            if( mData.size() == 0 ) {
                mData.addAll( articles );
                setOnCreate(); // 한번만 실행되어야 한다.
            }
            else {
                mData.addAll( articles );
                mAdapter.notifyDataSetChanged();
            }

            // 재시도 여부 설정
            if(articles.size() > 0)
                mRetry = true;
            else
                mRetry = false;


            flagGetData = false;
        }
    }
}





