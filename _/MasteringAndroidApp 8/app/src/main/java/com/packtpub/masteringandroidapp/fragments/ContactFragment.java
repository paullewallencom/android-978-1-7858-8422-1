package com.packtpub.masteringandroidapp.fragments;

import android.app.*;
import android.app.ListFragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.packtpub.masteringandroidapp.R;
import com.packtpub.masteringandroidapp.adapters.JobContactsAdapter;
import com.packtpub.masteringandroidapp.data.Country;
import com.packtpub.masteringandroidapp.data.JobContact;
import com.packtpub.masteringandroidapp.data.JobOffer;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonio on 28/04/2015.
 */
public class ContactFragment extends android.support.v4.app.ListFragment {

    List<Object> mListItems;

    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view,bundle);
        retrieveJobContacts();
    }

    public void retrieveJobContacts(){
        ParseQuery<JobContact> query = ParseQuery.getQuery("JobContact");
        query.orderByAscending("country");
        query.findInBackground(new FindCallback<JobContact>() {
            @Override
            public void done(List<JobContact> jobContactsList, ParseException e) {
                mListItems = new ArrayList<Object>();
                String currentCountry = "";
                for (JobContact jobContact: jobContactsList) {
                    if (!currentCountry.equals(jobContact.getCountry())){
                        currentCountry = jobContact.getCountry();
                        mListItems.add(new Country(currentCountry));
                    }
                    mListItems.add(jobContact);
                }
                setListAdapter(new JobContactsAdapter(mListItems,getActivity()));
            }
        });
    }
}
