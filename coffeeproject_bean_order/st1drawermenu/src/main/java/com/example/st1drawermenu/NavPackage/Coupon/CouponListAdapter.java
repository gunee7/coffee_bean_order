package com.example.st1drawermenu.NavPackage.Coupon;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.st1drawermenu.R;

import java.util.List;

/**
 * Created by Administrator on 2018-02-09.
 */

public class CouponListAdapter extends BaseAdapter {

    private Context context;
    private List<Coupon> couponList;


    public CouponListAdapter(Context context, List<Coupon> couponList) {
        this.context = context;
        this.couponList = couponList;
    }


    @Override
    public int getCount() {
        return couponList.size();
    }

    @Override
    public Object getItem(int position) {
        return couponList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.coupon, null);
        TextView couponText = (TextView) v.findViewById(R.id.couponText);
        TextView nameText = (TextView) v.findViewById(R.id.nameText);
        TextView dateText = (TextView) v.findViewById(R.id.dateText);

        couponText.setText(couponList.get(position).getCouponText());
        nameText.setText(couponList.get(position).getNameText());
        dateText.setText(couponList.get(position).getDateText());

        v.setTag(couponList.get(position).getCouponText());
        return v;
    }
}