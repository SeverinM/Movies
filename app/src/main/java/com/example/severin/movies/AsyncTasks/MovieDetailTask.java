package com.example.severin.movies.AsyncTasks;

import android.os.AsyncTask;

import com.example.severin.movies.DataAccess.MovieService;
import com.example.severin.movies.Model.KeyAndId;
import com.example.severin.movies.Model.MovieModel;

import java.io.IOException;
import java.text.ParseException;
import java.util.Random;

import retrofit.RestAdapter;
import retrofit.RetrofitError;

/**
 * Created by Severin on 11/01/2018.
 */

public class MovieDetailTask extends AsyncTask<KeyAndId,Void,MovieModel>{

    @Override
    protected MovieModel doInBackground(KeyAndId...param) {
        MovieService movieService = new RestAdapter.Builder()
                .setEndpoint(MovieService.ENDPOINT)
                .build()
                .create(MovieService.class);

        Random rand = new Random();
        MovieModel movieDetail;
        Integer id = param[0].getId();
        Boolean stop;
        int count = 5;

        do {
            try {
                movieDetail = movieService.detailMovie(id,param[0].getKey(), param[0].getLang());
                if (movieDetail.getOverview() == ""){
                    throw new IOException("Not a valid movie");
                }
                stop = true;
            }
            catch (RetrofitError | IOException e) {
                id = rand.nextInt(5000);
                movieDetail = MovieModel.UNDEFINED;
                stop = (--count <= 0);
            }
        } while (!stop);
        return movieDetail;
    }
}

