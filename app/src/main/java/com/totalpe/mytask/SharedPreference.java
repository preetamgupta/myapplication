package com.totalpe.mytask;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPreference {
    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String DETAILS = "Details_List";

    public SharedPreference() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<Details> details) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(details);

        editor.putString(DETAILS, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, Details product) {
        List<Details> details = getFavorites(context);
        if (details == null) {
            details = new ArrayList<Details>();
        }
        details.add(product);
        saveFavorites(context, details);
    }

    public void removeFavorite(Context context, Details details) {
        ArrayList<Details> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(details);
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<Details> getFavorites(Context context) {
        SharedPreferences settings;
        List<Details> details;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(DETAILS)) {
            String jsonFavorites = settings.getString(DETAILS, null);
            Gson gson = new Gson();
            Details[] favoriteItems = gson.fromJson(jsonFavorites,
                    Details[].class);

            details = Arrays.asList(favoriteItems);
            details = new ArrayList<Details>(details);
        } else
            return null;

        return (ArrayList<Details>) details;
    }
}
