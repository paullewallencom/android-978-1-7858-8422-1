package com.packtpub.masteringandroidapp;

import android.app.Application;

import com.packtpub.masteringandroidapp.data.JobOffer;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by antonio on 09/05/2015.
 */
public class MAApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        ParseObject.registerSubclass(JobOffer.class);

        Parse.initialize(this, "KAG0hs6sRnkUxIEzMuBWV4jyXcSgsUfQMi6J7KFK", "vnfYr9LRiw7T8DVy7eL9oMmmEQsjmtsDqWdlHhpW");
    }

}
