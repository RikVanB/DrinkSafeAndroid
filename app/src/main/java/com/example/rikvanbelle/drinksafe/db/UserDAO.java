package com.example.rikvanbelle.drinksafe.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.rikvanbelle.drinksafe.models.User;

import java.util.List;

/**
 * Created by Rik Van Belle on 24/11/2017.
 */
@Dao
public interface UserDAO {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE eMail = :email AND password = :password")
    List<User> checkLogin(String email, String password);

    @Insert
    void insertAll(User... users);

    @Update
    void updateUsers(User... users);

    @Delete
    void delete(User user);
}
