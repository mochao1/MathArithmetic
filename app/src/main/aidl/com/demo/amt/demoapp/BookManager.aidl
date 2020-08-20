// BookManager.aidl
package com.demo.amt.demoapp;
import com.demo.amt.demoapp.Book;
import com.demo.amt.demoapp.BookArrivedListener;
// Declare any non-default types here with import statements

interface BookManager {
   List<Book> getBookList();
   void addBook(in Book book);
   void registerBookArrivedListener(BookArrivedListener listener);
   void unregisterBookArrivedListener(BookArrivedListener listener);
}
