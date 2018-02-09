package com.example.severin.movies.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.severin.movies.AsyncTasks.ImageSettingTask;
import com.example.severin.movies.Model.MovieModel;
import com.example.severin.movies.R;
import com.example.severin.movies.Model.ViewAndUrl;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Severin on 20/01/2018.
 */

public class DetailActivity extends AppCompatActivity{

    public static String FAVORITE = "Enlever des favoris";
    public static String NOT_FAVORITE = "Ajouter aux favoris";

    static String UrlImg = "https://image.tmdb.org/t/p/w500";
    MovieModel mov;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailled_movie_activity);

        btn = (Button)findViewById(R.id.FavoriteButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mov.ToggleFavorite(getBaseContext());
                UpdateButtonText();
            }
        });

        mov = (MovieModel)getIntent().getSerializableExtra("movie");
        bindDatas();
        UpdateButtonText();
    }


    //Will try to bind datas on the stored model
    public void bindDatas(){
        TextView txt = (TextView)findViewById(R.id.titleDetail);
        txt.setText(mov.getTitle());
        TextView txtSyn = (TextView)findViewById(R.id.synopsis);
        txtSyn.setText(mov.getOverviewRepr());
        TextView txtRelease = (TextView)findViewById(R.id.release);
        txtRelease.setText("Released : " + mov.getRelease());

        ImageView img = (ImageView)findViewById(R.id.imageView);
        try{
            URL url = new URL(UrlImg + mov.getUrlImg());
            new ImageSettingTask().execute(new ViewAndUrl(url,img));
        }
        catch (MalformedURLException e){
            throw new RuntimeException("Malformed URL");
        }
    }

    public void UpdateButtonText(){
        btn.setText(mov.getIsFavorite(getBaseContext()) ? FAVORITE : NOT_FAVORITE);
    }
}
