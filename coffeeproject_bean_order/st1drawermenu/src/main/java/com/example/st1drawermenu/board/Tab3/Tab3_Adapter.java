package com.example.st1drawermenu.board.Tab3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.st1drawermenu.R;
import com.example.st1drawermenu.board.ModelArticle;

import java.util.List;

public class Tab3_Adapter extends ArrayAdapter<ModelArticle> {

    private Context context;
    private List<ModelArticle> mList;
    private final LayoutInflater mInflater;

    public Tab3_Adapter(@NonNull Context context, int resource, @NonNull List<ModelArticle> objects) {
        super(context, resource, objects);

        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;

        // 뷰와 관련된 설정.
        ViewHolder holder = null;

        if( rowView == null ){ // inflation
            rowView = mInflater.inflate(R.layout.fragment_tab3_custom, parent, false );

            holder = new ViewHolder();
            holder.no      = rowView.findViewById( R.id.no      );
            holder.title   = rowView.findViewById( R.id.title   );
            holder.hit     = rowView.findViewById( R.id.hit     );

            rowView.setTag( holder );
        }
        else {
            holder = (ViewHolder) rowView.getTag();
        }

        // 화면에 표시될 데이터 관련 설정
        ModelArticle item = getItem( position );

        holder.no     .setText( item.getArticleno() + "" );
        holder.hit    .setText( item.getHit() + ""  );
        holder.title  .setText( item.getTitle()     );

        return rowView;
    }

    private class ViewHolder {
        TextView no     ;
        TextView title  ;
        TextView hit    ;
    }
}
