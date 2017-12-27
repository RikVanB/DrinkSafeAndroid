package com.example.rikvanbelle.drinksafe;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rikvanbelle.drinksafe.db.AppDatabase;
import com.example.rikvanbelle.drinksafe.helpers.APIClient;
import com.example.rikvanbelle.drinksafe.helpers.CountUpTimer;
import com.example.rikvanbelle.drinksafe.models.Beer;
import com.example.rikvanbelle.drinksafe.models.Contact;
import com.example.rikvanbelle.drinksafe.models.DrinkActivity;
import com.example.rikvanbelle.drinksafe.models.Message;
import com.example.rikvanbelle.drinksafe.models.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    CountUpTimer timer;
    TextView timer_view;
    DrinkActivity drinkActivity;

    APIClient client;

    User currentUser;
    List<Contact> contacts;
    ListView lv;

    Button startButton;
    Button overviewButton;
    Button stopButton;

    Spinner spinner;

    SearchView searchView;
    TextView currentAvg;

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_with_search);
        setSupportActionBar(myToolbar);

        startButton = (Button) findViewById(R.id.start_button);
        overviewButton = (Button) findViewById(R.id.overview_button);
        stopButton = (Button) findViewById(R.id.stop_button);
        spinner = (Spinner) findViewById(R.id.hours_spinner);

        currentAvg = findViewById(R.id.alcohol_percentage);

        if (checkIfUserExist()) {
            currentUser = AppDatabase.getAppDatabase(getApplicationContext()).userDAO().getAll().get(0);
        }

        searchView = (SearchView) findViewById(R.id.search_view_beer);
        timer_view = (TextView) findViewById(R.id.timer_view);
        initTimer();

        lv = (ListView) findViewById(R.id.founded_beer);

        client = new APIClient(this);
        setSearchView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HomeActivity.this, DetailBeerActivity.class);
                Beer beer = (Beer) adapterView.getItemAtPosition(i);
                intent.putExtra("selectedBeer", beer);
                intent.putExtra("drinkActivity", drinkActivity);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.personal_settings:
                Intent settingsIntent = new Intent(this, PersonalSettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.message_settings:
                Intent messageSettingsIntent = new Intent(this, MessageSettingsActivity.class);
                startActivity(messageSettingsIntent);
                return true;
        }
        return false;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                drinkActivity = (DrinkActivity) data.getSerializableExtra("drinkActivity");
                currentAvg.setText(Double.toString(drinkActivity.getAlcoholPercentage()) + " ‰");
            }
        }
    }

    public void onStartActivity(View view) {
        if (checkIfUserExist()) {
            if (currentUser == null) {
                currentUser = AppDatabase.getAppDatabase(getApplicationContext()).userDAO().getAll().get(0);
            }
            contacts = AppDatabase.getAppDatabase(getApplicationContext()).contactDAO().getAll();
            drinkActivity = new DrinkActivity(currentUser);
            timer.start();
            startButton.setEnabled(false);
            overviewButton.setEnabled(true);
            stopButton.setEnabled(true);
        }
    }

    public void onOverview(View view) {
        //TODO: overzicht van gedronken dranken
        Intent intent = new Intent(this, OverviewDrinkActivity.class);
        intent.putExtra("listOfBeers", drinkActivity.getListOfBeers());
        startActivity(intent);
    }

    public void onStopActivity(View view) {
        //TODO: stop activiteit
        timer.stop();
        drinkActivity.stopActivity();
        TextView alcoholPercentage = (TextView) findViewById(R.id.alcohol_percentage);
        alcoholPercentage.setText("0 ‰");

        AppDatabase.getAppDatabase(getApplicationContext()).drinkActivityDAO().insertAll(drinkActivity);
        List<DrinkActivity> drinkActivityList = AppDatabase.getAppDatabase(getApplicationContext()).drinkActivityDAO().getAll();
        lv.setAdapter(null);

        Message mes = AppDatabase.getAppDatabase(getApplicationContext()).messageDAO().messageForStop();
        if (mes != null) {
            SmsManager manager = SmsManager.getDefault();
            for (Contact c : contacts) {
                manager.sendTextMessage(c.getNumber(), null, mes.getMessage(), null, null);
            }
        }
        startButton.setEnabled(true);
        overviewButton.setEnabled(false);
        stopButton.setEnabled(false);

    }

    private void initTimer() {
        timer = new CountUpTimer(1000) {
            @Override
            public void onTick(long elapsedTime) {
                Date date = new Date(elapsedTime);
                int aantalUur = Integer.parseInt(Character.toString(spinner.getSelectedItem().toString().charAt(0)));
                if (date.getTime() / (spinner.getSelectedItem().toString().charAt(0) * 60) == 1) {
                    int permissionCheck = ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.SEND_SMS);
                    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                        Message mess = AppDatabase.getAppDatabase(getApplicationContext()).messageDAO().messageForXHours();
                        if (mess != null) {
                            SmsManager manager = SmsManager.getDefault();
                            for (Contact c : contacts) {
                                manager.sendTextMessage(c.getNumber(), null, mess.getMessage(), null, null);
                            }
                        }
                    }
                }
                // formattter
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                // Pass date object
                String formatted = formatter.format(date);
                timer_view.setText(formatted);
            }
        };
    }

    private void setSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (checkIfUserExist()) {
                    client.execute(s);
                }
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private boolean checkIfUserExist() {
        List<User> allUsers = AppDatabase.getAppDatabase(getApplicationContext()).userDAO().getAll();
        if (allUsers.size() == 0) {
            Toast.makeText(this, getString(R.string.NoUserMade), Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}
