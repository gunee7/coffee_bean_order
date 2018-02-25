package com.example.st1drawermenu.NavPackage.Coupon;

/**
 * Created by Administrator on 2018-02-09.
 */

public class Coupon {

    String couponText;
    String nameText;
    String dateText;

    public Coupon(String couponText, String nameText, String dateText) {
        this.couponText = couponText;
        this.nameText = nameText;
        this.dateText = dateText;
    }

    public String getCouponText() {
        return couponText;
    }

    public void setCouponText(String couponText) {
        this.couponText = couponText;
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }

    public String getDateText() {
        return dateText;
    }

    public void setDateText(String dateText) {
        this.dateText = dateText;
    }
}
