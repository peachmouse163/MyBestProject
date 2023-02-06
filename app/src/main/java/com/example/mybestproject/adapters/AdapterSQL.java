package com.example.mybestproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mybestproject.R;
import com.example.mybestproject.datas.Commodity;
import com.example.mybestproject.datas.PayHistory;

import java.util.ArrayList;

public class AdapterSQL extends BaseAdapter {

    //
    //
    private ArrayList<PayHistory> payHistories;
    private Context context;
    private LayoutInflater layoutInflater;

    public AdapterSQL(Context context,ArrayList<PayHistory> payHistories) {
        this.context = context;
        this.payHistories = payHistories;
    }

    @Override
    public int getCount() {
        return payHistories.size();
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
            viewHolder.imageView = convertView.findViewById(R.id.sql_iamgeview);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.tvStore.setText(payHistories.get(position).getStoreName());
        viewHolder.tvTime.setText(payHistories.get(position).getPayTime());
        viewHolder.tvMoney.setText(payHistories.get(position).getPayMoney()+"元");
        viewHolder.tvNum.setText(payHistories.get(position).getPayNumber()+"件");
        try{
            viewHolder.imageView.setImageResource(payHistories.get(0).getCommodities().get(0).getPic());
        }catch (Exception e){
            viewHolder.imageView.setImageResource(R.drawable.domepicture);
        }
        return convertView;
    }

    public static class ViewHolder{
        TextView tvStore,tvTime,tvMoney,tvNum;
        ImageView imageView;
    }

}
