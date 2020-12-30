package com.packtpub.masteringandroidapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by antonio on 25/01/2015.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    /**
     * DATABASE VERSION
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * TABLE STRINGS
     */
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA = ", ";

    /**
     * SQL CREATE TABLE sentences
     */
    private static final String CREATE_JOB_OFFER_TABLE = "CREATE TABLE "
            + DatabaseContract.JobOfferTable.TABLE_NAME + " ("
            + BaseColumns._ID + INTEGER_TYPE +" PRIMARY KEY AUTOINCREMENT " + COMMA
            + DatabaseContract.JobOfferTable.TITLE + TEXT_TYPE + COMMA
            + DatabaseContract.JobOfferTable.DESC + TEXT_TYPE + COMMA
            + DatabaseContract.JobOfferTable.TYPE + TEXT_TYPE + COMMA
            + DatabaseContract.JobOfferTable.SALARY + TEXT_TYPE + COMMA
            + DatabaseContract.JobOfferTable.LOCATION + TEXT_TYPE + COMMA
            + DatabaseContract.JobOfferTable.COMPANY_ID + INTEGER_TYPE + " )";

    private static final String CREATE_COMPANY_TABLE = "CREATE TABLE "
            + DatabaseContract.CompanyTable.TABLE_NAME + " ("
            + DatabaseContract.CompanyTable.NAME + TEXT_TYPE + COMMA
            + DatabaseContract.CompanyTable.IMAGE_LINK + TEXT_TYPE +  " )";


    /**
     * SQL DELETE TABLE SENTENCES
     */
    public static final String DROP_JOB_OFFER_TABLE = "DROP TABLE IF EXISTS "+ DatabaseContract.JobOfferTable.TABLE_NAME;
    public static final String DROP_COMAPNY_TABLE = "DROP TABLE IF EXISTS "+ DatabaseContract.CompanyTable.TABLE_NAME;


    public static final String OFFER_JOIN_COMPANY = DatabaseContract.JobOfferTable.TABLE_NAME + " JOIN " +
            DatabaseContract.CompanyTable.TABLE_NAME + " ON " +
            DatabaseContract.JobOfferTable.TABLE_NAME+"."+DatabaseContract.JobOfferTable.COMPANY_ID
            +" = " + DatabaseContract.CompanyTable.TABLE_NAME+".rowid";

    public DBOpenHelper(Context context){
       super(context, DatabaseContract.DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_JOB_OFFER_TABLE);
        db.execSQL(CREATE_COMPANY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_COMAPNY_TABLE);
        db.execSQL(DROP_JOB_OFFER_TABLE);
        onCreate(db);
    }
}
