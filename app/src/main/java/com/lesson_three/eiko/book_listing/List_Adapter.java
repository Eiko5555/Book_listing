package com.lesson_three.eiko.book_listing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eiko on 11/26/2016.
 */
public class List_Adapter extends ArrayAdapter<List_item>{

    public List_Adapter(Context contex, List<List_item> listitem){
        super(contex, 0, listitem);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);

            List_item currentItem = getItem(position);

            TextView txtAuthor = (TextView) listItemView.findViewById(
                    R.id.txt_author);
            ArrayList<String> bookAuthorList = currentItem.getmAuthor();
            StringBuilder stringbuilderAuthor = new StringBuilder();
            for (int i = 0; i < bookAuthorList.size(); i++) {
                stringbuilderAuthor.append(bookAuthorList.get(i));
                if (bookAuthorList.size()>0 && i< bookAuthorList.size()-1){
                stringbuilderAuthor.append(", ");
                }
            }
            txtAuthor.setText(stringbuilderAuthor.toString());

            TextView txtTitle = (TextView)listItemView.findViewById(
                    R.id.txt_title);
            txtTitle.setText(currentItem.getmTitle());

            TextView txtInfo = (TextView)listItemView.findViewById(
                    R.id.txt_bookinfo);
            txtInfo.setText(currentItem.getmInfomation());
        }
        return listItemView;
    }
}
