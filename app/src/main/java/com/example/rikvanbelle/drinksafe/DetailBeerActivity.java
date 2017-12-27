package com.example.rikvanbelle.drinksafe;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rikvanbelle.drinksafe.models.Beer;
import com.example.rikvanbelle.drinksafe.models.DrinkActivity;
import com.squareup.picasso.Picasso;

public class DetailBeerActivity extends AppCompatActivity {
    ImageView imageView;
    TextView nameBeerView;
    TextView avgBeerView;
    TextView descriptionView;

    Beer selectedBeer;
    DrinkActivity currentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_beer);

        selectedBeer = (Beer) this.getIntent().getSerializableExtra("selectedBeer");
        currentActivity = (DrinkActivity) this.getIntent().getSerializableExtra("drinkActivity");
        nameBeerView = findViewById(R.id.beer_name_detailed);
        imageView = findViewById(R.id.beer_image_detailed);
        avgBeerView = findViewById(R.id.beer_avg_detailed);
        descriptionView = findViewById(R.id.beer_description_detailed);

        nameBeerView.setText(selectedBeer.getName());
        avgBeerView.setText(Double.toString(selectedBeer.getAlcoholPercentage()));
        descriptionView.setText(selectedBeer.getDescription());
        Picasso.with(this).load(selectedBeer.getUrlImage()).into(imageView);
    }

    public void addBeerToList(View v){
        Intent intent = new Intent();
        currentActivity.addBeerToList(selectedBeer);
        Toast.makeText(this, selectedBeer.getName() + " " + getString(R.string.isAdded), Toast.LENGTH_SHORT).show();
        intent.putExtra("drinkActivity", currentActivity);
        setResult(RESULT_OK, intent);
        finish();
    }
}
