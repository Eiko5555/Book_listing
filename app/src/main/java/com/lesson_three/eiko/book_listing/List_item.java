package com.lesson_three.eiko.book_listing;

/**
 * Created by eiko on 11/26/2016.
 */
public class List_item {
    private String mAuthor;
    private String mTitle;
    private String mInfomation;
    private String mUrl;
    private long mTime;

    public List_item(String author, String title,
                     String info ) {
        mAuthor = author;
        mTitle = title;
        mInfomation = info;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmInfomation() {
        return mInfomation;
    }

    public String getmUrl() {
        return mUrl;
    }

    public long getmTime() {
        return mTime;
    }
}
