package com.example.mybestproject.datas;

import java.util.Objects;

public class Commodity {

    private String id,name,description;
    private int money,pic,num = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commodity commodity = (Commodity) o;
        return money == commodity.money && pic == commodity.pic && num == commodity.num && id.equals(commodity.id) && Objects.equals(name, commodity.name) && Objects.equals(description, commodity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, money, pic, num);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public Commodity() {
    }

    public Commodity(String id, String name, String description, int money, int pic,int num) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.money = money;
        this.pic = pic;
        this.num = num;
    }
}
