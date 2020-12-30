package com.packtpub.masteringandroidapp.adapters;

import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.packtpub.masteringandroidapp.R;
import com.packtpub.masteringandroidapp.data.Country;
import com.packtpub.masteringandroidapp.data.JobContact;


import java.util.List;

/**
 * Created by antonio on 30/05/2015.
 */
public class JobContactsAdapter extends BaseAdapter {

    private List<Object> mItemsList;
    private Context mContext;

    public JobContactsAdapter(List<Object> list, Context context){
        mItemsList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mItemsList.size();
    }

    @Override
    public Object getItem(int i) {
        return mItemsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        //Not needed
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return mItemsList.get(position) instanceof Country ? 0 : 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        switch (getItemViewType(i)){

            case (0) :
                CountryViewHolder holderC;
                if (view == null){
                    view = View.inflate(mContext, R.layout.row_job_country,null);
                    holderC = new CountryViewHolder();
                    holderC.name = (TextView) view.findViewById(R.id.rowJobCountryTitle);
                    holderC.flag = (ImageView) view.findViewById(R.id.rowJobCountryImage);
                    view.setTag(view);
                } else {
                    holderC = (CountryViewHolder) view.getTag();
                }
                holderC.bindView((Country)mItemsList.get(i));
                break;
            case (1) :
                CompanyViewHolder holder;
                if (view == null){
                    view = View.inflate(mContext, R.layout.row_job_contact,null);
                    holder = new CompanyViewHolder();
                    holder.name = (TextView) view.findViewById(R.id.rowJobContactName);
                    holder.email = (TextView) view.findViewById(R.id.rowJobContactEmail);
                    holder.desc = (TextView) view.findViewById(R.id.rowJobContactDesc);
                    view.setTag(holder);
                } else {
                    holder = (CompanyViewHolder) view.getTag();
                }
                holder.bindView((JobContact)mItemsList.get(i));
        }

        return view;
    }

    private class CountryViewHolder{

        public TextView name;
        public ImageView flag;

        public void bindView(Country country){
            this.name.setText(country.getName());
            this.flag.setImageResource(country.getImageRes(mContext));
        }

    }

    private class CompanyViewHolder{

        public TextView name;
        public TextView email;
        public TextView desc;

        public void bindView(JobContact company){
            this.name.setText(company.getName());
            this.email.setText(company.getEmail());
            this.desc.setText(company.getDescription());
        }

    }
}
