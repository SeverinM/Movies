package com.example.severin.movies.DataAccess;

/**
 * Created by semichaut on 02/02/18.
 */

public class DatabaseDescription {
    public static String TABLE_NAME = "Ids";
    public static String COLUMN_NAME = "Identifiant";
    public static String CREATE_TABLE = "Create table " + TABLE_NAME + " ( " + COLUMN_NAME
            + " Integer primary key " + " )";
    public static String DELETE_TABLE = "Drop table if exists " + TABLE_NAME;
}
