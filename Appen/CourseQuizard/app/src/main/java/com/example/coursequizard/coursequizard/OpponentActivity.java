package com.example.coursequizard.coursequizard;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class OpponentActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

       SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.searcher).getActionView();


        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opponent);
        ListView friendsListView = (ListView) findViewById(R.id.friendsListView);
        ArrayList<String> myFriends = new ArrayList<String>();
        myFriends.add("Daniel");
        myFriends.add("Simon");
        myFriends.add("Tony");
        myFriends.add("A");
        myFriends.add("B");
        myFriends.add("C");
        myFriends.add("D");
        myFriends.add("E");
        myFriends.add("F");
        myFriends.add("G");
        myFriends.add("H");
        myFriends.add("I");
        myFriends.add("J");
        myFriends.add("K");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myFriends);
        friendsListView.setAdapter(arrayAdapter);







    }
}
