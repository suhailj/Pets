package com.example.suhail.pets.data;

import android.provider.BaseColumns;

/**
 * Created by suhail on 24-09-2016.
 */
public final class PetContract {
    private PetContract(){}
    public static class PetEntry implements BaseColumns{
        public static final String TABLE_NAME = "pets";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_BREED = "breed";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_WEIGHT = "weight";
        // constants for gender
        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
        // constants as uri codes
        public static final int PETS = 100;
        public static final int PET_ID = 101;
        // Constant fot content authority
        public static final String PET_CONTENT_AUTHORITY = "com.example.suhail.pets";
    }
}
