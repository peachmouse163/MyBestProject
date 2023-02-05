package com.example.mybestproject.login;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mybestproject.MainActivity;
import com.example.mybestproject.R;

public class Register extends AppCompatActivity {

    private EditText etRegisterName,etRegisterPwd,etRegisterPwd2;
    private Button btnRegister,btnSellerRegister;
    private ImageButton ibtnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //隐藏标题栏
        ActionBar actionBar= getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        //初始化控件
        InitView();
        //点击监听器配置
        MyOclickListener();
    }
    private void MyOclickListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwd1 = etRegisterPwd.getText().toString(),
                        pwd2 = etRegisterPwd2.getText().toString(),
                        name = etRegisterName.getText().toString();
                //try，catch，抓错误，输入框未输入的情况
                try{
                    if (name.equals("")){                    //先判断是否输入了账号名
                        Toast.makeText(Register.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                    }else if (pwd1.equals("")){              //先判断是否输入了密码
                        Toast.makeText(Register.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    }else if (!pwd1.equals(pwd2)){          //将两次输入的密码进行判断，不一样则清除掉并提示。一样则提示并携带数据，跳回登录活动。
                        Toast.makeText(Register.this,"密码不同",Toast.LENGTH_SHORT).show();
                        etRegisterPwd.setText("");
                        etRegisterPwd2.setText("");
                    }else {
                        Toast.makeText(Register.this,"注册成功",Toast.LENGTH_SHORT).show();
                        //跳转至注册活动
                        Intent intent = new Intent(Register.this,Login.class);
                        //将注册的数据，包装至bundle中，传递给登录界面，让用户减少重复输入。★且，密码验证不用联网。
                        Bundle bundle = new Bundle();
                        bundle.putString("name",name);
                        bundle.putString("pwd",etRegisterPwd.getText().toString());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }catch (NullPointerException e){
                    Toast.makeText(Register.this,"请输入……",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSellerRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this,RegisterSellerActivity.class);
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

    private void InitView() {
        btnRegister = findViewById(R.id.signin_btn_signin);
        btnSellerRegister = findViewById(R.id.signin_btn_seller);

        ibtnReturn = findViewById(R.id.register_ibtn_return);

        etRegisterName = findViewById(R.id.signin_et_name);
        etRegisterPwd = findViewById(R.id.signin_et_password);
        etRegisterPwd2 = findViewById(R.id.signin_et_password0);
    }
}