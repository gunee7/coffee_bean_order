package com.example.st1drawermenu.Fragment.Tab2;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class Tab2_Model_Card implements Parcelable {

    private int      imageCoffee;
    private String   textCoffee ;
    private String   textPrice  ;
    private String   countCoffee;

    /*-----------------------------------------*/
    /*getter & setter*/

    protected Tab2_Model_Card(Parcel in) {
        imageCoffee = in.readInt();
        textCoffee = in.readString();
        textPrice = in.readString();
        countCoffee = in.readString();
    }

    public static final Creator<Tab2_Model_Card> CREATOR = new Creator<Tab2_Model_Card>() {
        @Override
        public Tab2_Model_Card createFromParcel(Parcel in) {
            return new Tab2_Model_Card(in);
        }

        @Override
        public Tab2_Model_Card[] newArray(int size) {
            return new Tab2_Model_Card[size];
        }
    };

    public int getImageCoffee() {
        return imageCoffee;
    }

    public void setImageCoffee(int imageCoffee) {
        this.imageCoffee = imageCoffee;
    }

    public String getTextCoffee() {
        return textCoffee;
    }

    public void setTextCoffee(String textCoffee) {
        this.textCoffee = textCoffee;
    }

    public String getTextPrice() {
        return textPrice;
    }

    public void setTextPrice(String textPrice) {
        this.textPrice = textPrice;
    }

    public String getCountCoffee() {
        return countCoffee;
    }

    public void setCountCoffee(String countCoffee) {
        this.countCoffee = countCoffee;
    }
/*-----------------------------------------*/
    /*toString*/

    @Override
    public String toString() {
        return "Tab2_Model_Card{" +
                "imageCoffee=" + imageCoffee +
                ", textCoffee='" + textCoffee + '\'' +
                ", textPrice='" + textPrice + '\'' +
                '}';
    }
/*-----------------------------------------*/
    /*constructor*/

    public Tab2_Model_Card(int imageCoffee, String textCoffee, String textPrice, String countCoffee) {
        this.imageCoffee = imageCoffee;
        this.textCoffee = textCoffee;
        this.textPrice = textPrice;
        this.countCoffee = countCoffee;
    }

    public Tab2_Model_Card() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageCoffee);
        dest.writeString(textCoffee);
        dest.writeString(textPrice);
        dest.writeString(countCoffee);
    }
}
