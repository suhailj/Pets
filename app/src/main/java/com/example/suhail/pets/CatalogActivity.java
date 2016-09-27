package com.example.suhail.pets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.suhail.pets.data.PetContract.PetEntry;
import com.example.suhail.pets.data.PetDbHelper;

import java.util.ArrayList;


public class CatalogActivity extends AppCompatActivity {
    PetDbHelper mDbHelper = new PetDbHelper(this);
    public void insertDummyData(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String name = "DOG";
        String breed = "dkdsbk";
        float weight = (float) 55.5;
        values.put(PetEntry.COLUMN_NAME,name);
        values.put(PetEntry.COLUMN_BREED, breed);
        values.put(PetEntry.COLUMN_GENDER,PetEntry.GENDER_MALE);
        values.put(PetEntry.COLUMN_WEIGHT,weight);
        long newRowId = db.insert(PetEntry.TABLE_NAME,null,values);
    }
    public void deleteAllPets(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL("delete from " + PetEntry.TABLE_NAME);
    }
    public void displayPets(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {PetEntry._ID,
                PetEntry.COLUMN_NAME,
                PetEntry.COLUMN_BREED,
                PetEntry.COLUMN_GENDER,
                PetEntry.COLUMN_WEIGHT};
        String sortOrder = PetEntry._ID + " DESC";
        Cursor cPet = db.query(PetEntry.TABLE_NAME,projection,null,null,null,null,sortOrder);
        // display row count
        TextView view = (TextView) findViewById(R.id.text);
        view.setText("The row count is-" + cPet.getCount());
        //         display pets detail
        ArrayList<String> petsList = new ArrayList<>();
        if(cPet.moveToFirst()) {
            do{
                String id = cPet.getString(cPet.getColumnIndexOrThrow(PetEntry._ID));
                String name = cPet.getString(cPet.getColumnIndexOrThrow(PetEntry.COLUMN_NAME));
                String breed = cPet.getString(cPet.getColumnIndexOrThrow(PetEntry.COLUMN_BREED));
                int gender = cPet.getInt(cPet.getColumnIndexOrThrow(PetEntry.COLUMN_GENDER));
                float weight = cPet.getFloat(cPet.getColumnIndexOrThrow(PetEntry.COLUMN_WEIGHT));
                petsList.add(id + " - " +name + ", " + breed + ", " + gender + ", " + weight);
            } while (cPet.moveToNext());
        }
        ArrayAdapter<String> petsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,petsList);
        ListView listView = (ListView)findViewById(R.id.petsList);
        listView.setAdapter(petsAdapter);
        cPet.close();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this,EditorialActivity.class );
                startActivity(intent);
            }
        });
        displayPets();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.delete_all_the_pets:
                deleteAllPets();
                displayPets();
                break;
            case R.id.insert_dummy_data:
                insertDummyData();
                // update pet count
                displayPets();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
