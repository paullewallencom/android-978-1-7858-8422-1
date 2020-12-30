package com.packtpub.masteringandroidapp;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.packtpub.masteringandroidapp.data.JobContact;
import com.packtpub.masteringandroidapp.data.JobOffer;
import com.parse.Parse;
import com.parse.ParseObject;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

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
        ParseObject.registerSubclass(JobContact.class);

        Parse.initialize(this, "KAG0hs6sRnkUxIEzMuBWV4jyXcSgsUfQMi6J7KFK", "vnfYr9LRiw7T8DVy7eL9oMmmEQsjmtsDqWdlHhpW");

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Roboto-Regular.ttf").setFontAttrId(R.attr.fontPath)
                .build());

    }

    private static MAApplication getInstance(){
        return sInstance;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }
}
