package com.example.mybestproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mybestproject.R;

public class MyAdapterMenu extends BaseAdapter {

    private Context context;
    private String[] menus;
    private LayoutInflater layoutInflater;

    public MyAdapterMenu(Context context, String[] menus) {
        this.context = context;
        this.menus = menus;
    }

    public void setMenus(String[] menus) {
        this.menus = menus;
    }

    @Override
    public int getCount() {
        //返回的item数量
        return menus.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.lv_style_menu,null);
            viewHolder =new ViewHolder();
            viewHolder.textView = view.findViewById(R.id.tv_bill_menu);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (menus.length != 0){
            viewHolder.textView.setText(menus[i]);
        }

        return view;
    }
    /*
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.lv_style_menu,null);
            viewHolder = new ViewHolder();
            viewHolder.tvMenu =view.findViewById(R.id.tv_bill_menu);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }
        if (menuNames.length != 0){
            viewHolder.tvMenu.setText(menuNames[i]);
        }
        return view;
    }
    */
    public class ViewHolder{
        TextView textView;
    }
}
