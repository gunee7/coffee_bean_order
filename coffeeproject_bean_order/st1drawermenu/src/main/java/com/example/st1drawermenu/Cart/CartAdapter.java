package com.example.st1drawermenu.Cart;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.st1drawermenu.Fragment.Tab2.Tab2_Model_Card;
import com.example.st1drawermenu.R;

import java.util.List;

public class CartAdapter extends ArrayAdapter<Tab2_Model_Card>{

    public  Context context            = null;
    private ViewHolder holder         = null;
    private LayoutInflater inflater  = null;
    private View inflaterView;

    public CartAdapter(@NonNull Context context, int resource,@NonNull List<Tab2_Model_Card> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    private class ViewHolder {

        private TextView  coffeeName  ;
        private TextView  coffePrice  ;
        private TextView  coffeeCount ;
        private ImageView coffeeImage  ;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        inflater = LayoutInflater.from( context );
        inflaterView = inflater.inflate( R.layout.cart_custom,parent,false);

        holder = ( ViewHolder ) inflaterView.getTag();

        if ( holder == null ){

            holder = new ViewHolder();

            holder.coffeeName  =  inflaterView.findViewById( R.id.cart_name  );
            holder.coffePrice  =  inflaterView.findViewById( R.id.cart_price );
            holder.coffeeCount  = inflaterView.findViewById( R.id.cart_count );
            holder.coffeeImage  = inflaterView.findViewById( R.id.cart_image );

            inflaterView.setTag( holder );
        }

        holder.coffeeName   .setText          (this.getItem(position).getTextCoffee() );
        holder.coffePrice   .setText          (this.getItem(position).getTextPrice()  );
        holder.coffeeCount  .setText          (this.getItem(position).getCountCoffee());
        holder.coffeeImage  .setImageDrawable (context.getResources().getDrawable(this.getItem(position).getImageCoffee(),null));

        return inflaterView;

    }
}
