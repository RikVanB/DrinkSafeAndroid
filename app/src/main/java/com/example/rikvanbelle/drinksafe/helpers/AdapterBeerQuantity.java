package com.example.rikvanbelle.drinksafe.helpers;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rikvanbelle.drinksafe.R;
import com.example.rikvanbelle.drinksafe.models.Beer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Rik Van Belle on 20/12/2017.
 */

public class AdapterBeerQuantity extends ArrayAdapter<Beer> {

    private HashMap<Beer, Integer> beersWithQuantity;

    public AdapterBeerQuantity(Activity activity, int textViewResourceId, ArrayList<Beer> _lBeer) {
        super(activity, textViewResourceId, _lBeer);
    }

    public void setBeersWithQuantity(HashMap<Beer, Integer> beers) {
        beersWithQuantity = beers;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.row_beer_quantity_layout, null);
        }

        ArrayList<Integer> quantities = new ArrayList<>();
        for (Map.Entry<Beer, Integer> map : beersWithQuantity.entrySet()){
            quantities.add(map.getValue());
        }
        ArrayList<Beer> beers = new ArrayList<Beer>();

        //https://stackoverflow.com/questions/12960265/retrieve-all-values-from-hashmap-keys-in-an-arraylist-java
        for (Beer beer : beersWithQuantity.keySet()) {
            beers.add(beer);
        }

        ImageView imageView = (ImageView) v.findViewById(R.id.beer_label);
        TextView name = (TextView) v.findViewById(R.id.beer_name);
        TextView avg = (TextView) v.findViewById(R.id.beer_avg);
        TextView qnty = (TextView) v.findViewById(R.id.beer_quantity);

        //http://square.github.io/picasso/
        Picasso.with(v.getContext()).load(beers.get(position).getUrlImage()).into(imageView);
        name.setText(beers.get(position).getName());
        avg.setText(Double.toString(beers.get(position).getAlcoholPercentage()) + " ‰");
        qnty.setText(R.string.quantity + ": " + Integer.toString(quantities.get(position)));

        return v;
    }
}
