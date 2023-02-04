package com.example.mybestproject.datas;

public class PersonMessage {

    private String name,level;
    private int coupon,money,gift,score;

    public PersonMessage(String name, String level, int coupon, int money, int gift, int score) {
        this.name = name;
        this.level = level;
        this.coupon = coupon;
        this.money = money;
        this.gift = gift;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getCoupon() {
        return coupon;
    }

    public void setCoupon(int coupon) {
        this.coupon = coupon;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getGift() {
        return gift;
    }

    public void setGift(int gift) {
        this.gift = gift;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
