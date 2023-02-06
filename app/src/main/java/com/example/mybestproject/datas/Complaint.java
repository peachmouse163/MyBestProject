package com.example.mybestproject.datas;

public class Complaint {

    private String complaint,seller,time;
    private User user;

    public Complaint(String complaint, String seller, User user,String time) {
        this.complaint = complaint;
        this.seller = seller;
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
