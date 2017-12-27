package com.example.rikvanbelle.drinksafe.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.rikvanbelle.drinksafe.models.Contact;
import com.example.rikvanbelle.drinksafe.models.DrinkActivity;
import com.example.rikvanbelle.drinksafe.models.Message;
import com.example.rikvanbelle.drinksafe.models.User;

/**
 * Created by Rik Van Belle on 24/11/2017.
 */

@Database(entities = {User.class, DrinkActivity.class, Contact.class, Message.class}, version = 8)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract UserDAO userDAO();
    public abstract DrinkActivityDAO drinkActivityDAO();
    public abstract ContactDAO contactDAO();
    public abstract MessageDAO messageDAO();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
