package com.example.st1drawermenu.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.st1drawermenu.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab4Fragment extends Fragment {

    private ImageView btn_promotion2= null;
    private ImageView btn_promotion3= null;
    private ImageView btn_promotion4= null;
    private ImageView btn_promotion5= null;
    private ImageView btn_promotion6= null;
    private ImageView btn_promotion7= null;
    private ImageView btn_promotion8= null;



    public Tab4Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tab4, container, false);



        btn_promotion2 = view.findViewById(R.id.btn_promotion2);
        btn_promotion3 = view.findViewById(R.id.btn_promotion3);
        btn_promotion4 = view.findViewById(R.id.btn_promotion4);
        btn_promotion5 = view.findViewById(R.id.btn_promotion5);
        btn_promotion6 = view.findViewById(R.id.btn_promotion6);
        btn_promotion7 = view.findViewById(R.id.btn_promotion7);
        btn_promotion8 = view.findViewById(R.id.btn_promotion8);


        btn_promotion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "네이버로 이동합니다", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
                startActivity(i);
            }
        });


        btn_promotion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "유튜브로 이동합니다", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com"));
                startActivity(i);
            }
        });


        btn_promotion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "갤러리 사진파일로 이동합니다.", Toast.LENGTH_SHORT).show();
                String url = "content://media/internal/images/media";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });


        btn_promotion5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "응급전화 119로 이동합니다.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("tel://119"));
                startActivity(i);
            }
        });

        btn_promotion6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "카카오로 이동합니다.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.kakaocorp.com"));
                startActivity(i);
            }
        });

        btn_promotion7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "구글로 이동합니다.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(i);
            }
        });

        btn_promotion8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "기브커피로 이동합니다.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.givecoffeemall.com"));
                startActivity(i);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
