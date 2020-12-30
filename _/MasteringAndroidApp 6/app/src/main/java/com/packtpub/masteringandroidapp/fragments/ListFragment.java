package com.packtpub.masteringandroidapp.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.packtpub.masteringandroidapp.R;
import com.packtpub.masteringandroidapp.adapters.JobOffersAdapter;
import com.packtpub.masteringandroidapp.data.JobOffer;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends android.support.v4.app.Fragment {

    public List<JobOffer> mListItems;
    public RecyclerView mRecyclerView;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Retrieve the list of offers
        retrieveJobOffers();

        return view;
    }


    public void retrieveJobOffers(){
        ParseQuery<JobOffer> query = ParseQuery.getQuery("JobOffer");
        query.findInBackground(new FindCallback<JobOffer>() {

            @Override
            public void done(List<JobOffer> jobOffersList, ParseException e) {
                mListItems = jobOffersList;
                JobOffersAdapter adapter = new JobOffersAdapter(mListItems);
                mRecyclerView.setAdapter(adapter);
            }

        });
    }

}
