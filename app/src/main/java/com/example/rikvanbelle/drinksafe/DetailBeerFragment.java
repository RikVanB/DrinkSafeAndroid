package com.example.rikvanbelle.drinksafe;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rikvanbelle.drinksafe.models.Beer;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailBeerFragment extends Fragment {


    public DetailBeerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_beer, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void updateValues(Beer b){
        ImageView imageView;
        TextView nameBeerView;
        TextView avgBeerView;
        TextView descriptionView;

        nameBeerView = getActivity().findViewById(R.id.beer_name_detailed);
        imageView = getActivity().findViewById(R.id.beer_image_detailed);
        avgBeerView = getActivity().findViewById(R.id.beer_avg_detailed);
        descriptionView = getActivity().findViewById(R.id.beer_description_detailed);

        nameBeerView.setText(b.getName());
        avgBeerView.setText(Double.toString(b.getAlcoholPercentage()));
        descriptionView.setText(b.getDescription());
        Picasso.with(getActivity()).load(b.getUrlImage()).into(imageView);
    }
}
