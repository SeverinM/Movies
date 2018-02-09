package com.example.severin.movies.Model;

/**
 * Created by Severin on 11/01/2018.
 */

public class KeyAndId {
    private String key;
    private Integer id;
    private String lang;

    public KeyAndId(String key,Integer id, String lang){
        this.key = key;
        this.id = id;
        this.lang = lang;
    }

    public String getKey(){return key;}
    public Integer getId(){return id;}
    public String getLang(){return lang;}
}
