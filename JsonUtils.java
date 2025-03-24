package com.example.week9;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static List<Movie> loadMoviesFromJson(Context context) {
        List<Movie> movieList = new ArrayList<>();
        String jsonString = null;

        try {
            InputStream inputStream = context.getAssets().open("movies.json");

            int size = inputStream.available();
            byte[] buffer = new byte[size];

            inputStream.read(buffer);

            inputStream.close();

            jsonString = new String(buffer, StandardCharsets.UTF_8);

            JSONArray jsonArray = new JSONArray(jsonString); // reads json file and converts to string

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject movieJson = jsonArray.getJSONObject(i); // iterates through the json file to extract movie data

                String title = null;
                if (movieJson.has("title") && !movieJson.isNull("title")) {
                    title = movieJson.getString("title");
                }

                Integer year = null;
                try {
                    if (movieJson.has("year") && !movieJson.isNull("year")) {
                        year = movieJson.getInt("year");

                        if (year != null && year < 0) {
                            year = Math.abs(year); // extracts year if available and converts to positive value if negative
                        }
                    }
                } catch (JSONException e) {
                    Log.e("JsonUtisl", "error parsing integer");
                }

                String genre = null;
                if (movieJson.has("genre") && !movieJson.isNull("genre")) {
                    genre = movieJson.getString("genre");
                }

                String poster = null;
                if (movieJson.has("poster") && !movieJson.isNull("poster")) {
                    poster = movieJson.getString("poster");
                }

                Movie movie = new Movie(title, year, genre, poster);
                movieList.add(movie);
            }


        } catch (IOException | JSONException e) {
            e.printStackTrace(); // handles exception related to file I/O or JSON parsing
        }

        return movieList;
    }
}
