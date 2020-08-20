package com.demo.amt.demoapp;

import com.demo.amt.operation.MathArithmetic;
import com.google.gson.Gson;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Messi.Mo
 * @date 2020/5/12
 * description:
 */

public class Demo {
    @Test
    public void main() {
        String a="asdas";
        System.out.println("extra:"+a);
        System.out.println("extra:"+3D/2);
        System.out.println("extra:"+new Gson().fromJson(a,String.class));

        System.out.println(""+MathArithmetic.add(1,2,4,5));

        UserInfo userInfo=new UserInfo();
        userInfo.setAge(16);
        userInfo.setName("小明");
        try {
            ObjectOutputStream outputStream=new ObjectOutputStream(new FileOutputStream("user.txt"));
            outputStream.writeObject(userInfo);
            outputStream.close();

            ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream("user.txt"));
            UserInfo u= (UserInfo) inputStream.readObject();
            System.out.println(u!=null?u.getName():"user对象为空");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
