package com.packtpub.masteringandroidapp.database;

/**
 * Created by antonio on 25/01/2015.
 */
public class DatabaseContract {

    public static final String DB_NAME = "mastering_android_app.db";


    public abstract class JobOfferTable {

        public static final String TABLE_NAME = "job_offer_table";

        public static final String TITLE = "title";
        public static final String DESC = "description";
        public static final String TYPE = "type";
        public static final String SALARY = "salary";
        public static final String LOCATION = "location";
        public static final String COMPANY_ID = "company_id";
    }

    public abstract class CompanyTable {

        public static final String TABLE_NAME = "company_table";

        public static final String NAME = "name";
        public static final String IMAGE_LINK = "image_link";
    }

    public static final String OFFER_JOIN_COMPANY = "offer_join_company";
}
