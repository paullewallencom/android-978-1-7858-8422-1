package com.packtpub.masteringandroidapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.packtpub.masteringandroidapp.MainActivity;
import com.packtpub.masteringandroidapp.OfferDetailActivity;
import com.packtpub.masteringandroidapp.R;
import com.packtpub.masteringandroidapp.data.JobOffer;

import java.util.List;

/**
 * Created by antonio on 03/06/2015.
 */
public class JobOffersAdapter extends RecyclerView.Adapter<JobOffersAdapter.MyViewHolder>  {

    private  List<JobOffer> mOfferList;

    public JobOffersAdapter(List<JobOffer> offersList) {
        this.mOfferList = offersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_job_offer, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textViewName.setText(mOfferList.get(position).getTitle());
        holder.textViewDescription.setText(mOfferList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mOfferList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        public TextView textViewName;
        public TextView textViewDescription;

        public  MyViewHolder(View v){
            super(v);
            textViewName = (TextView)v.findViewById(R.id.rowJobOfferTitle);
            textViewDescription = (TextView)v.findViewById(R.id.rowJobOfferDesc);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OfferDetailActivity.class);
                JobOffer selectedJobOffer = mOfferList.get(getPosition());
                intent.putExtra("job_title", selectedJobOffer.getTitle());
                intent.putExtra("job_description",selectedJobOffer.getDescription());
                
                view.getContext().startActivity(intent);
        }

        @Override
        public boolean onLongClick(View view) {
            mOfferList.remove(getPosition());
            notifyItemRemoved(getPosition());
            return true;
        }
    }
}
