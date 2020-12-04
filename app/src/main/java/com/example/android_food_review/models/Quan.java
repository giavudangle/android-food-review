package com.example.android_food_review.models;

public class Quan {
    public Integer  _id;
    public String tenQuan;
    public Integer soLuongMon;

    public Quan(Integer _id,String tenQuan,Integer soLuongMon){
        this._id=_id;
        this.tenQuan=tenQuan;
        this.soLuongMon=soLuongMon;
    }

    public Quan(String tenQuan, int soLuongMon) {
        this.tenQuan = tenQuan;
        this.soLuongMon=soLuongMon;
    }
}
