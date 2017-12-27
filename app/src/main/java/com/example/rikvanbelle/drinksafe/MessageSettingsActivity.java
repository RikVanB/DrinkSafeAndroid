package com.example.rikvanbelle.drinksafe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rikvanbelle.drinksafe.db.AppDatabase;
import com.example.rikvanbelle.drinksafe.helpers.AdapterContacts;
import com.example.rikvanbelle.drinksafe.models.Beer;
import com.example.rikvanbelle.drinksafe.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class MessageSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_settings);

        ListView listview = (ListView) findViewById(R.id.overview_message_settings);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0: //CLICKED ON CONTACT LIST
                        Intent intentContactList = new Intent(MessageSettingsActivity.this, ListContactsActivity.class);
                        startActivity(intentContactList);
                        break;
                    case 1: //CLICKED ON MESSAGE SETTINGS
                        Intent intentMessageSettings = new Intent(MessageSettingsActivity.this, SendMessageSettingsActivity.class);
                        startActivity(intentMessageSettings);
                        break;
                }

            }
        });
    }


}
