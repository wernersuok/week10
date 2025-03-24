package com.example.week9;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private ImageView posterImageView;
    private TextView titleTextView;
    private TextView yearTextView;
    private TextView genreTextView;

    public MovieViewHolder(View itemView) {
        super(itemView);
        posterImageView = itemView.findViewById(R.id.posterImageView);
        titleTextView = itemView.findViewById(R.id.titleTextView);
        yearTextView = itemView.findViewById(R.id.yearTextView);
        genreTextView = itemView.findViewById(R.id.genreTextView); // constructor for the MovieViewHolder
    }

    public void bind(Movie movie) {
        titleTextView.setText(movie.getTitle() != null ? movie.getTitle() : "Unknown title");
        yearTextView.setText(movie.getYear() != null ? movie.getYear().toString() : "Unknown year");
        genreTextView.setText(movie.getGenre() != null ? movie.getGenre() : "Unknown genre"); // binds movie data to the views in this ViewHolder.

        if (movie.getPosterResource() != null && !movie.getPosterResource().isEmpty()) {
            Context context = itemView.getContext();
            int resourceId = context.getResources().getIdentifier(
                    movie.getPosterResource(), "drawable", context.getPackageName()); // sets poster image or placeholder image for movie

            if (resourceId != 0) {
                posterImageView.setImageResource(resourceId);
            } else {
                posterImageView.setImageResource(R.drawable.placeholder_poster);
            }
        } else {
            posterImageView.setImageResource(R.drawable.placeholder_poster);
        }
    }
}
