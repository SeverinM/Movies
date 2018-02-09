package com.example.severin.movies.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.severin.movies.Activities.MainActivity;
import com.example.severin.movies.DataAccess.DataCache;
import com.example.severin.movies.DataAccess.DatabaseDescription;
import com.example.severin.movies.DataAccess.IdsDatabase;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Severin on 09/01/2018.
 */

public class MovieModel implements Serializable{

    public static MovieModel UNDEFINED = new MovieModel(0,"<Undefined>","<Undefined>");
    private Integer id;
    private String title;
    private String overview;
    private String backdrop_path;
    private String release_date = "00/00/0000";

    public MovieModel(Integer firstId, String firstTitle, String firstOverview){
        id = firstId;
        title = firstTitle;
        overview = firstOverview;
    }

    //Getters
    public String getUrlImg(){return backdrop_path;}
    public Integer getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getRelease(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM");

        try {
            Date date = format.parse(release_date);
            return format.format(date);
        }
        catch (ParseException e){
            return "<Unknown>";
        }
    }
    public String getOverviewRepr(){
        if (overview.trim() == "")
            return "<No overview>";
        return overview;
    }

    public String getOverview(){
        return overview;
    }

    public Boolean getIsFavorite(Context ctx){
        if (id == null){
            return false;
        }
        SQLiteDatabase db = new IdsDatabase(ctx).getReadableDatabase();
        String[] projection = {DatabaseDescription.COLUMN_NAME};
        String selection = DatabaseDescription.COLUMN_NAME + " = ?";
        String[] selectionArgs = {id.toString()};

        Cursor cursor = db.query(
                DatabaseDescription.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        boolean output = cursor.moveToNext();
        return (output);
    }

    //Setters
    public void ToggleFavorite(Context ctx){
        boolean value = !getIsFavorite(ctx);

        //Wasn't on database , will be added
        if (value){
            SQLiteDatabase db = new IdsDatabase(ctx).getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseDescription.COLUMN_NAME,id);
            if (db.insert(DatabaseDescription.TABLE_NAME,null,values) == -1){
                throw new RuntimeException("Insertion failed");
            }
        }

        //Was on database , will be deleted
        else{
            SQLiteDatabase db = new IdsDatabase(ctx).getWritableDatabase();
            String selection = DatabaseDescription.COLUMN_NAME + " = ? ";
            String[] selectionArgs = {id.toString()};
            if (db.delete(DatabaseDescription.TABLE_NAME,selection,selectionArgs) == 0){
                throw new RuntimeException("The database deleted nothing");
            }
        }
    }
}
