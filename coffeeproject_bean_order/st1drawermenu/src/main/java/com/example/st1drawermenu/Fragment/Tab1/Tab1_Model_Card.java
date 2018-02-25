package com.example.st1drawermenu.Fragment.Tab1;

import android.graphics.drawable.Drawable;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class Tab1_Model_Card {

    private Drawable imageCoffee;
    private String   textCoffee ;
    private String   textPrice  ;

    /*-----------------------------------------*/
    /*getter & setter*/

    public Drawable getImageCoffee() {
        return imageCoffee;
    }

    public void setImageCoffee(Drawable imageCoffee) {
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
    /*-----------------------------------------*/
    /*toString*/

    public Tab1_Model_Card(Drawable imageCoffee, String textCoffee, String textPrice) {
        this.imageCoffee = imageCoffee;
        this.textCoffee = textCoffee;
        this.textPrice = textPrice;
    }

    public Tab1_Model_Card() {
    }

    @Override
    public String toString() {
        return "Tab1_Model_Card{" +
                "imageCoffee=" + imageCoffee +
                ", textCoffee='" + textCoffee + '\'' +
                ", textPrice='" + textPrice + '\'' +
                '}';
    }
}
