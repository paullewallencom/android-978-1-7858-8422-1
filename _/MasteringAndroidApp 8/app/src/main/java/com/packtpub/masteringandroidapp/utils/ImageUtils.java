package com.packtpub.masteringandroidapp.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by antonio on 01/07/2015.
 */
public class ImageUtils {

    public static Bitmap getImage(String urlString) {

        URL url = null;

        try {
             url = new URL(urlString);
        } catch (MalformedURLException e) {
            return null;
        }

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                return BitmapFactory.decodeStream(connection.getInputStream());
            } else
                return null;
        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
