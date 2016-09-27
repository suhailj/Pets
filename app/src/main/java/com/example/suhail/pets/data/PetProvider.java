package com.example.suhail.pets.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import static com.example.suhail.pets.data.PetContract.PetEntry.PETS;
import static com.example.suhail.pets.data.PetContract.PetEntry.PET_ID;
import static com.example.suhail.pets.data.PetContract.PetEntry.TABLE_NAME;

/**
 * Created by suhail on 27-09-2016.
 */

public class PetProvider extends ContentProvider {
    // Create a Uri matcher
    private static final UriMatcher petUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        // uri for pets table
        petUriMatcher.addURI("com.example.suhail.pets",TABLE_NAME,PETS);
        // uri for pets table row
        petUriMatcher.addURI("com.example.suhail.",TABLE_NAME,PET_ID);
    }
    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
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
