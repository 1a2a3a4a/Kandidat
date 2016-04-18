package com.example.coursequizard.coursequizard;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class OpponentActivity extends AppCompatActivity {

    public ArrayList<String> myFriends = new ArrayList<String>();
    private String opponent = new String();



    public String getOpponent(){
        return opponent;
    }
    public void randomOpponentButtonClicked(View view){
                String random  = "Random Opponent";
        toNextActivity( random);

    }
    public void singlePlayerButtonClicked(View view){
        String sp = "Single Player";
        toNextActivity(sp);

    }
    public void toNextActivity(String opponentChosen) {
        String [] message = new String[2];
        message =  getIntent().getExtras().getStringArray("prevActivity");
        if (message[0].equals("fromMainActivity")){
            Intent i = new Intent(getApplicationContext(), CourseActivity.class);
            message [0]= "fromOpponentActivity";
            message [1] = opponentChosen;
            i.putExtra("prevActivity", message);
            startActivity(i);
        }
        if (message[0].equals("fromChallengeActivity")) {
            Intent i = new Intent(getApplicationContext(), ChallengeActivity.class);
            message[0]= opponentChosen;
            i.putExtra("Opponent and Course", message);
            startActivity(i);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

       SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.searchFriend).getActionView();


        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opponent);
        ListView friendsListView = (ListView) findViewById(R.id.friendsListView);
        //ArrayList<String> myFriends = new ArrayList<String>();
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
        friendsListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view, int position,long id){
                String opponentFriend = myFriends.get(position);
                toNextActivity(opponentFriend);
            }
        });







    }
}
