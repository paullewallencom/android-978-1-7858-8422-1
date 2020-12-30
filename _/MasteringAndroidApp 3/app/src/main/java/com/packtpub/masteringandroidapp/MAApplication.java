package com.packtpub.masteringandroidapp;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.packtpub.masteringandroidapp.data.JobOffer;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by antonio on 09/05/2015.
 */
public class MAApplication extends Application {

    private static MAApplication sInstance;

    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        mRequestQueue = Volley.newRequestQueue(this);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        ParseObject.registerSubclass(JobOffer.class);

        Parse.initialize(this, "KAG0hs6sRnkUxIEzMuBWV4jyXcSgsUfQMi6J7KFK", "vnfYr9LRiw7T8DVy7eL9oMmmEQsjmtsDqWdlHhpW");
    }

    private static MAApplication getInstance(){
        return sInstance;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }
}
