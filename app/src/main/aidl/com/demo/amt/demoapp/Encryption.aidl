// Encryption.aidl
package com.demo.amt.demoapp;

// Declare any non-default types here with import statements

interface Encryption {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    String encry(String source,String key);
    String decry(String source,String key);
}
