package com.packtpub.masteringandroidapp;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.packtpub.masteringandroidapp.data.JobOffer;
import com.packtpub.masteringandroidapp.utils.ImageUtils;
import com.parse.ParseAnalytics;

import java.util.HashMap;
import java.util.Map;


public class OfferDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);

        String job_title = getIntent().getStringExtra("job_title");

        Map<String, String> eventParams = new HashMap<>();
        eventParams.put("job_title", job_title);
        ParseAnalytics.trackEventInBackground("job_visited", eventParams);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsingtoolbar);
        collapsingToolbar.setTitle(job_title);

        String imageLink = getIntent().getStringExtra("job_image");
        ImageView imageViewLogo = (ImageView) findViewById(R.id.logo);

        displayImageFromUrl(imageViewLogo, imageLink);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void displayImageFromUrl(ImageView imageView, String link){

        new AsyncTask<Object,Void,Bitmap>(){

            ImageView imageView;
            String link;

            @Override
            protected Bitmap doInBackground(Object... params) {
                imageView = (ImageView) params[0];
                link = (String) params[1];

                return ImageUtils.getImage(link);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                imageView.setImageBitmap(bitmap);
            }

        }.execute(imageView,link);

    }


    public void displayImageWithVolley(final ImageView imageView, String url){

        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        //imageView.setImageResource(R.drawable.image_load_error);
                    }
                });

        MAApplication.getInstance().getRequestQueue().add(request);
    }

}
