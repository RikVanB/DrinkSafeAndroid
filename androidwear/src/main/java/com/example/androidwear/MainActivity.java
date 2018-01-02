package com.example.androidwear;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidwear.helpers.APIClient;

public class MainActivity extends WearableActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enables Always-on
        setAmbientEnabled();
    }

    public void onSearch(View v){
        Intent intent = new Intent(this, OverviewBeerActivity.class);
        EditText queryBeer = findViewById(R.id.query);
        intent.putExtra("query", queryBeer.getText().toString());
        startActivity(intent);
    }
}
