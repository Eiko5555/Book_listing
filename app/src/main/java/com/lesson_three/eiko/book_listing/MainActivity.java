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
    private Button buttonSearch;
    private TextView noDatatxt;
    ListView listview;
    private String compleateUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);
        noDatatxt = (TextView) findViewById(R.id.no_conecction_message);

        buttonSearch = (Button) findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittxtSearch = (EditText) findViewById(R.id.edittxtt_search);
                final String search = edittxtSearch.getText().toString();
                if (search.length() > 0) {
                    search.replace(" ", "+");
                    compleateUrl = URL_BASE + search;

                    ConnectivityManager connectManager = (ConnectivityManager)
                            getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connectManager.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        LoaderManager lodermamagerager = getLoaderManager();
                        lodermamagerager.restartLoader(0, null, MainActivity.this);
                    }
                    Log.v(TAG, "passing onclick to fetch data");
                    //from this point i cant get through...
                    getLoaderManager().initLoader(0, null, MainActivity.this);
                    Log.v(TAG, "passing loader");
                    Query.fetchBookdata(compleateUrl);
                    mAdapter = new List_Adapter(MainActivity.this, new ArrayList<List_item>());
                    listview = (ListView) findViewById(R.id.listview);
                    listview.setAdapter(mAdapter);
                } else {
                    noDatatxt.setText("not conected");
                }
            }
        });

//        mAdapter = new List_Adapter(this, new ArrayList<List_item>());
//        listview = (ListView)findViewById(R.id.listview);
//        listview.setAdapter(mAdapter);
//        noDatatxt = (TextView)findViewById(R.id.no_conecction_message);
//        listview.setEmptyView(noDatatxt);
        ConnectivityManager connectManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            getLoaderManager().initLoader(0, null, this);  // .forceLoad();
        } else if (networkInfo == null) {
            noDatatxt.setText("not getting data from google...");
        }
    }

    @Override
    public Loader<List<List_item>> onCreateLoader(int i,
                                                  Bundle bundle) {
        //return null;
        return new Booklist_Loader(this, compleateUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<List_item>> loader, List<List_item> list_items) {
        Log.v(TAG,"pass onLoadFinished");
        // noDatatxt.setText(R.string.no_data_message);
        mAdapter.clear();
        if (list_items != null && !list_items.isEmpty()) {
            mAdapter.addAll(list_items);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<List_item>> loader) {
        mAdapter.clear();
    }
}
