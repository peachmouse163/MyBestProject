package com.example.mybestproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.mybestproject.adapters.MyAdapterThings;
import com.example.mybestproject.datas.Commodity;
import com.example.mybestproject.datas.PayHistory;

import java.util.ArrayList;

public class Settle extends AppCompatActivity {
    //
    private int countNumber = 0;
    //
    private ArrayList<Commodity> commodities;
    private PayHistory payHistory = null;
    //基本控件
    private Button btnWeixinPay,btnZfbPay;
    private ImageButton ibtnReturn;
    private ListView lvSettle;
    private EditText etPlace;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settle);

        //获取从上个页面得到的订单数据
        getBillDatas();
        //初始化
        initView();
        //获取地址，并发送至服务器
        getPlaceSendSettle();
        //绑定按钮监听-跳转至主页面
        intentToMainAcitvity();
    }

    private void intentToMainAcitvity() {
        //省略具体支付过程，(需营业执照才能申请第三方API 所以未引入)，直接跳转至主页面.
        btnWeixinPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settle.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnZfbPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settle.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getPlaceSendSettle() {
        String place = etPlace.getText().toString();
        //以下假数据，等待处理。
        String storeName = "烟台蓬莱店";
        String payTime = "2025-10-30 12：00";
        int payMoney = 100;
        //【String storeName】, 【String payTime】, String deliveryAddress, 【int payMoney】, int payNumber, ArrayList<Commodity> commodities
        payHistory = new PayHistory(storeName,payTime,place,payMoney,countNumber,commodities);
        //将payHistory对象，发送至服务器。
        //*****
        //*****
    }

    private void initView() {
        lvSettle = findViewById(R.id.lv_settle);
        MyAdapterThings myAdapterThings = new MyAdapterThings(this.getApplicationContext(),commodities);
        lvSettle.setAdapter(myAdapterThings);

        btnZfbPay = findViewById(R.id.btn_settle_zfb);
        btnWeixinPay = findViewById(R.id.btn_settle_wx);

        etPlace = findViewById(R.id.et_settle_place);
    }

    private void getBillDatas() {
        //通过访问Bill中静态的things属性，遍历其中的commodity，若num>0，则为订单信息.
        commodities = new ArrayList<>();
        ArrayList<Commodity> allCommodity = Bill.things;
        for (Commodity com :
                allCommodity) {
            int num = com.getNum();
            if (num > 0) {
                countNumber += num;
                commodities.add(com);
            }
        }
    }
}