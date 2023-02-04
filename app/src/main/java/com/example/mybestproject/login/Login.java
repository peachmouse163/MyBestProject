package com.example.mybestproject.login;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mybestproject.MainActivity;
import com.example.mybestproject.R;
import com.example.mybestproject.datas.User;

public class Login extends AppCompatActivity {

    //控件
    private Button btnLogin,btnRegister;
    private ImageButton ibtnReturn;
    private EditText etUserName,etUserPwd;

    //基本数据类型
    private String name = "",pwd = "",id = "";

    //特殊数据类型
    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //隐藏标题栏
        ActionBar actionBar= getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        //初始化控件
        InitView();
        //点击监听器配置
        MyOclickListener();
        //检查register 注册活动是否有传来的值,有的话，获取用户名和密码
        GetRegisterValue();
    }

    private void GetRegisterValue() {
        Intent registerIntent = getIntent();
        Bundle bundle = registerIntent.getExtras();
        if (bundle != null){
            //如果有数据，则获取其中的bundle，减少用户的重复输入量
            name = bundle.getString("name");
            etUserName.setText(name);
        }
    }

    private void MyOclickListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //检查用户名和密码是否正确。正确返回true，错误返回false.
                pwd = etUserPwd.getText().toString();
                name = etUserName.getText().toString();
                if(CheckNamePwd(name,pwd)){
                    //跳转至主活动
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }else
                    Toast.makeText(Login.this,"用户名和密码错误",Toast.LENGTH_SHORT).show();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转至注册活动
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
        ibtnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //返回上一个活动页面
                finish();
            }
        });
    }

    //用于监听器中，检查用户名和密码正确的方法。
    private boolean CheckNamePwd(String name,String pwd) {
        //将数据发送至服务器，检查正确。根据情况返回true和false
        //**********



        //**********
        id = "user123";//★假数据，测试写死
        user = new User(name,id);
        return true;
    }

    private void InitView() {
        btnLogin = findViewById(R.id.login_btn_login);
        btnRegister = findViewById(R.id.login_btn_signin);
        ibtnReturn = findViewById(R.id.login_ibtn_return);
        etUserName = findViewById(R.id.login_et_name);
        etUserPwd = findViewById(R.id.login_et_password);

        //★为测试方便，etUserName，默认“汝豪”
        etUserName.setText("汝豪");
    }
}