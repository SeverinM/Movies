package com.example.severin.movies.RecyclerView;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.severin.movies.Activities.DetailActivity;
import com.example.severin.movies.Model.MovieModel;
import com.example.severin.movies.R;

/**
 * Created by Severin on 11/01/2018.
 */

public class movieViewHolder extends RecyclerView.ViewHolder {

    MovieModel currentModel;

    public movieViewHolder(View itemView){
        super(itemView);
    }

    public void bind(MovieModel model) {
        currentModel = model;
        Button btn = (Button) itemView.findViewById(R.id.button);
        btn.setText(model.getTitle());
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(),DetailActivity.class);
                intent.putExtra("movie",currentModel);
                itemView.getContext().startActivity(intent);
            }
        });
        ImageView view = (ImageView)itemView.findViewById(R.id.imgFavorite);
        view.setVisibility(currentModel.getIsFavorite(itemView.getContext()) ? View.VISIBLE
                : View.INVISIBLE);
    }
}
