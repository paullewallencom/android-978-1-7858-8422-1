package com.packtpub.masteringandroidapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.packtpub.masteringandroidapp.database.DBOpenHelper;
import com.packtpub.masteringandroidapp.database.DatabaseContract;

/**
 * Created by antonio on 18/07/2015.
 */
public class MAAProvider extends ContentProvider {

    public static String authority = "com.packtpub.masteringandroidapp.MAAProvider";
    private UriMatcher mUriMatcher;

    private static final int COMPANY_TABLE = 0;
    private static final int COMPANY_TABLE_ROW = 1;
    private static final int OFFER_TABLE = 2;
    private static final int OFFER_TABLE_ROW = 3;
    private static final int JOIN_TABLE = 4;
    private static final int JOIN_TABLE_ROW = 5;

    private SQLiteDatabase mDB;

    public static final String JOIN_TABLE_URI =  "content://" + MAAProvider.authority + "/" + DatabaseContract.OFFER_JOIN_COMPANY;

    @Override
    public boolean onCreate() {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(authority,DatabaseContract.CompanyTable.TABLE_NAME,COMPANY_TABLE);
        mUriMatcher.addURI(authority,DatabaseContract.CompanyTable.TABLE_NAME+"/#",COMPANY_TABLE_ROW);
        mUriMatcher.addURI(authority,DatabaseContract.JobOfferTable.TABLE_NAME,OFFER_TABLE);
        mUriMatcher.addURI(authority,DatabaseContract.JobOfferTable.TABLE_NAME+"/#",OFFER_TABLE_ROW);
        mUriMatcher.addURI(authority,DatabaseContract.OFFER_JOIN_COMPANY,JOIN_TABLE);
        mUriMatcher.addURI(authority,DatabaseContract.OFFER_JOIN_COMPANY+"/#",JOIN_TABLE_ROW);

        mDB = new DBOpenHelper(getContext()).getWritableDatabase();

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (mUriMatcher.match(uri)){
            case COMPANY_TABLE:
                return mDB.query(DatabaseContract.CompanyTable.TABLE_NAME, projection,selection,selectionArgs,null,null,sortOrder);
            case COMPANY_TABLE_ROW:
                selection = "rowid LIKE "+uri.getLastPathSegment();
                return mDB.query(DatabaseContract.CompanyTable.TABLE_NAME, projection,selection,selectionArgs,null,null,sortOrder);
            case OFFER_TABLE:
                return mDB.query(DatabaseContract.JobOfferTable.TABLE_NAME, projection,selection,selectionArgs,null,null,sortOrder);
            case OFFER_TABLE_ROW:
                selection = "rowid LIKE "+uri.getLastPathSegment();
                return mDB.query(DatabaseContract.JobOfferTable.TABLE_NAME, projection,selection,selectionArgs,null,null,sortOrder);
            case JOIN_TABLE:
                Cursor cursor =  mDB.query(DBOpenHelper.OFFER_JOIN_COMPANY, projection,selection,selectionArgs,null,null,sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            case JOIN_TABLE_ROW:
                selection = "rowid LIKE "+uri.getLastPathSegment();
                return mDB.query(DBOpenHelper.OFFER_JOIN_COMPANY, projection,selection,selectionArgs,null,null,sortOrder);
        }
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
