package com.eso.countriesapp.api;

import com.eso.countriesapp.model.MovieDBResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/popular")
    Call<MovieDBResponse> getPopulartMovies(@Query("api_key")String apikey);
}
