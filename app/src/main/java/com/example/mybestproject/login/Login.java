package com.example.mybestproject.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mybestproject.MainActivity;
import com.example.mybestproject.MainSellerActivity;
import com.example.mybestproject.R;
import com.example.mybestproject.datas.User;
import com.example.mybestproject.threads.LoginCheckThread;

public class Login extends AppCompatActivity {

    //控件
    private Button btnLogin,btnRegister;
    private ImageButton ibtnReturn;
    private EditText etUserName,etUserPwd;

    //基本数据类型
    private String name = "",pwd = "",id = "",identity = "";
    private boolean checkFlag = false;
    public static boolean check = false;
    //特殊数据类型
    public static User user;
    private Handler loginCheckHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if (message.what == 0){
                //checkFlag = (boolean) message.obj;
                //测试假数据，为方便进入。
                checkFlag = true;
            }
            return true;
        }
    });

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
            id = "b"+bundle.getString("name");
            etUserName.setText("b"+id);
        }
    }

    private void MyOclickListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //检查用户名和密码是否正确。正确返回true，错误返回false.
                pwd = etUserPwd.getText().toString();
                id = etUserName.getText().toString();
                //先获取id的第一个的字符，判断身份
                StringBuilder stringBuilder = new StringBuilder(id);
                char i = stringBuilder.charAt(0);
                //开启线程，访问数据，查看用户信息是否正确
                LoginCheckThread loginCheckThread = new LoginCheckThread(getApplicationContext(),loginCheckHandler);
                loginCheckThread.setUserid(id);
                loginCheckThread.setUserpassword(pwd);
                //为接下来的跳转的页面做准备
                Intent intent = null;
                switch (i){
                    case 'a':
                        //访问管理员
                        identity = "admin";
                        intent = new Intent(Login.this, MainActivity.class);
                        break;
                    case 'b':
                        //访问买家
                        identity = "buyer";
                        intent = new Intent(Login.this, MainActivity.class);
                        break;
                    case 's':
                        //访问卖家
                        identity = "seller";
                        intent = new Intent(Login.this, MainSellerActivity.class);
                        break;
                    default:
                        Toast.makeText(Login.this,"用户名格式错误",Toast.LENGTH_SHORT).show();
                        break;
                }
                loginCheckThread.setUserIdentity(identity);
                loginCheckThread.start();
                //空的睡眠循环，等待LoginCheckThread线程更改check。代表完成信息检索。
                while (!check){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(checkFlag){
                    //跳转至主活动
                    startActivity(intent);
                }else
                    Toast.makeText(Login.this,"用户名和密码错误",Toast.LENGTH_SHORT).show();
                check = true;
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
    private boolean CheckNamePwd(String id,String pwd) {
        //将数据发送至服务器，检查正确。根据情况返回true和false
        name = "汝豪";//★假数据，测试写死
        user = new User(name,id,identity);
        return checkFlag;
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