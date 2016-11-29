package com.lesson_three.eiko.book_listing;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by eiko on 11/26/2016.
 */
public class Loader extends AsyncTaskLoader<List<List_item>> {

String mUrl;
    private Loader(Context contx, String url){
        super(contx);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<List_item> loadInBackground() {
        List<List_item> books = Query.fetchBookdata(mUrl);
        return books;
    }
}
