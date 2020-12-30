package com.packtpub.masteringandroidapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.packtpub.masteringandroidapp.data.JobOffer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonio on 25/01/2015.
 */
public class MasteringAndroidDAO {

    /**
     * Singleton pattern
     */
    private static MasteringAndroidDAO sInstane = null;

    /**
     * Get an instance of the Database Access Object
     *
     * @return instance
     */
    public static MasteringAndroidDAO getInstance(){
        if (sInstane == null){
            sInstane = new MasteringAndroidDAO();
        }
        return sInstane;
    }

    public boolean storeOffers(Context context, List<JobOffer> offers){

        try {
            SQLiteDatabase db = new DBOpenHelper(context).getWritableDatabase();

            db.beginTransaction();

            for (JobOffer offer : offers){

                ContentValues cv_company = new ContentValues();
                cv_company.put(DatabaseContract.CompanyTable.NAME, offer.getCompany());
                cv_company.put(DatabaseContract.CompanyTable.IMAGE_LINK,offer.getImageLink());

                int id = findCompanyId(db,offer);

                if (id < 0) {
                    id = (int) db.insert(DatabaseContract.CompanyTable.TABLE_NAME,null,cv_company);
                }

                ContentValues cv = new ContentValues();
                cv.put(DatabaseContract.JobOfferTable.TITLE,offer.getTitle());
                cv.put(DatabaseContract.JobOfferTable.DESC,offer.getDescription());
                cv.put(DatabaseContract.JobOfferTable.TYPE, offer.getType());
                cv.put(DatabaseContract.JobOfferTable.DESC, offer.getType());
                cv.put(DatabaseContract.JobOfferTable.SALARY,offer.getSalary());
                cv.put(DatabaseContract.JobOfferTable.LOCATION,offer.getLocation());
                cv.put(DatabaseContract.JobOfferTable.COMPANY_ID,id);

                db.insert(DatabaseContract.JobOfferTable.TABLE_NAME,null,cv);
            }

            db.setTransactionSuccessful();
            db.endTransaction();

            db.close();

        } catch ( Exception e){
            Log.d("MasteringAndroidDAO", e.toString());
            return false;
        }

        return true;
    }

    public int findCompanyId(SQLiteDatabase db, JobOffer offer){
        Cursor cursorCompany = db.query(DatabaseContract.CompanyTable.TABLE_NAME,
                new String[]{"rowid"},
                DatabaseContract.CompanyTable.NAME +" LIKE ?",
                new String[]{offer.getCompany()},
                null,null,null);

        int id = -1;

        if (cursorCompany.moveToNext()){
            id = cursorCompany.getInt(0);
        }

        cursorCompany.close();
        return id;
    }


    public synchronized List<JobOffer> getOffersFromDB(Context context){

        SQLiteDatabase db = new DBOpenHelper(context).getWritableDatabase();

        String join = DatabaseContract.JobOfferTable.TABLE_NAME + " JOIN " +
                DatabaseContract.CompanyTable.TABLE_NAME + " ON " +
                DatabaseContract.JobOfferTable.TABLE_NAME+"."+DatabaseContract.JobOfferTable.COMPANY_ID
                +" = " + DatabaseContract.CompanyTable.TABLE_NAME+".rowid";

        Cursor cursor = db.query(join,null,null,null,null,null,null);

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

        cursor.close();
        db.close();

        return jobOfferList;
    }



    /**
     * Remove all offers and companies
     */
    public void clearDB(Context context)
    {
        SQLiteDatabase db = new DBOpenHelper(context).getWritableDatabase();
        // db.delete(String tableName, String whereClause, String[] whereArgs);
        // If whereClause is null, it will delete all rows.
        db.delete(DatabaseContract.JobOfferTable.TABLE_NAME, null, null);
        db.delete(DatabaseContract.CompanyTable.TABLE_NAME, null, null);
    }

}
