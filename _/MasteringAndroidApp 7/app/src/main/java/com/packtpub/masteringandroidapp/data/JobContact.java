package com.packtpub.masteringandroidapp.data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by antonio on 09/05/2015.
 */
@ParseClassName("JobContact")
public class JobContact extends ParseObject {

    public JobContact() {
        // A default constructor is required.
    }

    public String getName() {
        return getString("name");
    }

    public String getDescription() {
        return getString("description");
    }

    public String getCountry() {
        return getString("country");
    }

    public String getEmail() {
        return getString("email");
    }

}
