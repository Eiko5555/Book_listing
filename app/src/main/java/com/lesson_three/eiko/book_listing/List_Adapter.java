package com.lesson_three.eiko.book_listing;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by eiko on 11/26/2016.
 */
public class List_Adapter extends ArrayAdapter<List_item>{

    public List_Adapter(Activity contex,
                        ArrayList<List_item> listitem){
        super(contex, 0, listitem);
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView ==null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
            List_item currentItem = getItem(position);

            TextView txtAuthor = (TextView)listItemView.findViewById(
                    R.id.txt_author);
            txtAuthor.setText(currentItem.getmAuthor());

            TextView txtTitle = (TextView)listItemView.findViewById(
                    R.id.txt_title);
            txtTitle.setText(currentItem.getmTitle());

            TextView txtInfo
                    = (TextView)listItemView.findViewById(
                    R.id.txt_bookinfo);
            txtInfo.setText(currentItem.getmInfomation());
        }

        return listItemView;
    }
}
