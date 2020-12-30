package com.packtpub.masteringandroidapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.packtpub.masteringandroidapp.R;
import com.packtpub.masteringandroidapp.data.JobOffer;
import com.parse.ParseObject;

/**
 * Created by antonio on 28/04/2015.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        view.findViewById(R.id.settingsButtonListExample).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.settingsButtonListExample) :
                showListDialog();
                break;
        }
    }

    /**
     *  Show a dialog with different options to choose from
     */
    public void showListDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1);
        arrayAdapter.add("Option 0");
        arrayAdapter.add("Option 1");
        arrayAdapter.add("Option 2");

        builder.setTitle("Choose an option");

        builder.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(),"Option choosen "+i, Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                });

        builder.show();
    }

}
