package com.eso.countriesapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.eso.countriesapp.model.Movie;
import com.eso.countriesapp.utils.Command;

public class DetailsActivity extends AppCompatActivity {

    ImageView ivMovieLarge;
    String id, image, name, releaseDate, rate, overview;
    Movie movie;
    Toolbar mToolbar;
    TextView mTvMovieTitle, mTvMovieRating, mTvReleaseDate,mTvPlotsynopsis;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ivMovieLarge = findViewById(R.id.ivMovieLarge);
        mTvMovieTitle = findViewById(R.id.tvMovieTitle);
        mTvMovieRating = findViewById(R.id.tvMovieRating);
        mTvReleaseDate = findViewById(R.id.tvReleaseDate);
        mTvPlotsynopsis = findViewById(R.id.tvPlotsynopsis);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        Intent intent = getIntent();
        if (intent.hasExtra(Command.MOVIES)){
            movie = getIntent().getParcelableExtra(Command.MOVIES);

            image = movie.getPosterPath();
            String path = "https://image.tmdb.org/t/p/w500" + image;
            Glide.with(getApplicationContext()).load(path).into(ivMovieLarge);

            mTvMovieTitle.setText(movie.getTitle());
            mTvMovieRating.setText(Double.toString(movie.getVoteAverage()));
            mTvPlotsynopsis.setText(movie.getOverview());
            mTvReleaseDate.setText(movie.getReleaseDate());

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
