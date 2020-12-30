package com.packtpub.masteringandroidapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.packtpub.masteringandroidapp.R;
import com.parse.ParseObject;

/**
 * Created by antonio on 28/04/2015.
 */
public class ContactFragment extends Fragment {

    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        view.findViewById(R.id.addJobOffer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseObject jobOffer = new ParseObject("JobOffer");
                jobOffer.put("title", "Android Contract");
                jobOffer.put("description", "6 months rolling contract. /n The client" +
                        "is a worldwide known digital agency");
                jobOffer.put("type", "Contract");
                jobOffer.put("salary", "450 GBP/day");
                jobOffer.put("company", "Recruiters LTD");
                jobOffer.put("imageLink", "http://.....recruitersLTD_logo.png");
                jobOffer.put("location","Reading, UK");
                jobOffer.saveInBackground();
            }
        });

        return view;
    }
}
