package com.example.rikvanbelle.drinksafe;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.rikvanbelle.drinksafe.helpers.AdapterBeer;
import com.example.rikvanbelle.drinksafe.helpers.AdapterBeerQuantity;
import com.example.rikvanbelle.drinksafe.models.Beer;

import java.util.ArrayList;
import java.util.HashMap;

public class OverviewDrinksFragment extends Fragment {

    ListView listViewBeers;


    public interface OnBeerDetailListener {
        public void onRegisterButtonListener(View v);
    }

    OnBeerDetailListener mCallback;

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_overview_drinks);

        listViewBeers = (ListView) findViewById(R.id.list_beers);

        Intent intent = this.getIntent();
        HashMap<Beer, Integer> listOfBeers = (HashMap<Beer, Integer>) intent.getSerializableExtra("listOfBeers");

        ArrayList<Beer> allBeers = new ArrayList<Beer>();
        allBeers.addAll(listOfBeers.keySet());

        AdapterBeerQuantity adapterBeer = new AdapterBeerQuantity(this, R.layout.row_beer_quantity_layout, allBeers);
        adapterBeer.setBeersWithQuantity(listOfBeers);
        listViewBeers.setAdapter(adapterBeer);
    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview_drinks, container, false);
    }
}
