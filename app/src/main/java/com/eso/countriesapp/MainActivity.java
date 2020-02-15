package com.eso.countriesapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.eso.countriesapp.adapter.MovieAdapter;
import com.eso.countriesapp.api.ApiManager;
import com.eso.countriesapp.model.Movie;
import com.eso.countriesapp.model.MovieDBResponse;
import com.eso.countriesapp.utils.Command;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    ArrayList<Movie> movies;
    SwipeRefreshLayout swipeRefreshLayout;
    MovieAdapter movieAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("TMDB Popular MovieDBResponse Today");
        getPopularMovies();
        swipeRefreshLayout = findViewById(R.id.mSwipeRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void getPopularMovies() {
        ApiManager.getInstance().apiInterface().getPopulartMovies("2d4bb3c1287f6bcf575fa1b30b9a82bc")
                .enqueue(new Callback<MovieDBResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<MovieDBResponse> call, @NotNull Response<MovieDBResponse> response) {
                        MovieDBResponse movieDBResponse = response.body();
                        if (movieDBResponse != null && movieDBResponse.getMovies() != null) {
                            movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                            showOnRecyclerView();
                            onClickItem();
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<MovieDBResponse> call, Throwable t) {
                        swipeRefreshLayout.setRefreshing(true);
                    }
                });
    }


    private void showOnRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        }else {
            recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        movieAdapter = new MovieAdapter(MainActivity.this,movies);
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);

    }

    private void onClickItem() {
        movieAdapter.setmOnClickItemListener(new MovieAdapter.OnClickItemListener() {
            @Override
            public void onClickItem(View view, int position) {
                if (position != RecyclerView.NO_POSITION){
                    Movie movie= movies.get(position);
                    Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                    intent.putExtra(Command.MOVIES,movie);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        getPopularMovies();

    }
}
