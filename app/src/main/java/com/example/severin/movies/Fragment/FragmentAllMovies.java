package com.example.severin.movies.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.severin.movies.Activities.MainActivity;
import com.example.severin.movies.RecyclerView.MoviesAdapterList;
import com.example.severin.movies.R;

import java.util.ArrayList;

/**
 * Created by Severin on 14/01/2018.
 */

public class FragmentAllMovies extends Fragment {

    @Override
    public void onCreate(Bundle allData){
        super.onCreate(allData);
    }

    @Override
    public View onCreateView(LayoutInflater layout, ViewGroup grp , Bundle allDatas){
        View view = layout.inflate(R.layout.all_movies_frament,grp,false);
        RecyclerView rec = (RecyclerView) view.findViewById(R.id.recyclerView);
        rec.setHasFixedSize(true);
        ArrayList<Integer> allInt = new ArrayList<>();
        rec.setAdapter(new MoviesAdapterList(allInt,getActivity()));
        rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        MainActivity.cache.deleteAll();
    }
}
