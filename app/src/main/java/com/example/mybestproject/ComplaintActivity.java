package com.example.mybestproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mybestproject.datas.Complaint;
import com.example.mybestproject.datas.PayHistory;
import com.example.mybestproject.datas.User;
import com.example.mybestproject.login.Login;
import com.example.mybestproject.tools.GetNowTimeTool;
import com.example.mybestproject.ui.dashboard.DashboardFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

public class ComplaintActivity extends AppCompatActivity {

    //
    private String sellerName,nowTime;
    //
    private User user;
    private Complaint complaint;
    //
    private Button btnComplaint;
    private EditText etComplaint;
    private Spinner spinnerComplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        //初始化控件
        initView();
        //获取spinner的值
        getSpinnerValue();
        //形成 complaint对象
        complaint = new Complaint(etComplaint.getText().toString(),sellerName,user, GetNowTimeTool.getNowTime());
        //将信息，发送至服务器,操作省略。
        //*****
        //*****
    }

    private void getSpinnerValue() {
        sellerName = spinnerComplaint.getSelectedItem().toString();
        Log.d("******log", "getSpinnerValue: "+sellerName);
    }

    private void initView() {
        user = Login.user;
        btnComplaint = findViewById(R.id.btn_complaint_complaint);
        etComplaint = findViewById(R.id.et_complaint_context);
        //初始化下拉菜单,通过历史账单的成员变量，获得店家名字的值。
        spinnerComplaint = findViewById(R.id.spinner_complaint);
        ArrayList<String> storeNames = new ArrayList<>();
        //通过hashset的特殊性：重复值无法存储。
        HashSet<String> storeNameHS = new HashSet<>();
        for (PayHistory pHistory:
                DashboardFragment.payHistories) {
            storeNameHS.add(pHistory.getStoreName());
        }
        //由于结果需要list集合，因此转为arraylist
        for (String string :
                storeNameHS) {
            storeNames.add(string);
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,storeNames);
        spinnerComplaint.setAdapter(spinnerAdapter);
    }
}