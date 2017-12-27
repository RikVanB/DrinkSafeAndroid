package com.example.rikvanbelle.drinksafe;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.rikvanbelle.drinksafe.db.AppDatabase;
import com.example.rikvanbelle.drinksafe.helpers.AdapterContacts;
import com.example.rikvanbelle.drinksafe.models.Contact;

import java.util.ArrayList;

public class ListContactsActivity extends AppCompatActivity {

    private FloatingActionButton button;
    ArrayList<Contact> contacts = new ArrayList<Contact>();
    ListView contactListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contacts);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_with_search);
        setSupportActionBar(myToolbar);

        button = (FloatingActionButton) findViewById(R.id.addNumber);
        contactListView = (ListView) findViewById(R.id.overview_contacts);

        contacts = (ArrayList<Contact>) AppDatabase.getAppDatabase(getApplicationContext()).contactDAO().getAll();

        AdapterContacts contactsAdapter = new AdapterContacts(this, R.layout.row_contact_layout, contacts);
        contactListView.setAdapter(contactsAdapter);
    }

    public void addContact(View view) {
        Intent intent = new Intent(this, AddContactActivity.class);
        startActivity(intent);
    }
}
