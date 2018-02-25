package com.example.st1drawermenu.NavPackage.Notices;

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

public class StatisticsListAdapter extends BaseAdapter {

    private Context context;
    private List<Statistics> statisticsList;

    public StatisticsListAdapter(Context context, List<Statistics> statisticsList) {
        this.context = context;
        this.statisticsList = statisticsList;
    }

    @Override

    public int getCount() {
        return statisticsList.size();
    }

    @Override
    public Object getItem(int i) {
        return statisticsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.statistics , null);
        TextView statisticsText = (TextView) v.findViewById(R.id.statisticsText);
        TextView nameText   = (TextView) v.findViewById(R.id.nameText);
        TextView dateText   = (TextView) v.findViewById(R.id.dateText);

        statisticsText.setText(statisticsList.get(i).getStatistics());
        nameText.setText(statisticsList.get(i).getName());
        dateText.setText(statisticsList.get(i).getDate());

        v.setTag(statisticsList.get(i).getStatistics());
        return v;
    }
}
