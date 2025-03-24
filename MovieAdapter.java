package com.example.week9;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view); // returns a new MovieViewHolder that holds a View of the given view type
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie); //Binds movie data to the ViewHolder at the specified position.
    }

    @Override
    public int getItemCount() {
        return movies.size(); // Returns the total number of items in the data set.
    }

    public void updateMovies(List<Movie> movies) {
        this.movies = movies; // updates the list of movies
    }
}
