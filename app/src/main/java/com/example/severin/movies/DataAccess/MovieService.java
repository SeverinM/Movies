package com.example.severin.movies.DataAccess;
import com.example.severin.movies.Model.MovieModel;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Severin on 09/01/2018.
 */

public interface MovieService {
    String ENDPOINT = "https://api.themoviedb.org/3";

    @GET("/movie/{id}")
    MovieModel detailMovie(@Path("id") Integer id, @Query("api_key") String str,
                           @Query("language") String language);
}
