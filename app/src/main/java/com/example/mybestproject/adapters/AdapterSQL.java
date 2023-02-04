package com.example.mybestproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.mybestproject.R;

public class AdapterSQL extends BaseAdapter {

    private Context context;
    private String[][] data;
    private LayoutInflater layoutInflater;
    private int[] pic;

    public AdapterSQL(Context context, String[][] data, int[] pics) {
        this.context = context;
        this.data = data;
        this.pic = pics;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_style_sql,null);
            viewHolder = new ViewHolder();

            viewHolder.tvStore =convertView.findViewById(R.id.sql_storename);
            viewHolder.tvTime = convertView.findViewById(R.id.sql_time);
            viewHolder.tvMoney = convertView.findViewById(R.id.sql_money);
            viewHolder.tvNum = convertView.findViewById(R.id.sql_num);
            viewHolder.gridView = convertView.findViewById(R.id.sql_listview);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.tvStore.setText(data[position][0]);
        viewHolder.tvTime.setText(data[position][1]);
        viewHolder.tvMoney.setText(data[position][2]);
        viewHolder.tvNum.setText(data[position][3]);

        AdapterSQLImage sqlImage = new AdapterSQLImage(context,pic);
        viewHolder.gridView.setAdapter(sqlImage);

        return convertView;
    }

    public static class ViewHolder{
        TextView tvStore,tvTime,tvMoney,tvNum;
        GridView gridView;
    }

}
