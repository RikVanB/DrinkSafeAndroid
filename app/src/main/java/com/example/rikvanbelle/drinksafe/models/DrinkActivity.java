package com.example.rikvanbelle.drinksafe.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.rikvanbelle.drinksafe.HomeActivity;
import com.example.rikvanbelle.drinksafe.db.AppDatabase;
import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rik Van Belle on 24/11/2017.
 */
@Entity
public class DrinkActivity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int drinkActivityId;
    @Ignore
    private User currentUser;
    @TypeConverters(DateTypeConverter.class)
    @ColumnInfo(name = "start_time")
    private Date startTime;
    @TypeConverters(DateTypeConverter.class)
    @ColumnInfo(name = "end_time")
    private Date endTime;
    @Ignore
    private HashMap<Beer, Integer> listOfBeers = new HashMap<Beer, Integer>();
    @ColumnInfo(name = "avg")
    private double alcoholPercentage;

    public DrinkActivity(){

    }

    public DrinkActivity(User user){
        this.currentUser = user;
        startTime = Calendar.getInstance().getTime();
    }

    public HashMap<Beer, Integer> getListOfBeers(){
        return listOfBeers;
    }

    public double getAlcoholPercentage(){
        return alcoholPercentage;
    }

    public void stopActivity(){
        endTime = Calendar.getInstance().getTime();
        updateAlcoholPercentage();
    }

    private void updateAlcoholPercentage(){
        //TODO: FORMULE VOOR ALCOHOLPERCENTAGE TE BEREKENEN
        //(A x 5.14 / W * r) - 0.015 * H

        double liquidOuncesOfAlcoholConsumed= 0;
        for(Beer beer : listOfBeers.keySet()){
            int value = listOfBeers.get(beer);
            double ounces = 12 * beer.getAlcoholPercentage();
            liquidOuncesOfAlcoholConsumed += ounces * value;
        }
        long difference = startTime.getTime() - Calendar.getInstance().getTime().getTime();
        long hours = TimeUnit.MILLISECONDS.toHours(difference);
        double weightPounds = currentUser.getWeight() * 2.204;
        double genderFactor = 0;
        if(currentUser.getGender() == 'M'){
            genderFactor = 0.73;
        } else {
            genderFactor = 0.66;
        }
        double BAC = ((liquidOuncesOfAlcoholConsumed * 5.14) / (weightPounds * genderFactor)) - 0.15 * hours;

        this.alcoholPercentage = BAC;

        /*
        A = liquid ounces of alcohol consumed
        W = a person’s weight in pounds
        r = a gender constant of alcohol distribution (.73 for men and .66 for women)*
        H = hours elapsed since drinking commenced
        */
    }

    public void addBeerToList(Beer beer){
        if(listOfBeers.containsKey(beer)){
            int value = listOfBeers.get(beer);
            value++;
            listOfBeers.put(beer, value);
        } else {
            listOfBeers.put(beer, 1);
        }
        updateAlcoholPercentage();
    }

    public int getDrinkActivityId() {
        return drinkActivityId;
    }

    public void setDrinkActivityId(int drinkActivityId) {
        this.drinkActivityId = drinkActivityId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setAlcoholPercentage(double alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }
}
