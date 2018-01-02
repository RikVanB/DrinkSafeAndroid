package com.example.androidwear.models;

import java.io.Serializable;

/**
 * Created by Rik Van Belle on 24/11/2017.
 */

public class Beer implements Serializable {
    private String name;
    private double alcoholPercentage;
    private String description;
    private String urlImage;
    public Beer(String name, double alcoholPercentage, String description, String urlImage) {
        this.name = name;
        this.alcoholPercentage = alcoholPercentage;
        this.description = description;
        this.urlImage = urlImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(double alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Beer beer = (Beer) o;

        if (Double.compare(beer.alcoholPercentage, alcoholPercentage) != 0) return false;
        return name != null ? name.equals(beer.name) : beer.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(alcoholPercentage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "name='" + name + '\'' +
                ", alcoholPercentage=" + alcoholPercentage +
                ", description='" + description + '\'' +
                '}';
    }
}
