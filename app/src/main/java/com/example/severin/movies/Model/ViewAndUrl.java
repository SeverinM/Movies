package com.example.severin.movies.Model;

import android.widget.ImageView;

import java.net.URL;

/**
 * Created by Severin on 21/01/2018.
 */

public class ViewAndUrl{
    private URL url;
    private ImageView img;

    public URL getURL(){
        return url;
    }

    public ImageView getView(){
        return img;
    }

    public ViewAndUrl(URL firstUrl , ImageView firstImg){
        url = firstUrl;
        img = firstImg;
    }
}

