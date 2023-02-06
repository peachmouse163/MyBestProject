package com.example.mybestproject.datas;

import java.util.ArrayList;

public class PayHistory {
    //"青岛职业技术学院","address", "2021-10-29 12：00", "合计：25元", "共2件"
    private String storeName,payTime,deliveryAddress;
    private int payMoney,payNumber;
    private ArrayList<Commodity> commodities;

    public PayHistory() {
    }

    public PayHistory(String storeName, String payTime, String deliveryAddress, int payMoney, int payNumber) {
        this.storeName = storeName;
        this.payTime = payTime;
        this.deliveryAddress = deliveryAddress;
        this.payMoney = payMoney;
        this.payNumber = payNumber;
    }

    public PayHistory(String storeName, String payTime, String deliveryAddress, int payMoney, int payNumber, ArrayList<Commodity> commodities) {
        this.storeName = storeName;
        this.payTime = payTime;
        this.deliveryAddress = deliveryAddress;
        this.payMoney = payMoney;
        this.payNumber = payNumber;
        this.commodities = commodities;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(int payMoney) {
        this.payMoney = payMoney;
    }

    public int getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(int payNumber) {
        this.payNumber = payNumber;
    }

    public ArrayList<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(ArrayList<Commodity> commodities) {
        this.commodities = commodities;
    }
}
