package com.example.rikvanbelle.drinksafe.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rik Van Belle on 24/11/2017.
 */
@Entity
public class User implements Serializable {
    public enum Gender{
        MEN(0),
        WOMAN(1);

        private int code;
        Gender(int code){
            this.code = code;
        }
        public int getCode(){return code;}
    }

    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name="first_name")
    private String first_name;
    @ColumnInfo(name="last_name")
    private String last_name;
    @ColumnInfo(name="eMail")
    public String eMail;
    @ColumnInfo(name="password")
    private String password;
    @ColumnInfo(name="date_of_birth")
    @TypeConverters(DateTypeConverter.class)
    private Date dateOfBirth;
    @ColumnInfo(name="weight")
    private double weight;
    @ColumnInfo(name="length")
    private double length;
    @ColumnInfo(name="gender")
    private char gender;

    @Ignore
    public User() {

    }

    public User(String first_name, String last_name, String eMail, String password, Date dateOfBirth, double weight, double length, char gender) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.eMail = eMail;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.length = length;
        this.gender = gender;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", weight=" + weight +
                ", length=" + length +
                '}';
    }
}
