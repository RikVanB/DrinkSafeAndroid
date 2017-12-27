package com.example.rikvanbelle.drinksafe.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.rikvanbelle.drinksafe.models.DrinkActivity;

import java.util.List;

/**
 * Created by Rik Van Belle on 24/11/2017.
 */

@Dao
public interface DrinkActivityDAO {
    @Query("SELECT * FROM drinkactivity")
    List<DrinkActivity> getAll();

    @Insert
    void insertAll(DrinkActivity... drinkActivities);

    @Update
    void updateUsers(DrinkActivity... drinkActivities);

    @Delete
    void delete(DrinkActivity drinkActivity);
}
