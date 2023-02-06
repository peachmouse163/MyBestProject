package com.example.mybestproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mybestproject.adapters.MyAdapterMenu;
import com.example.mybestproject.adapters.MyAdapterThings;
import com.example.mybestproject.datas.Commodity;
import com.example.mybestproject.threads.WebThread;

import java.io.Serializable;
import java.util.ArrayList;


public class Bill extends AppCompatActivity {

    //基本控件
    private TextView tvMessageNews,tvMessageTip,tvShowMoney;
    private ListView lvThings,lvMenu;
    private Button btnPay,btnReturn;
    private ImageButton ibtnTop;
    //特殊控件

    //基本数据
    private String messageNews = "优惠活动";
    private boolean haveMenu = false,haveThings = false;
        //存放menu的数组
    private String[] menus = {"默认数据源"};
    //特殊数据
    private Context context;
        //存商品的communities的数组
    public static ArrayList<Commodity> things = new ArrayList<>();
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //while (msg.obj != null && !haveMenu && !haveThings){
                if (msg.what == 1){
                    //获得things的文字内容
                    things = (ArrayList<Commodity>) msg.obj;
                    haveThings = true;
                }else if(msg.what == 0) {
                    //获得menus的文字内容
                    menus = (String[])msg.obj;
                    haveMenu = true;
                }
            //}
            //获取数据后,重新加载mydapter
            reMakeMyAdapter();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        //初始化组件
        initView();
        //运行新线程，通过网络json文件，获得货物资源。通过
        WebThread webThread = new WebThread(handler);
        webThread.start();
        //绑定按钮监听器
        AddOnClickListener();
        //获得信息  News和Tips
        GetAndChangeText();
        //自定义适配器Adapter并绑定
        MakeMyAdapter();

    }

    public void reMakeMyAdapter(){
        MakeMyAdapter();
    }

    private void MakeMyAdapter() {
        MyAdapterMenu myAdapterMenu = new MyAdapterMenu(context,menus);
        lvMenu.setAdapter(myAdapterMenu);
        MyAdapterThings myAdapterThings = new MyAdapterThings(context,things);
        lvThings.setAdapter(myAdapterThings);
    }

    private void GetAndChangeText() {
        //通过访问，获得文字信息
        //*************
        //死数据
        String news = "news",tip = "Tips";

        //*************
        tvMessageTip.setText(tip);
        tvMessageNews.setText(news);
    }

    private void AddOnClickListener() {
        //返回按钮
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //支付购买按钮
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至下一个结账settle界面，订单信息通过访问static属性的things，其中num不为0的。
                //模拟订单,假数据
                for (int i = 0; i < 5; i++) {
                    things.get(i).setNum(1);
                }
                Intent intent = new Intent(Bill.this,Settle.class);
                startActivity(intent);
            }
        });
        //回到顶部
        ibtnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void initView() {
        context = this.getApplicationContext();

        tvMessageNews = findViewById(R.id.tv_bill_news);
        tvMessageTip = findViewById(R.id.tv_bill_store);
        tvShowMoney = findViewById(R.id.bill_tv_moneys);

        lvMenu = findViewById(R.id.lv_bill_menu);
        lvThings = findViewById(R.id.lv_bill_things);

        btnPay = findViewById(R.id.btn_bill_settle);
        btnReturn = findViewById(R.id.btn_bill_return);

        ibtnTop = findViewById(R.id.imgbtn_bill_top);

        things.add(new Commodity("id","name","description",10,R.drawable.domepicture,0));
    }
}