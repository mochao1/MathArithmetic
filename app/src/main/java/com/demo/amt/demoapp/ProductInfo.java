package com.demo.amt.demoapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Messi.Mo
 * @date 2020/8/18
 * description:
 */

public class ProductInfo implements Parcelable {

    private String name;
    private float price;

    protected ProductInfo(Parcel in) {
        name = in.readStringNoHelper();
        price = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringNoHelper(name);
        dest.writeFloat(price);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductInfo> CREATOR = new Creator<ProductInfo>() {
        @Override
        public ProductInfo createFromParcel(Parcel in) {
            return new ProductInfo(in);
        }

        @Override
        public ProductInfo[] newArray(int size) {
            return new ProductInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
