package com.example.suhail.pets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.suhail.pets.data.PetContract.PetEntry;
import com.example.suhail.pets.data.PetDbHelper;

public class EditorialActivity extends AppCompatActivity {
    private int mGender = 0;
    PetDbHelper mDbHelper = new PetDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editorial);
        setSpinnerForGender();
    }

    private void setSpinnerForGender() {
        Spinner genderSpinner = (Spinner) findViewById(R.id.Gender_spinner);
        ArrayAdapter<CharSequence> genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender_spinner_array, android.R.layout.simple_spinner_dropdown_item);
        genderSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderSpinnerAdapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).toString().equals("Unknown"))
                    mGender = PetEntry.GENDER_UNKNOWN;
                else if (parent.getItemAtPosition(position).toString().equals("Male"))
                    mGender = PetEntry.GENDER_MALE;
                else mGender = PetEntry.GENDER_FEMALE;
//                Log.v("gender", "onItemSelected: "+gender);
//                Toast.makeText(parent.getContext(),"selected gender is"+ gender, Toast.LENGTH_LONG).show();
//                Toast.makeText(parent.getContext(),"item"+ parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_a_pet, menu);
        return true;
    }
    public void savePetInfo(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        EditText name = (EditText)findViewById(R.id.pet_name);
        EditText breed = (EditText)findViewById(R.id.pet_breed);
        EditText weight = (EditText)findViewById(R.id.pet_weight);
        ContentValues values = new ContentValues();
        values.put(PetEntry.COLUMN_NAME, String.valueOf(name.getText()));
        values.put(PetEntry.COLUMN_BREED, String.valueOf(breed.getText()));
        values.put(PetEntry.COLUMN_GENDER,mGender);
        values.put(PetEntry.COLUMN_WEIGHT,Float.valueOf(String.valueOf(weight.getText())));
        long newRowId = db.insert(PetEntry.TABLE_NAME,null,values);
        // make toast
        Toast.makeText(this,"Pet created id " + newRowId ,Toast.LENGTH_LONG).show();
        // intent back to catalog activity
        Intent intent = new Intent(EditorialActivity.this,CatalogActivity.class);
        startActivity(intent);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_item_add_a_pet_delete:
                // add code to execute when menu item delete is selected.
                break;
            case R.id.add_a_pet_toolbar_save_icon:
                // add code to execute when menu item icon "save" is selected.
                savePetInfo();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
