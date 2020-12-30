package com.packtpub.masteringandroidapp.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.packtpub.masteringandroidapp.R;
import com.packtpub.masteringandroidapp.data.JobOffer;
import com.parse.ParseQueryAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends android.support.v4.app.Fragment {


    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);


        ParseQueryAdapter<JobOffer> parseQueryAdapter = new ParseQueryAdapter<JobOffer>(getActivity(),JobOffer.class) {

            @Override
            public View getItemView(JobOffer jobOffer, View v, ViewGroup parent) {

                if (v == null) {
                    v = View.inflate(getContext(), R.layout.row_job_offer, null);
                }

                super.getItemView(jobOffer, v, parent);

                TextView titleTextView = (TextView) v.findViewById(R.id.rowJobOfferTitle);
                titleTextView.setText(jobOffer.getTitle());
                TextView descTextView = (TextView) v.findViewById(R.id.rowJobOfferDesc);
                descTextView.setText(jobOffer.getDescription());

                return v;
            }
        };

        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(parseQueryAdapter);

        return view;
    }


}
