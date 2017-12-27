package com.example.rikvanbelle.drinksafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.rikvanbelle.drinksafe.db.AppDatabase;
import com.example.rikvanbelle.drinksafe.models.Contact;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddContactActivity extends AppCompatActivity {

    ArrayList<Contact> currentContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        currentContacts = (ArrayList<Contact>) this.getIntent().getSerializableExtra("contacts");

    }

    public void addContactToList(View v){
        Intent intent = new Intent();

        EditText nameContact = (EditText) findViewById(R.id.name_contact);
        EditText numberContact = (EditText) findViewById(R.id.number_contact);


        Contact contact = new Contact(nameContact.getText().toString(), numberContact.getText().toString());
        AppDatabase.getAppDatabase(getApplicationContext()).contactDAO().insertAll(contact);
        finish();
    }
}
