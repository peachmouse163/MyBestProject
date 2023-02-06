package com.example.mybestproject.threads;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.mybestproject.R;
import com.example.mybestproject.datas.Commodity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebThread extends Thread {

    //基本数据
    //flag为commodities的下标指针
    private static int flag = 0;
    private String[] menus = new String[11];
    //特殊数据
    //此处默认仅10个商品，最后一个始终为空，后期更改可改为，链式串数据类型，以便处理长度不确定情况。
    private ArrayList<Commodity> commodities = new ArrayList<>();
    private Handler handler;

    public WebThread(Handler handler){
        this.handler = handler;
    }

    @Override
    public void run() {

        flag = 0;
        //获取things数据
        GetThingsDatas();
        //发送menus数据
        Message messageMenus = new Message();
        messageMenus.what = 0;
        messageMenus.obj = menus;
        handler.sendMessage(messageMenus);
        //发送things数据
        Message message = new Message();
        message.what = 1;
        message.obj = commodities;
        handler.sendMessage(message);
    }

    private void GetThingsDatas() {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    //获取json数据的链接,以下链接为测试链接,注意打开模拟器
                    .url("http://192.168.3.145/datas/things.json")
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            String responseData =  response.body().string();
            //获得menus数据
            parseJSONWithJSONObject(responseData);
        }catch (Exception e){
            Log.d("try catch error:", "GetThingsDatas: "+e);
        }
    }

    private void parseJSONWithJSONObject(String responseData) {
        try {
            JSONObject jsonObject = new JSONObject(responseData);
            //things数据
            JSONObject commodity = jsonObject.getJSONObject("commodity");

            //判断commodity内的数量
            for (int i = 0; i < 2; i++) {
                char flag = (char)('A'+i);
                menus[i] = flag+"thing";
                JSONArray thing = commodity.getJSONArray(flag+"thing");
                getEveryThings(thing);
            }
        }catch (Exception e){
            Log.d("try catch error:", "parseJSONWithJSONObject: "+e);
        }
    }

    private void getEveryThings(JSONArray jsonArray) {
        try{
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject everything = jsonArray.getJSONObject(i);
                String id = everything.getString("id");
                String name = everything.getString("name");
                String description = everything.getString("description");
                int money = Integer.parseInt(everything.getString("money"));
                //图片资源，后期改为资源路径。暂时用假数据代替。
                //String pic = everything.getString("pic");
                commodities.add(new Commodity(id,name,description,money, R.drawable.domepicture,0));
                //log显示获得到的数据
                Log.d("***log", "parseJSONWithJSONObject: everything:"+"\nid:"+id+"\nname:"+name+"\ndescription:"+description+"\nmoney:"+money);
            }
        }catch (Exception e){
            Log.d("try catch error:", "getEveryThings: "+e);
        }
    }
}
