package com.demo.amt.demoapp;

import android.os.RemoteException;

/**
 * @author Messi.Mo
 * @date 2020/8/19
 * description:
 */

public class EncryptionImpl extends Encryption.Stub {
    @Override
    public String encry(String source, String key) throws RemoteException {
        return source+key;
    }

    @Override
    public String decry(String source, String key) throws RemoteException {
        int index=key.length();
        return source.substring(0,index);
    }
}
