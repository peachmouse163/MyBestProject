package com.example.mybestproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.mybestproject.R;

public class AdapterSQLImage extends BaseAdapter {

    private int[] res;
    private Context mcontext;

    public AdapterSQLImage(Context context, int[] images) {
        res = images;
        mcontext = context;
    }

    @Override
    public int getCount() {
        return res.length;
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
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.adapter_style_sqlimg,null);
            viewHolder = new ViewHolder();

            viewHolder.imageView =convertView.findViewById(R.id.sql_sqlimg);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.imageView.setImageResource(res[position]);


        return convertView;
    }

    public static class ViewHolder{
        ImageView imageView;
    }
}
