package com.lesson_three.eiko.book_listing;

import android.support.v4.app.LoaderManager;
import android.content.*;
import android.support.v4.content.Loader;
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

    private String TAG = "main_activity";
    private static String URL_BASE =
            "https://www.googleapis.com/books/v1/volumes?q=";
    private List_Adapter mAdapter;
    private EditText edittxtSearch;
    private TextView noDatatxt;
    private String search;
    private View loadingCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);

        noDatatxt = (TextView) findViewById(R.id.no_conecction_message);
        noDatatxt.setText("type and click search");

        mAdapter = new List_Adapter(this, new ArrayList<List_item>());
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(mAdapter);
        listview.setEmptyView(noDatatxt);

        final LoaderManager loadermanager = getSupportLoaderManager();
       // loadermanager.initLoader(0, null, MainActivity.this);


        loadingCircle = findViewById(R.id.loading_indicator);
        loadingCircle.setVisibility(View.GONE);

        Button buttonSearch = (Button) findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittxtSearch = (EditText) findViewById(R.id.edittxtt_search);
                search = edittxtSearch.getText().toString();
                mAdapter.clear();
                ConnectivityManager connectManager = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                 NetworkInfo networkInfo = connectManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    loadingCircle.setVisibility(View.GONE);
                    if (search.length() > 0) {
                        search = search.replace(" ", "+");
                        loadingCircle.setVisibility(View.VISIBLE);
                      loadermanager.initLoader(0, null, MainActivity.this);

                    }else {
                        noDatatxt.setText("type something");
                    }
                }else {
                    noDatatxt.setText("No Data");
                }
                loadermanager.restartLoader(0, null, MainActivity.this);
            }
        });
        //loadermanager.initLoader(0, null, MainActivity.this);
    }

    @Override
    public Loader<List<List_item>> onCreateLoader(int i, Bundle bundle) {
        Log.v(TAG, "URL_BASE: " + URL_BASE);
        Log.v(TAG, "search: " + search);
        return new Booklist_Loader(this, URL_BASE + search);
    }

    @Override
    public void onLoadFinished(Loader<List<List_item>> loader,
                               List<List_item> list_items) {
        Log.v(TAG, "pass onLoadFinished");
        mAdapter.clear();

        if (list_items != null && !list_items.isEmpty()) {
            loadingCircle.setVisibility(View.GONE);
            mAdapter.addAll(list_items);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<List_item>> loader) {
        mAdapter.clear();
    }
}
