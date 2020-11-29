package com.example.android_food_review.models;

public class MonAn {
    private Integer _id;
    private String tenMonAn;
    private String diaChi;
    private Integer maQuan;
    private Long gia;
    private byte[] image;

    public Integer getMaQuan() {
        return maQuan;
    }

    public MonAn(Integer _id, String tenMonAn, String diaChi, Integer maQuan, Long gia, byte[] image) {
        this._id = _id;
        this.tenMonAn = tenMonAn;
        this.diaChi = diaChi;
        this.maQuan = maQuan;
        this.gia = gia;
        this.image = image;
    }

    public void setMaQuan(Integer maQuan) {
        this.maQuan = maQuan;
    }



    public MonAn(Integer _id, String tenMonAn, String diaChi, Long gia) {
        this._id = _id;
        this.tenMonAn = tenMonAn;
        this.diaChi = diaChi;
        this.gia = gia;
    }



    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Long getGia() {
        return gia;
    }

    public void setGia(Long gia) {
        this.gia = gia;
    }
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

