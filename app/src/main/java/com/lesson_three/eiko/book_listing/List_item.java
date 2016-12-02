package com.lesson_three.eiko.book_listing;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by eiko on 11/26/2016.
 */
public class List_item {
    private ArrayList<String> mAuthor;
    private String mTitle;
    private String mInfomation;

    public List_item(ArrayList<String> author, String title,
                     String info ) {
        mAuthor = author;
        mTitle = title;
        mInfomation = info;
    }

    public ArrayList<String> getmAuthor() {
        return mAuthor;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmInfomation() {
        return mInfomation;
    }




}
