package com.example.severin.movies.RecyclerView;


import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.severin.movies.Activities.MainActivity;
import com.example.severin.movies.DataAccess.DataCache;
import com.example.severin.movies.DataAccess.MovieRest;
import com.example.severin.movies.Model.MovieModel;
import com.example.severin.movies.R;
import com.example.severin.movies.RecyclerView.movieViewHolder;

import java.util.ArrayList;

/**
 * Created by Severin on 11/01/2018.
 */

public class MoviesAdapterList extends RecyclerView.Adapter<movieViewHolder> {

    public enum Language{

        FRENCH("FR"),
        GERMAN("DE"),
        ENGLISH("US");

        private String name = "";
        Language(String name){
            this.name = name;
        }
        public String toString(){
            return name;
        }
    }

    private Language currentLang = Language.FRENCH;
    private ArrayList<Integer> allId = new ArrayList<>();
    Context ctx;

    public MoviesAdapterList(ArrayList<Integer> list, Context ctx){
        allId = list;
        this.ctx = ctx;
    }

    @Override
    public movieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new movieViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_summary,parent,false));
    }

    @Override
    public void onBindViewHolder(movieViewHolder holder, int position){

        MovieModel model;
        try {
            model = MainActivity.cache.getMovie(allId.get(position),ctx);

            //Cache failed , loading from the API
            if (model == null){
                model = MovieRest.getMovie(allId.get(position),currentLang);
                MainActivity.cache.saveMovie(allId.get(position),model,ctx);
            }
        }
        catch(Exception e){
            model = MovieModel.UNDEFINED;
        }
        holder.bind(model);
    }

    public void setDatas(ArrayList<Integer> newList){
        allId = newList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return allId.size();
    }

    public void end(ProgressDialog dialog){
        dialog.hide();
    }
}
