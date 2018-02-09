package com.example.severin.movies.DataAccess;

import com.example.severin.movies.AsyncTasks.MovieDetailTask;
import com.example.severin.movies.Model.KeyAndId;
import com.example.severin.movies.Model.MovieModel;
import com.example.severin.movies.RecyclerView.MoviesAdapterList;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Severin on 11/01/2018.
 */

public class MovieRest {

    static private String key = "0206ae42d01c36ec57d7597cea007e91";

    public static MovieModel getMovie(Integer id, MoviesAdapterList.Language language)
            throws ExecutionException, InterruptedException{
        KeyAndId input  = new KeyAndId(key,id,language.toString());

        MovieModel model;
        try{
            model = new MovieDetailTask().execute(input).get(750, TimeUnit.MILLISECONDS);
        }
        catch(TimeoutException e){
            model = MovieModel.UNDEFINED;
        }
        return model;
    }
}
