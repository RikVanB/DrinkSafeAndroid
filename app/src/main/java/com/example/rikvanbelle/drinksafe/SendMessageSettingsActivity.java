package com.example.rikvanbelle.drinksafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rikvanbelle.drinksafe.db.AppDatabase;
import com.example.rikvanbelle.drinksafe.models.Message;

public class SendMessageSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message_settings);

    }

    public void onSaveMessage(View v){
        EditText sendMessageHours = findViewById(R.id.message_after_hours);
        EditText sendMessageStop = findViewById(R.id.message_after_stop);

        Message messageHours = new Message(0, sendMessageHours.getText().toString());
        Message messageStop = new Message(1, sendMessageHours.getText().toString());

        AppDatabase.getAppDatabase(getApplicationContext()).messageDAO().insertAll(messageHours, messageStop);
        finish();
    }

}
