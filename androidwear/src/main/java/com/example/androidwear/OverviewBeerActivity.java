package com.example.androidwear;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidwear.helpers.APIClient;

public class OverviewBeerActivity extends WearableActivity {
    private ListView listViewBeers;
    private APIClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_beer);

        apiClient = new APIClient(this);

        Intent intent = this.getIntent();
        String query = intent.getStringExtra("query").replaceAll("\\s+","");

        apiClient.execute(query);

        // Enables Always-on
        setAmbientEnabled();
    }
}
