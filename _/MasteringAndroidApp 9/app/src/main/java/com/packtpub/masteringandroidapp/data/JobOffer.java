package com.packtpub.masteringandroidapp.data;

import android.database.Cursor;

import com.packtpub.masteringandroidapp.database.DatabaseContract;
import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.io.Serializable;

/**
 * Created by antonio on 09/05/2015.
 */
@ParseClassName("JobOffer")
public class JobOffer extends ParseObject implements Serializable {

    public JobOffer() {
        // A default constructor is required.
    }

    private String title;

    private String description;

    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        put("title", title);
    }

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String description) {
        put("description", description);
    }

    public String getType() {
        return getString("type");
    }

    public void setType(String type) {
        put("type", type);
    }

    public String getSalary() {
        return getString("salary");
    }

    public void setSalary(String salary) {
        put("salary", salary);
    }

    public String getCompany() {
        return getString("company");
    }

    public void setCompany(String company) {
        put("company", company);
    }

    public String getImageLink() {
        return getString("imageLink");
    }

    public void setImageLink(String imageLink) {
        put("imageLink", imageLink);
    }

    public String getLocation() {
        return getString("location");
    }

    public void setLocation(String company) {
        put("company", company);
    }




    public static JobOffer createJobOfferfromCursor(Cursor cursor){
        JobOffer offer = new JobOffer();
        offer.setTitle(cursor.getString(cursor.getColumnIndex(DatabaseContract.JobOfferTable.TITLE)));
        offer.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseContract.JobOfferTable.DESC)));
        offer.setType(cursor.getString(cursor.getColumnIndex(DatabaseContract.JobOfferTable.TYPE)));
        offer.setSalary(cursor.getString(cursor.getColumnIndex(DatabaseContract.JobOfferTable.SALARY)));
        offer.setLocation(cursor.getString(cursor.getColumnIndex(DatabaseContract.JobOfferTable.LOCATION)));
        offer.setCompany(cursor.getString(cursor.getColumnIndex(DatabaseContract.CompanyTable.NAME)));
        offer.setImageLink(cursor.getString(cursor.getColumnIndex(DatabaseContract.CompanyTable.IMAGE_LINK)));
        return offer;
    }

}
