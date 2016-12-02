package com.lesson_three.eiko.book_listing;

import android.app.LoaderManager;
import android.content.*;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<List_item>> {

    String TAG = "main_activity";
    private static String URL_BASE =
            "https://www.googleapis.com/books/v1/volumes?q=";
    private List_Adapter mAdapter;
    private EditText edittxtSearch;
    private TextView noDatatxt;
    String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);

        noDatatxt = (TextView) findViewById(R.id.no_conecction_message);
        mAdapter = new List_Adapter(this, new ArrayList<List_item>());
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(mAdapter);

        ConnectivityManager connectManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectManager.getActiveNetworkInfo();
        final LoaderManager lodermamagerager = getLoaderManager();
        lodermamagerager.initLoader(0, null, this);

        Button buttonSearch = (Button) findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittxtSearch = (EditText) findViewById(R.id.edittxtt_search);
                search = edittxtSearch.getText().toString();
                if (search.length() > 0) {
                    search.replace(" ", "+");
//                    search = URL_BASE + search;
//
//                    ConnectivityManager connectManager = (ConnectivityManager)
//                            getSystemService(Context.CONNECTIVITY_SERVICE);
//                    NetworkInfo networkInfo = connectManager.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        getLoaderManager().restartLoader(0, null, MainActivity.this);
                    } else {
                        noDatatxt.setText("not conected");
                    }
                }
            }
        });

//        mAdapter = new List_Adapter(this, new ArrayList<List_item>());
//        listview = (ListView)findViewById(R.id.listview);
//        listview.setAdapter(mAdapter);
//        noDatatxt = (TextView)findViewById(R.id.no_conecction_message);
//        listview.setEmptyView(noDatatxt);
//        ConnectivityManager connectManager = (ConnectivityManager)
//                getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectManager.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnected()) {
//            getLoaderManager().initLoader(0, null, this);  // .forceLoad();
//        } else if (networkInfo == null) {
//            noDatatxt.setText("not getting data from google...");
//        }
    }

    @Override
    public Loader<List<List_item>> onCreateLoader(int i, Bundle bundle) {
       return new Booklist_Loader(this, URL_BASE + search);
    }

    @Override
    public void onLoadFinished(Loader<List<List_item>> loader, List<List_item> list_items) {
        Log.v(TAG,"pass onLoadFinished");
       // mAdapter.clear();
        // noDatatxt.setText(R.string.no_data_message);
        if (list_items != null && !list_items.isEmpty()) {
            noDatatxt.setVisibility(View.GONE);
            mAdapter.addAll(list_items);
        }else{
            noDatatxt.setVisibility(View.VISIBLE);
            noDatatxt.setText("nothing to show..");
        }
    }

    @Override
    public void onLoaderReset(Loader<List<List_item>> loader) {
        mAdapter.clear();
    }
}
