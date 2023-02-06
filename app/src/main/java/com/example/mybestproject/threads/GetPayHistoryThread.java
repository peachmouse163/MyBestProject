package com.example.mybestproject.threads;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.mybestproject.datas.PayHistory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetPayHistoryThread extends Thread{
    //
    //
    private ArrayList<PayHistory> getPayHistories = new ArrayList<>();
    private Handler handler = null;
    //
    //

    public GetPayHistoryThread(Handler handler,String userId){
        this.handler = handler;
        //id用来筛选数据，此处测试不用
    }

    @Override
    public void run() {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    //获取json数据的链接,以下链接为测试链接,注意打开模拟器
                    .url("http://192.168.3.145/datas/payhistory.json")
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            String responseData =  response.body().string();
            //获得menus数据
            parseJSONWithJSONObject(responseData);
        }catch (Exception e){
            Log.d("try catch error:", "GetThingsDatas: "+e);
        }

        Message message = new Message();
        message.what = 10;
        message.obj = getPayHistories;
        handler.sendMessage(message);
    }

    private void parseJSONWithJSONObject(String responseData) {
        try {
            JSONObject jsonObject = new JSONObject(responseData);
            //数据
            JSONArray payhistories = jsonObject.getJSONArray("payhistory");
            for (int i = 0; i < payhistories.length(); i++) {
                JSONObject payhistory = payhistories.getJSONObject(i);
                String storeName = payhistory.getString("storeName");
                String payTime = payhistory.getString("payTime");
                String deliveryAddress = payhistory.getString("deliveryAddress");
                int payMoney = Integer.parseInt(payhistory.getString("payMoney"));
                int payNumber = Integer.parseInt(payhistory.getString("payNumber"));

                getPayHistories.add(new PayHistory(storeName,payTime,deliveryAddress,payMoney,payNumber));
            }

        }catch (Exception e){
            Log.d("try catch error:", "parseJSONWithJSONObject: "+e);
        }
    }
}
