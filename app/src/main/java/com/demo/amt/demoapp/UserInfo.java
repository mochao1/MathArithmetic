package com.demo.amt.demoapp;

import java.io.Serializable;

/**
 * @author Messi.Mo
 * @date 2020/8/18
 * description:
 */

public class UserInfo implements Serializable {
    private static final long serialVersionUID = 7365416536006028510L;
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
