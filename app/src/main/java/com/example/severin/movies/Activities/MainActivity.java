package com.example.severin.movies.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.severin.movies.DataAccess.DataCache;
import com.example.severin.movies.DataAccess.DatabaseDescription;
import com.example.severin.movies.DataAccess.IdsDatabase;
import com.example.severin.movies.RecyclerView.MoviesAdapterList;
import com.example.severin.movies.R;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String currentSelection;
    public static DataCache cache = new DataCache();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.items_name,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = adapterView.getItemAtPosition(i).toString();
                refresh(selected);
                currentSelection = selected;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    public void refresh(String selected){
        ArrayList<Integer> allInt = new ArrayList<>();
        switch (selected){
            case "Hot":
                allInt.add(550);
                allInt.add(551);
                allInt.add(552);
                allInt.add(553);
                allInt.add(560);
                break;
            case "Random":
                Random rand = new Random();
                for (int j = 0 ; j < 7; j++){
                    allInt.add(rand.nextInt(400000));
                }
                break;
            case "Favorites":
                SQLiteDatabase db = new IdsDatabase(getBaseContext()).getReadableDatabase();
                Cursor curs = db.query(DatabaseDescription.TABLE_NAME,
                        new String[]{DatabaseDescription.COLUMN_NAME},
                        null,
                        null,
                        null,
                        null,
                        null);
                while (curs.moveToNext()){
                    allInt.add(curs.getInt(
                            curs.getColumnIndex(DatabaseDescription.COLUMN_NAME)));
                }
                break;
        }
        RecyclerView rec = (RecyclerView)findViewById(R.id.recyclerView);
        ((MoviesAdapterList)rec.getAdapter()).setDatas(allInt);
    }

    @Override
    public void onResume(){
        super.onResume();
        if (currentSelection == "Favorite")
            refresh(currentSelection);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        cache.deleteAll();
    }
}
