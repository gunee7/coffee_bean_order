package com.example.st1drawermenu.Cart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.content.Intent;
import android.widget.TextView;

import com.example.st1drawermenu.Fragment.Tab2.Tab2_Model_Card;
import com.example.st1drawermenu.MainActivity;
import com.example.st1drawermenu.NavPackage.Event.EventActivity;
import com.example.st1drawermenu.R;
import com.example.st1drawermenu.SubuMenu.QrcodeActivity;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ListView cartListview = null;
    private CartAdapter cartAdapter;
    private List<Tab2_Model_Card> data;
    List<Tab2_Model_Card> list = new ArrayList<>();
    List<Tab2_Model_Card> datalist;
    private ImageView btn_back;
    private ImageView btn_barket;
    private Button btn_menuadd;
    private Button btn_checking;
    private TextView textprice;
    private final String GO_COFFEE = "select intro";
    int total = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartListview = findViewById( R.id.cart_listview );

        Intent i = getIntent();
        list = i.getParcelableArrayListExtra("cart");

        data = MakeData( 0 , list.size()-1 );

        cartAdapter = new CartAdapter( CartActivity.this , R.layout.cart_custom, data);
        cartListview.setAdapter( cartAdapter );

        btn_back = findViewById( R.id.btn_back );
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_barket = findViewById( R.id.btn_barket );
        btn_barket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.clear();
                cartAdapter.notifyDataSetChanged();

            }
        });


        btn_menuadd = findViewById( R.id.btn_menuadd );
        btn_menuadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_checking = findViewById( R.id.btn_checking );
        btn_checking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( /* context */ CartActivity.this  ,  /* class 이름 */ QrcodeActivity.class);
                startActivity ( i );
            }
        });
        textprice = findViewById( R.id.textprice );
        textprice.setText( total + "");

        cartListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder( CartActivity.this );
                builder.setTitle("삭제하시겠습니까??");
                builder.setMessage("정말로??");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.remove( position );
                        cartAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("취소",null);
                builder.show();
                return false;
            }
        });

    }

    private List<Tab2_Model_Card> MakeData( int start , int end ) {

        Tab2_Model_Card item = new Tab2_Model_Card();
        datalist = new ArrayList<>();
        for ( int i = start ; i <= end ; i++ ){
            item = list.get( i );
            datalist.add( item );
            int pri = Integer.valueOf(item.getTextPrice());
            int cou = Integer.valueOf(item.getCountCoffee());
            int pay = pri * cou;
            total = total + pay;
        }


        return list;
    };


}
