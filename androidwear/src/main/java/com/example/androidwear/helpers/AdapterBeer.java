package com.example.androidwear.helpers;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidwear.R;
import com.example.androidwear.models.Beer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Rik Van Belle on 24/11/2017.
 */

public class AdapterBeer extends ArrayAdapter<Beer> {
    private Activity currentActivity;
    private ArrayList<Beer> listBeer;
    private static LayoutInflater inflater = null;

    public AdapterBeer(Activity activity, int textViewResourceId, ArrayList<Beer> _lBeer) {
        super(activity, textViewResourceId, _lBeer);
        this.listBeer = _lBeer;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.row_beer_layout, null);
        }

        Beer beer = listBeer.get(position);
        ImageView imageView = (ImageView) v.findViewById(R.id.beer_label);
        TextView name = (TextView) v.findViewById(R.id.beer_name);
        TextView avg = (TextView) v.findViewById(R.id.beer_avg);

        //http://square.github.io/picasso/
        Picasso.with(v.getContext()).load(beer.getUrlImage()).into(imageView);
        name.setText(beer.getName());
        avg.setText(Double.toString(beer.getAlcoholPercentage()) + " ‰");

        return v;
    }
}
