package com.packtpub.masteringandroidapp;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.packtpub.masteringandroidapp.data.JobContact;
import com.packtpub.masteringandroidapp.data.JobOffer;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.SaveCallback;

import java.text.ParseException;

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
        //ParseInstallation.getCurrentInstallation().saveInBackground();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Roboto-Regular.ttf").setFontAttrId(R.attr.fontPath)
                .build());

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(com.parse.ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });


    }

    public static MAApplication getInstance(){
        return sInstance;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }
}
