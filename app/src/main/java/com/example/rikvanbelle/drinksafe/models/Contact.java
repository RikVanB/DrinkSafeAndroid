package com.example.rikvanbelle.drinksafe.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by Rik Van Belle on 7/12/2017.
 */
@Entity
public class Contact implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private int contactID;
    @ColumnInfo(name="name")
    private String name;
    @ColumnInfo(name="number")
    private String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
