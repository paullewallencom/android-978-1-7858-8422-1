package com.packtpub.masteringandroidapp.adapters;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.packtpub.masteringandroidapp.OfferDetailActivity;
import com.packtpub.masteringandroidapp.R;
import com.packtpub.masteringandroidapp.data.JobOffer;

/**
 * Created by antonio on 24/07/2015.
 */
public class JobOfferCursorAdapter extends RecyclerView.Adapter<JobOfferCursorAdapter.MyViewHolder>{

    Cursor mDataCursor;

    @Override
    public int getItemCount() {
        return (mDataCursor == null) ? 0 : mDataCursor.getCount();
    }


    public void changeCursor(Cursor newCursor) {
        //If the cursors are the same do nothing
        if (mDataCursor == newCursor){
            return;
        }

        //Swap the cursors
        Cursor previous = mDataCursor;
        mDataCursor = newCursor;

        //Notify the Adapter to update the new data
        if (mDataCursor != null){
            this.notifyDataSetChanged();
        }

        //Close previous cursor
        if (previous != null) {
            previous.close();
        }
    }


    private JobOffer getItem(int position) {
        mDataCursor.moveToPosition(position);
        return JobOffer.createJobOfferfromCursor(mDataCursor);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_job_offer, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(JobOfferCursorAdapter.MyViewHolder holder, int position) {
        JobOffer jobOffer =  getItem(position);
        holder.textViewName.setText(jobOffer.getTitle());
        holder.textViewDescription.setText(jobOffer.getDescription());
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView textViewName;
        public TextView textViewDescription;

        public  MyViewHolder(View v){
            super(v);
            textViewName = (TextView)v.findViewById(R.id.rowJobOfferTitle);
            textViewDescription = (TextView)v.findViewById(R.id.rowJobOfferDesc);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), OfferDetailActivity.class);
            JobOffer selectedJobOffer = getItem(getAdapterPosition());
            intent.putExtra("job_title", selectedJobOffer.getTitle());
            intent.putExtra("job_description",selectedJobOffer.getDescription());
            intent.putExtra("job_image",selectedJobOffer.getImageLink());
            view.getContext().startActivity(intent);
        }
    }

}