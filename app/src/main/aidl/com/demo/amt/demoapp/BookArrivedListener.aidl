// BookArrivedListener.aidl
package com.demo.amt.demoapp;
import com.demo.amt.demoapp.Book;
// Declare any non-default types here with import statements

interface BookArrivedListener {
      void onNewBookArrived(in Book book);
}
