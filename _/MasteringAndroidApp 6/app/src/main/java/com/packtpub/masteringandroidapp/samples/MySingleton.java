package com.packtpub.masteringandroidapp.samples;

/**
 * Created by antonio on 17/05/2015.
 */
public class MySingleton {

    private static MySingleton sInstance;

    public static MySingleton getInstance(){
        if (sInstance == null) {
            sInstance = new MySingleton();
        }
        return sInstance;
    }
}
