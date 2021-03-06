package com.example.android_food_review.models;

public class Review {
    private Integer _id;
    private Integer idMonAn;
    private String tenNguoiReview;
    private String noiDungReview;
    private byte[] imageReview;

    public Review(Integer idMonAn, String tenNguoiReview, String noiDungReview, byte[] imageReview) {
        this.idMonAn = idMonAn;
        this.tenNguoiReview = tenNguoiReview;
        this.noiDungReview = noiDungReview;
        this.imageReview = imageReview;
    }

    public byte[] getImageReview() {
        return imageReview;
    }

    public void setImageReview(byte[] imageReview) {
        this.imageReview = imageReview;
    }

    public Review(Integer _id, Integer idMonAn, String tenNguoiReview, String noiDungReview, byte[] imageReview) {
        this._id = _id;
        this.idMonAn = idMonAn;
        this.tenNguoiReview = tenNguoiReview;
        this.noiDungReview = noiDungReview;
        this.imageReview = imageReview;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getIdMonAn() {
        return idMonAn;
    }

    public void setIdMonAn(Integer idMonAn) {
        this.idMonAn = idMonAn;
    }

    public String getTenNguoiReview() {
        return tenNguoiReview;
    }

    public void setTenNguoiReview(String tenNguoiReview) {
        this.tenNguoiReview = tenNguoiReview;
    }

    public String getNoiDungReview() {
        return noiDungReview;
    }

    public void setNoiDungReview(String noiDungReview) {
        this.noiDungReview = noiDungReview;
    }


}
