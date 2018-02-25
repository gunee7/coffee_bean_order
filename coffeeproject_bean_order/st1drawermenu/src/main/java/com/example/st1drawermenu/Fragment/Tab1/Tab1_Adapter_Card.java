package com.example.st1drawermenu.Fragment.Tab1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;


import com.example.st1drawermenu.R;

import java.util.List;

public class Tab1_Adapter_Card extends ArrayAdapter<Tab1_Model_Card> {

    public  Context context            = null;
    private ViewHolder holder         = null;
    private LayoutInflater inflater  = null;
    private View inflaterView;

    public Tab1_Adapter_Card(@NonNull Context context, int resource, @NonNull List<Tab1_Model_Card> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    private class ViewHolder {

        private ImageView imageCoffeeView;
        private TextView  textCoffeeView ;
        private TextView  textPriceView  ;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        inflater = LayoutInflater.from( context );
        inflaterView = inflater.inflate( R.layout.fragment_tab1_custom, parent , false );

        holder = ( ViewHolder ) inflaterView.getTag();

        if ( holder == null ){

            holder = new ViewHolder();

            holder.imageCoffeeView = inflaterView.findViewById( R.id.image_coffee );
            holder.textCoffeeView  = inflaterView.findViewById( R.id.text_coffee  );
            holder.textPriceView   = inflaterView.findViewById( R.id.text_coffee_price  );

            inflaterView.setTag( holder );
        }

        holder.imageCoffeeView  .setImageDrawable(this.getItem(position).getImageCoffee());
        holder.textCoffeeView   .setText         (this.getItem(position).getTextCoffee() );
        holder.textPriceView    .setText         (this.getItem(position).getTextPrice()  );

        return inflaterView;
    }



}
