package com.example.rikvanbelle.drinksafe.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.example.rikvanbelle.drinksafe.models.Message;
import com.example.rikvanbelle.drinksafe.models.User;

import java.util.List;

/**
 * Created by Rik Van Belle on 9/12/2017.
 */
@Dao
public interface MessageDAO {
    @Query("SELECT * FROM message")
    List<Message> getAll();

    @Insert
    void insertAll(Message... messages);

    @Query("SELECT * FROM message WHERE id = 0")
    Message messageForXHours();

    @Query("SELECT * FROM message WHERE id = 1")
    Message messageForStop();
}
