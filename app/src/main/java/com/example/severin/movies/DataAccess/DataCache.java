package com.example.severin.movies.DataAccess;

import android.content.Context;
import android.graphics.Movie;
import android.icu.text.LocaleDisplayNames;
import android.provider.MediaStore;
import android.support.annotation.IntegerRes;
import android.util.Log;

import com.example.severin.movies.Model.MovieModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by semichaut on 07/02/18.
 */

public class DataCache {
    public static String FilePrefix = "CacheMovie-";
    public static int Size = 30;

    ArrayList<Integer> savedIntegers = new ArrayList<>();

    public MovieModel getMovie(int id, Context ctx){

        FileInputStream fis = null;
        ObjectInputStream is = null;
        MovieModel movie = null;
        try {
            fis = ctx.openFileInput(FilePrefix + String.valueOf(id));
            is = new ObjectInputStream(fis);
            movie = (MovieModel) is.readObject();
            is.close();
            fis.close();
            Log.d("CACHE","Cache success");
            savedIntegers.add(id);
        }
        catch (IOException | ClassNotFoundException e){
            movie = null;
            Log.d("CACHE",e.toString());
            Log.d("CACHE","Cache fail");
        }
        return movie;
    }

    public boolean saveMovie(int id, MovieModel mod, Context ctx){

        //Max size reached
        if (savedIntegers.size() > Size){
            Log.d("CACHE","Maximum size reached");
            return false;
        }

        FileOutputStream fos;
        ObjectOutputStream os;

        try{
            fos = ctx.openFileOutput(FilePrefix + String.valueOf(id),Context.MODE_PRIVATE);
            os = new ObjectOutputStream(fos);
            os.writeObject(mod);
            os.close();
            fos.close();
            Log.d("CACHE","save success : " + FilePrefix + String.valueOf(id));
        }
        catch (IOException e){
            Log.d("CACHE",e.toString());
            Log.d("CACHE","save failed");
            return false;
        }
        savedIntegers.add(id);
        return true;
    }

    public boolean deleteMovie(int id){
        File file = new File(FilePrefix + String.valueOf(id));
        return file.delete();
    }

    public void deleteAll(){
        for (Integer id : savedIntegers){
            deleteMovie(id);
        }
    }
}
