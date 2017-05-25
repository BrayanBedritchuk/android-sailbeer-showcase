package com.brayanbedritchuk.sailbeer.persistence;

import android.content.Context;

import com.brayanbedritchuk.sailbeer.R;
import com.brayanbedritchuk.sailbeer.model.Beer;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class BeerDAO {

    private Context context;

    private static BeerDAO instance;

    private BeerDAO(Context context) {
        this.context = context.getApplicationContext();
    }

    public static final BeerDAO getInstance(Context context) {
        if (instance == null) {
            instance = new BeerDAO(context);
        }
        return instance;
    }

    public List<Beer> getBeers() throws Exception {
        InputStream raw =  context.getResources().openRawResource(R.raw.beers);
        Reader reader = new BufferedReader(new InputStreamReader(raw));

        BeersJSONList json = new Gson().fromJson(reader, BeersJSONList.class);
        reader.close();

        return json.getBeers();
    }


}
