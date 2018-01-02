package com.example.rikvanbelle.drinksafe;

import com.example.rikvanbelle.drinksafe.models.Beer;
import com.example.rikvanbelle.drinksafe.models.DrinkActivity;
import com.example.rikvanbelle.drinksafe.models.User;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class DrinkActivityTest {
    User user = new User("Rik", "Van Belle", "rik_van_belle@live.be", "test", new Date(), 68, 180, 'M');
    DrinkActivity drinkActivity = new DrinkActivity(user);

    @Test
    public void addBeer() throws Exception {
        Beer beerOne = new Beer("Witkap", 5.4, "Bier uit Ninove", "");
        Beer beerTwo = new Beer("Jupiler", 4.6, "Gewoon bier", "");
        drinkActivity.addBeerToList(beerOne);
        assertEquals(drinkActivity.getListOfBeers().size(), 1);
        drinkActivity.addBeerToList(beerOne);
        assertEquals(drinkActivity.getListOfBeers().size(), 1);
        drinkActivity.addBeerToList(beerTwo);
        assertEquals(drinkActivity.getListOfBeers().size(), 2);
    }

    @Test
    public void checkTime() throws Exception {
        assertNotNull(drinkActivity.getStartTime());
        assertNull(drinkActivity.getEndTime());

        drinkActivity.stopActivity();
        assertNotNull(drinkActivity.getEndTime());
    }
}