package com.example.st1drawermenu.Fragment.Tab1;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.st1drawermenu.R;

import java.util.ArrayList;
import java.util.List;

public class Tab1Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    private View inflatedview1 = null;
    private Tab1_Adapter_Card tab1Adapter;
    private GridView tab1GridView;
    private List<Tab1_Model_Card> data1;
    private AlertDialog dialog;

    private int[] coffee_images = Tab1MenuButton.coffee_icon;


    private int getid1 = 0;
    private View dialogView;


    public Tab1Fragment() {

    }

    public static com.example.st1drawermenu.Fragment.Tab1.Tab1Fragment newInstance(String param1, String param2) {
        com.example.st1drawermenu.Fragment.Tab1.Tab1Fragment fragment = new com.example.st1drawermenu.Fragment.Tab1.Tab1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        inflatedview1 = inflater.inflate(R.layout.fragment_tab1, container, false);

        data1 = MakeData1(0, coffee_images.length - 1);

        tab1Adapter = new Tab1_Adapter_Card(getContext(), R.layout.fragment_tab1_custom, data1);
        tab1GridView = inflatedview1.findViewById(R.id.gridView1);
        tab1GridView.setAdapter(tab1Adapter);

        return inflatedview1;
    }


    @Override                    // 위젯 관련된 것은 이쪽에서
    public void onActivityCreated( @Nullable Bundle savedInstanceState ) {
        super.onActivityCreated( savedInstanceState );

        tab1GridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // intent 여기안에서 하기
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                dialogView = View.inflate(getContext(),R.layout.select_coffee_alert,null);

                ImageView image = dialogView.findViewById( R.id.menu_image );
                TextView  name  = dialogView.findViewById( R.id.coffee_text );
                TextView  price = dialogView.findViewById( R.id.coffeepayText );
                Tab1_Model_Card model = data1.get(position);
                image.setImageDrawable( model.getImageCoffee() );
                name.setText( model.getTextCoffee() );
                price.setText( model.getTextPrice() );


                builder.setView(dialogView);
                builder.setPositiveButton("주문표 넣기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("닫기",null);

                builder.show();

                /*Intent i = new Intent ( getContext(), SubMenuActivity.class);
                i.putExtra( "position" , position );
                i.putExtra( "name"     , getid1 );
                startActivity(i);*/
            }
        });
    }


    private List<Tab1_Model_Card> MakeData1( int start, int end ) {

        String[] coffeeName = getResources().getStringArray(R.array.menu_coffee_name);
        String[] coffeePrice = getResources().getStringArray(R.array.menupay_coffee);
        List<Tab1_Model_Card> list = new ArrayList<>();
        for(int i =start ; i<=end; i++){

            Tab1_Model_Card item = new Tab1_Model_Card();
            item.setImageCoffee(getResources().getDrawable(coffee_images[i]));
            item.setTextCoffee( coffeeName[i] );
            item.setTextPrice( coffeePrice[i] );

            list.add(item);
        }
        return list;
    }
}
