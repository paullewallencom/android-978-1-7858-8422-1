package com.packtpub.masteringandroidapp.fragments;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import com.packtpub.masteringandroidapp.MAAProvider;
import com.packtpub.masteringandroidapp.R;
import com.packtpub.masteringandroidapp.adapters.CursorRecyclerAdapter;
import com.packtpub.masteringandroidapp.adapters.JobOfferCursorAdapter;
import com.packtpub.masteringandroidapp.adapters.JobOffersAdapter;
import com.packtpub.masteringandroidapp.data.JobOffer;
import com.packtpub.masteringandroidapp.database.DatabaseContract;
import com.packtpub.masteringandroidapp.database.MasteringAndroidDAO;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends android.support.v4.app.Fragment implements LoaderManager.LoaderCallbacks<Cursor>, SwipeRefreshLayout.OnRefreshListener {

    public List<JobOffer> mListItems;
    public RecyclerView mRecyclerView;

    private JobOfferCursorAdapter mAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    public static final int MAA_LOADER = 1;


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
        mRecyclerView.setHasFixedSize(false);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new JobOfferCursorAdapter();
        mRecyclerView.setAdapter(mAdapter);

        getLoaderManager().initLoader(MAA_LOADER, null, this);

        retrieveJobOffers();

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrieveJobOffers();
            }
        });

        return view;
    }


    public void retrieveJobOffers(){
        ParseQuery<JobOffer> query = ParseQuery.getQuery("JobOffer");
        query.findInBackground(new FindCallback<JobOffer>() {

            @Override
            public void done(List<JobOffer> jobOffersList, ParseException e) {
                Log.d("ListFragment","Storing offers :"+jobOffersList.size());
                MasteringAndroidDAO.getInstance().clearDB(getActivity());
                MasteringAndroidDAO.getInstance().storeOffers(getActivity(), jobOffersList);
                getActivity().getContentResolver().notifyChange(Uri.parse(MAAProvider.JOIN_TABLE_URI), null);
                mSwipeRefreshLayout.setRefreshing(false);
            }

        });
    }

    public void showOffersFromDB(){
        mListItems = MasteringAndroidDAO.getInstance().getOffersFromDB(getActivity());
        JobOffersAdapter adapter = new JobOffersAdapter(mListItems);
        mRecyclerView.setAdapter(adapter);
    }

    public void showOffersFromContentProvider(){
        ContentResolver cr = getActivity().getContentResolver();
        Uri uriPath = Uri.parse("content://" + MAAProvider.authority + "/" + DatabaseContract.OFFER_JOIN_COMPANY);
        Cursor cursor = cr.query(uriPath, null, null, null, null);

        List<JobOffer> jobOfferList = new ArrayList<>();
        while (cursor.moveToNext()) {

            JobOffer offer = new JobOffer();
            offer.setTitle(cursor.getString(cursor.getColumnIndex(DatabaseContract.JobOfferTable.TITLE)));
            offer.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseContract.JobOfferTable.DESC)));
            offer.setType(cursor.getString(cursor.getColumnIndex(DatabaseContract.JobOfferTable.TYPE)));
            offer.setSalary(cursor.getString(cursor.getColumnIndex(DatabaseContract.JobOfferTable.SALARY)));
            offer.setLocation(cursor.getString(cursor.getColumnIndex(DatabaseContract.JobOfferTable.LOCATION)));
            offer.setCompany(cursor.getString(cursor.getColumnIndex(DatabaseContract.CompanyTable.NAME)));
            offer.setImageLink(cursor.getString(cursor.getColumnIndex(DatabaseContract.CompanyTable.IMAGE_LINK)));

            jobOfferList.add(offer);
        }
        JobOffersAdapter adapter = new JobOffersAdapter(jobOfferList);
        mRecyclerView.setAdapter(adapter);
    }


    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderID, Bundle bundle)
    {
        Log.d("ListFragment", "Creatin loader :");
        switch (loaderID) {
            case MAA_LOADER:
                Uri uriPath = Uri.parse("content://" + MAAProvider.authority + "/" + DatabaseContract.OFFER_JOIN_COMPANY);
                return new CursorLoader(
                        getActivity(),   // Parent activity context
                        uriPath,  // Table to query
                        null,            // Projection to return
                        null,            // No selection clause
                        null,            // No selection arguments
                        null             // Default sort order
                );
            default:
                //Invalid ID
                return null;
        }
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.d("ListFragment", "OnLoader Finished :" + cursor.getCount());
        mAdapter.changeCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.changeCursor(null);
        Log.d("ListFragment", "OnLoader Reset :");
    }


    @Override
    public void onRefresh() {
        retrieveJobOffers();
    }
}
