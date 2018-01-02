package com.example.androidwear.helpers;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;


import com.example.androidwear.R;
import com.example.androidwear.models.Beer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

/**
 * Created by Rik Van Belle on 24/11/2017.
 */

public class APIClient extends AsyncTask<String, Integer, ArrayList<Beer>> {
    private Activity activity;

    private ArrayList<Beer> list = new ArrayList<>();
    private ListView lv;


    public APIClient(Activity a) {
        this.lv = a.findViewById(R.id.beers);
        activity = a;
    }

    @Override
    protected void onPreExecute() {
        //super.onPreExecute();
    }

    @Override
    protected ArrayList<Beer> doInBackground(String[] query) {
        String url = "https://api.untappd.com/v4/search/beer?q=" + query[0] + "&client_id=FBB7594D7BFE0771B6B56DD0BF19F1229A2C1091&client_secret=218594F573A7D983F9C6A6C6EC784DAF4F19DE93";
        HttpClient httpClient = new DefaultHttpClient();
        String result = "";
        try {
            HttpResponse httpResponse = httpClient.execute(new HttpGet(url));
            InputStream inputStream = httpResponse.getEntity().getContent();
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null)
                result += line;
            inputStream.close();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
            return null;
        }
        try {
            JSONObject jsonResponse = new JSONObject(result);
            return pretifyJSON(jsonResponse);
        } catch (JSONException exc) {
            System.out.println(exc.getMessage());
            return null;
        }
    }

    private ArrayList<Beer> pretifyJSON(JSONObject object) {
        list = new ArrayList<Beer>();
        try {
            JSONObject response = object.getJSONObject("response");
            JSONObject beers = response.getJSONObject("beers");
            int count = beers.getInt("count");
            JSONArray beerItems = beers.getJSONArray("items");
            System.out.println("Aantal: " + count);
            for (int i = 0; i < count; i++) {
                JSONObject beerObject = beerItems.getJSONObject(i);
                JSONObject beer = beerObject.getJSONObject("beer");
                String beer_name = beer.getString("beer_name");
                double alchohol = beer.getDouble("beer_abv");
                String description = beer.getString("beer_description");
                String urlImage = beer.getString("beer_label");
                Beer foundedBeer = new Beer(beer_name, alchohol, description, urlImage);
                System.out.println(foundedBeer);
                list.add(foundedBeer);
            }
        } catch (JSONException exc) {
            System.out.println(exc.getMessage());
            return null;
        }
        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<Beer> beers) {
        System.out.println(beers.toString());

        AdapterBeer adapterBeer = new AdapterBeer(activity, android.R.layout.simple_list_item_1, beers);
        lv.setAdapter(adapterBeer);
    }
}
