package com.example.st1drawermenu.NavPackage.Event;

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

public class EventListAdapter extends BaseAdapter {

    private Context context;
    private List<Event> eventList;


    public EventListAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @Override

    public int getCount() {
        return eventList.size();
    }

    @Override
    public Object getItem(int i) {
        return eventList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.event , null);
        TextView eventText = (TextView) v.findViewById(R.id.eventText);
        TextView nameText   = (TextView) v.findViewById(R.id.nameText);
        TextView dateText   = (TextView) v.findViewById(R.id.dateText);

        eventText.setText(eventList.get(i).getEvent());
        nameText.setText(eventList.get(i).getName());
        dateText.setText(eventList.get(i).getDate());

        v.setTag(eventList.get(i).getEvent());
        return v;
    }
}
