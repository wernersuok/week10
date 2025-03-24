package com.example.week9;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView movieRecyclerView;
    private MovieAdapter adapter;
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        movieRecyclerView = findViewById(R.id.movie_recycler_view); //initializes recycler view

        movies = new ArrayList<>(); //initializes the movie list

        setUpRecyclerView(); // sets up recyclerview with layout manager and adapter

        loadMovieData(); // loads the movies from the json file
    }

    public void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); // sets up the recyclerview with linear layout manager
        movieRecyclerView.setLayoutManager(layoutManager);

        adapter = new MovieAdapter(movies);
        movieRecyclerView.setAdapter(adapter);
    }

    public void loadMovieData() {

        try {
            List<Movie> loadedMovies = JsonUtils.loadMoviesFromJson(this); // loads movies using JsonUtils
            if (loadedMovies != null && !loadedMovies.isEmpty()) {
                movies.clear();
                movies.addAll(loadedMovies);
                adapter.updateMovies(movies); // updates the UI if the movies were loaded successfully
            } else {
                showError("No movies loaded");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showError("Error loading movies: " + e.getMessage());
        }
    }

    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}