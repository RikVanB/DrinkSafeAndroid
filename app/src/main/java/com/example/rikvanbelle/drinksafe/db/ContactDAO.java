package com.example.rikvanbelle.drinksafe.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.rikvanbelle.drinksafe.models.Contact;
import com.example.rikvanbelle.drinksafe.models.DrinkActivity;
import com.example.rikvanbelle.drinksafe.models.User;

import java.util.List;

/**
 * Created by Rik Van Belle on 7/12/2017.
 */

@Dao
public interface ContactDAO {
    @Query("SELECT * FROM contact")
    List<Contact> getAll();

    @Insert
    void insertAll(Contact... contacts);
}
