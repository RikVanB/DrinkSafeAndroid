package com.example.rikvanbelle.drinksafe;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.rikvanbelle.drinksafe.db.AppDatabase;
import com.example.rikvanbelle.drinksafe.models.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PersonalSettingsActivity extends AppCompatActivity {

    EditText first_name;
    EditText last_name;
    EditText email;
    EditText password;
    EditText dateOfBirth;
    EditText weight;
    EditText length;
    char gender = 'M';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_settings);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "DrinkSafe").build();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        weight = findViewById(R.id.weight);
        length = findViewById(R.id.length);

        List<User> users = AppDatabase.getAppDatabase(getApplicationContext()).userDAO().getAll();
        if(users.size() > 0){
            initiateFields(users.get(0));
        }
    }
    private void initiateFields(User user){
        first_name.setText(user.getFirst_name());
        last_name.setText(user.getLast_name());
        email.setText(user.geteMail());
        password.setText(user.getPassword());
        dateOfBirth.setText(user.getDateOfBirth().toString());
        weight.setText(Double.toString(user.getWeight()));
        length.setText(Double.toString(user.getLength()));
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        if(view.getId() == R.id.woman){
            gender = 'V';
        }
    }

    public void onConfirm(View v){
        Date birth = new Date();
        Double weightD = Double.parseDouble(weight.getText().toString());
        Double lengthD = Double.parseDouble(length.getText().toString());
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            birth = format.parse(dateOfBirth.getText().toString());
        } catch (ParseException exc){
            Log.d("ERROR", exc.getMessage());
        }

        User user = new User(first_name.getText().toString(), last_name.getText().toString(), email.getText().toString(), password.getText().toString(), birth, weightD, lengthD,gender);
        AppDatabase.getAppDatabase(getApplicationContext()).userDAO().insertAll(user);
        Toast.makeText(this, getString(R.string.UserIsAdded), Toast.LENGTH_SHORT).show();
        finish();
    }
}
