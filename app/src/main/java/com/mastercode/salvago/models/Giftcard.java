package com.mastercode.salvago.models;

import java.util.Date;

public class Giftcard {

    public String title;
    public String shortDescript;
    public String largeDescript;
    public long initDate;
    public long finalDate;
    public String company;
    public double savedMoney;


    public Giftcard(String title, String shortDescript) {
        this.title = title;
        this.shortDescript = shortDescript;
    }




}
