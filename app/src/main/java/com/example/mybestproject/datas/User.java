package com.example.mybestproject.datas;

public class User {

    private String username,userid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public User(String username,String userid) {
        this.username = username;
        this.userid = userid;
    }
}
