package com.example.mybestproject.threads;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.example.mybestproject.datas.User;
import com.example.mybestproject.login.Login;

public class LoginCheckThread extends Thread {
    //基本数据
    private String userId,userPassword,userIdentity="buyer";
    //特殊数据
    private Handler handler;
    //
    //


    @Override
    public void run() {
        Message message = new Message();
        message.what = 0;
        message.obj = checkIdAndPwd();
        handler.sendMessage(message);
        Login.check = true;
    }

    private boolean checkIdAndPwd() {
        //从安全性的考量，Android访问远程服务器前段的PHP，PHP函数完成数据库操作，把结果经过JSON编码后传回，Android端再parse出结果。
        //若Android通过JDBC直接访问数据库有安全性风险和出错风险。
        //无PHP函数，身份验证部分简单带过，具体json解析代码，类似 WebThread.java文件。
        String webId = "",webPassword = "",webName = "";
        //webName应该由检索后获得,此处写死假数据.
        webName = "汝豪";
        Login.user = new User(webName,userId,userIdentity);
        return userId.equals(webId) && userPassword.equals(webPassword);
    }

    public LoginCheckThread(Handler handler){
        this.handler = handler;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getUserid() {
        return userId;
    }

    public void setUserid(String userid) {
        this.userId = userid;
    }

    public String getUserpassword() {
        return userPassword;
    }

    public void setUserpassword(String userpassword) {
        this.userPassword = userpassword;
    }
}
