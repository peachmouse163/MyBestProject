package com.example.mybestproject.shared;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedHelperBill {

    private Context mContext;

    public SharedHelperBill() {
    }

    public SharedHelperBill(Context mContext) {
        this.mContext = mContext;
    }


    //定义一个保存数据的方法
    public void save(String tag,int num) {
        SharedPreferences sp = mContext.getSharedPreferences("bill", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(tag,""+num);
        editor.commit();
    }

    //定义一个读取SP文件的方法
    public int read(String tag) {
        int num;
        /*Map<String, String> data = new HashMap<String, String>();*/
        SharedPreferences sp = mContext.getSharedPreferences("bill", Context.MODE_PRIVATE);
        String string = sp.getString(tag,"");
        num = Integer.parseInt(string);
        return num;
    }

}
