package com.example.st1drawermenu.AppBar;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.st1drawermenu.Fragment.Tab2.Tab2Fragment;
import com.example.st1drawermenu.Fragment.Tab1.Tab1Fragment;
import com.example.st1drawermenu.board.Tab3.Tab3Fragment;
import com.example.st1drawermenu.Fragment.Tab4Fragment;
import com.example.st1drawermenu.R;

public class AppBarMainActivity extends AppCompatActivity {

    private ViewPager pager;
    private TabLayout tabLayout;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);


//        setSupportActionBar(toolbar);
        pager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("커피"));
        tabLayout.addTab(tabLayout.newTab().setText("라떼"));
        tabLayout.addTab(tabLayout.newTab().setText("음료"));
        tabLayout.addTab(tabLayout.newTab().setText("녹차"));


        //ViewPager설정
        pager = findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        pager.setAdapter(adapter);

        //ViewPager의 PagerChange와 TabLayout연동 설정
        //ViewPager를 스크롤하면 Tab도 같이 바뀌게 하는 설정.

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //TabLayout에서 Tab를 클릭하면
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    static class PagerAdapter extends FragmentPagerAdapter {

        private int tabCount ;
        public PagerAdapter(FragmentManager fm, int numberOfTabs) {
            super(fm);

            this.tabCount = numberOfTabs;
        }


        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new Tab1Fragment();
                case 1:
                    return new Tab2Fragment();
                case 2:
                    return new Tab3Fragment();
                case 3:
                    return new Tab4Fragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return this.tabCount;
        }
    }
}
