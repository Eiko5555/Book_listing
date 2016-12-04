package com.lesson_three.eiko.book_listing;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

/**
 * Created by eiko on 11/26/2016.
 */
public class Booklist_Loader extends AsyncTaskLoader<List<List_item>> {
private String TAG = "Booklist loader";
String mUrl;
    public Booklist_Loader(Context contx, String url){
        super(contx);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.v(TAG,"passing onStartLoading");
        forceLoad();
    }

    @Override
    public List<List_item> loadInBackground() {
        if (mUrl == null){
            return  null;
        }
        Log.v(TAG,"passing loadInBackground");
        Log.v(TAG,"mURL: "+mUrl);
        List<List_item> books = Query.fetchBookdata(mUrl);
        //Log.v(TAG,"books: " + books.toString());
        return books;
    }
}
