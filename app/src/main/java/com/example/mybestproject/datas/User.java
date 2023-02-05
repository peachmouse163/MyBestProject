package com.example.mybestproject.datas;

public class User {

    private String username,userid,identity;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

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

    public User(String username,String userid,String identity) {
        this.username = username;
        this.userid = userid;
        this.identity = identity;
    }
}
