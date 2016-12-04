package com.lesson_three.eiko.book_listing;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by eiko on 11/26/2016.
 */
public class List_item {

    private String mTitle;
    private ArrayList<String> mAuthor;
    private String mInfomation;

    public List_item(String title, ArrayList<String> author,
                     String info ) {
        mTitle = title;
        mAuthor = author;
        mInfomation = info;
    }

    public String getmTitle() {
        return mTitle;
    }

    public ArrayList<String> getmAuthor() {
        return mAuthor;
    }

    public String getmInfomation() {
        return mInfomation;
    }




}
