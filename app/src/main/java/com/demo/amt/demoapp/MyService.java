package com.demo.amt.demoapp;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyService extends Service {
    private static final String TAG = MyService.class.getSimpleName();

    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    private CopyOnWriteArrayList<Book> mBooks = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<BookArrivedListener> bookArrivedListeners = new RemoteCallbackList<>();
    private Binder binder = new BookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBooks;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBooks.add(book);
        }

        @Override
        public void registerBookArrivedListener(BookArrivedListener listener) throws RemoteException {
            bookArrivedListeners.register(listener);
            Log.d(TAG, "listener size:" + bookArrivedListeners.getRegisteredCallbackCount());
        }

        @Override
        public void unregisterBookArrivedListener(BookArrivedListener listener) throws RemoteException {
            bookArrivedListeners.unregister(listener);
            Log.d(TAG, "listener size:" + bookArrivedListeners.getRegisteredCallbackCount());
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onDestroy() {
        atomicBoolean.set(true);
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBooks.add(new Book("android开发艺术探索", 88, "任玉刚"));
        mBooks.add(new Book("ios入门", 99, "未知"));
        new Thread(new BookService()).start();
    }


    private void newBookArrived(Book book) throws android.os.RemoteException {
        mBooks.add(book);
        Log.d(TAG, "new book is arrived,books size:" + mBooks.size());
        final int num = bookArrivedListeners.beginBroadcast();
        for (int i = 0; i < num; i++) {
            BookArrivedListener bookArrivedListener = bookArrivedListeners.getBroadcastItem(i);
            bookArrivedListener.onNewBookArrived(book);
        }
        bookArrivedListeners.finishBroadcast();
    }

    private class BookService implements Runnable {

        @Override
        public void run() {
            while (!atomicBoolean.get()) {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int size = mBooks.size();
                Book book = new Book("开发书籍第" + (size + 1) + "版", 99 + size, "未知");
                try {
                    newBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
