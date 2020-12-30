package com.packtpub.masteringandroidapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.packtpub.masteringandroidapp.R;
import com.packtpub.masteringandroidapp.data.JobOffer;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Unathi on 7/29/2015.
 */
public class MyMapFragment extends Fragment{

    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_map, container, false);

        FragmentManager fm = getChildFragmentManager();
        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (googleMap == null) {
            googleMap = mapFragment.getMap();

            ParseQuery<JobOffer> query = ParseQuery.getQuery("JobOffer");
            query.findInBackground(new FindCallback<JobOffer>() {
                @Override
                public void done(List<JobOffer> list, ParseException e) {

                    ParseGeoPoint geoPoint = new ParseGeoPoint();

                    for(int i =0;i<list.size();i++){
                        geoPoint = list.get(i).getParseGeoPoint("coordinates");

                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(geoPoint.getLatitude(), geoPoint.getLongitude()))
                                .title(list.get(i).getTitle()));
                    }

                }
            });

        }
    }
}
