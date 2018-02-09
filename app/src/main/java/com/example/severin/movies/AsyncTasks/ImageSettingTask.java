package com.example.severin.movies.AsyncTasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.severin.movies.Model.ViewAndUrl;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Severin on 21/01/2018.
 */

public class ImageSettingTask extends AsyncTask<ViewAndUrl,Void,Bitmap> {
    URL url;
    ImageView img;

    @Override
    protected Bitmap doInBackground(ViewAndUrl...params) {
        url = params[0].getURL();
        img = params[0].getView();
        try {
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }
        catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap map){
        img.setImageBitmap(map);
    }
}
