package com.example.coursequizard.coursequizard;

import android.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;

public class MyGamesActivity extends FragmentActivity  {
  // public  LinkedList<Game> myGamesList = new LinkedList<Game>;
    public LinkedList<GameItem> gameList = new LinkedList<GameItem>();

    public void fromActivity(){
        ArrayList<String> message = new ArrayList<String>();
        message = getIntent().getExtras().getStringArrayList("prevActivity");
        if (message.get(0).equals("fromMainActivity")){
            String unparsedMyGames = message.get(1);
            CQParser parser = new CQParser();
          // myGamesList= parser.toGList(unparsedMyGames);
        }
    }
    public void openGameItem(GameItem chosen){

    }
    public void clickMyTurn(View view){
        GameItemFragment gamesMyTurn =new GameItemFragment();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView gamesListView = (ListView) findViewById(R.id.list);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_games);
        fromActivity();
        //ArrayList<GameItem> gameList = new ArrayList<GameItem>();
        ArrayAdapter<GameItem> arrayAdapter = new ArrayAdapter<GameItem>(this,android.R.layout.simple_list_item_1,gameList);
        gamesListView.setAdapter(arrayAdapter);
        // crate a listnener for the courselistview, so ww know what course that was chosen.
        gamesListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                GameItem gameChosn = gameList.get(position);
                // get the name anf the id of the pressed course
               // openGameItem(gameChosen);

            }
        });

    }
}
