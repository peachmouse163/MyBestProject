package com.example.mybestproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mybestproject.datas.Commodity;
import com.example.mybestproject.datas.Commodity;
import com.example.mybestproject.R;

import java.util.ArrayList;

public class MyAdapterThings extends BaseAdapter {

    private ArrayList<Commodity> commodities;
    private Context context;

    public MyAdapterThings(Context context,ArrayList<Commodity> commodities) {
        this.commodities = commodities;
        this.context = context;
    }

    @Override
    public int getCount() {
        return commodities.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    //旧版代码
    /*
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        int num;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lv_style_things_h, null);
            viewHolder = new ViewHolder();
            //增加对应组件
            viewHolder.tvThingName = view.findViewById(R.id.tv_bill_thingname);
            viewHolder.tvThingMoney = view.findViewById(R.id.tv_bill_money);
            viewHolder.tvThingsDescription = view.findViewById(R.id.tv_bill_description);
            viewHolder.imgThing = view.findViewById(R.id.img_bill_things);
            viewHolder.ibtnAdd = view.findViewById(R.id.iv_product_jia);
            viewHolder.ibtnReduce = view.findViewById(R.id.iv_product_jian);
            viewHolder.tvNum = view.findViewById(R.id.tv_check_num);
            viewHolder.tvPosition = view.findViewById(R.id.tv_position);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (thingsNames.length != 0) {
            viewHolder.tvThingName.setText(thingsNames[i]);
            viewHolder.imgThing.setImageResource(thingsImg[i]);
            viewHolder.ibtnAdd.setTag(R.id.thing, "" + i);
            viewHolder.tvNum.setTag(R.id.et_id, i);
            cursor = mydb.select(thingsNames[i]);
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                String stringnum = cursor.getString(2);
                viewHolder.tvNum.setText(stringnum);

            } else {
                ;
            }

            viewHolder.tvPosition.setText(i + "");
            viewHolder.ibtnReduce.setOnClickListener(clickListener);
            viewHolder.ibtnAdd.setOnClickListener(clickListener);

            try {
                num = shp.read("" + i);
            } catch (Exception e) {
                num = 0;
            }
        }

        return view;
    }
    */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        int num;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.lv_style_things_h, null);
            viewHolder = new ViewHolder();
            //增加对应组件
            viewHolder.tvThingName = view.findViewById(R.id.tv_bill_thingname);
            viewHolder.tvThingMoney = view.findViewById(R.id.tv_bill_money);
            viewHolder.tvThingsDescription = view.findViewById(R.id.tv_bill_description);
            viewHolder.imgThing = view.findViewById(R.id.img_bill_things);
            viewHolder.ibtnAdd = view.findViewById(R.id.iv_product_jia);
            viewHolder.ibtnReduce = view.findViewById(R.id.iv_product_jian);
            viewHolder.tvNum = view.findViewById(R.id.tv_check_num);
            viewHolder.tvPosition = view.findViewById(R.id.tv_position);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (commodities.size() != 0){
            viewHolder.tvThingName.setText(commodities.get(i).getName());
            viewHolder.tvThingsDescription.setText(commodities.get(i).getDescription());
            viewHolder.tvThingMoney.setText(commodities.get(i).getMoney()+"元");

            //图片为固定默认图片
            viewHolder.imgThing.setImageResource(R.drawable.domepicture);

        }
        return view;
    }

    public class ViewHolder{
        TextView tvThingName,tvThingsDescription,tvThingMoney,tvNum,tvPosition;
        ImageView imgThing;
        ImageView ibtnAdd,ibtnReduce;
    }

}
