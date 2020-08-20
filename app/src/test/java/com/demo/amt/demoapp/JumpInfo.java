package com.demo.amt.demoapp;

import java.io.Serializable;

/**
 * Created by LBF on 2019/1/23.
 */
public class JumpInfo implements Serializable {
    private int type;
    private String extra;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
