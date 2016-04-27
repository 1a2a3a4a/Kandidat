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

/**
 * The user can choose who to challenge or playing sinlge player
 * myFriends The friends of the user
 * Opponent: The name of the opponent
 */
public class OpponentActivity extends AppCompatActivity {

    public ArrayList<String> myFriends = new ArrayList<String>();
    private String opponent = new String();

    /**
     * if user chooses random opponent
     * @param view
     */
    public void randomOpponentButtonClicked(View view){
                String random  = "Random Opponent";
        toNextActivity( random);

    }
    /**
     * if user chooses to play alone
     * @param view
     */
    public void singlePlayerButtonClicked(View view){
        String sp = "singleplayer";
        toNextActivity(sp);

    }

    /**
     * go to the next activity after oipponent is chosen
     * @param opponentChosen the opponent chosen
     */
    public void toNextActivity(String opponentChosen) {
        ArrayList<String> message = new ArrayList<String>();
        message =  getIntent().getExtras().getStringArrayList("prevActivity");

        if (message.get(0).equals("fromMainActivity")){
            String type ="my courses, from opponent";
            //to courseactivity
            BackgroundWithServer bgws = new BackgroundWithServer(this);
            bgws.execute(type,opponentChosen);
        }
        if (message.get(0).equals("fromChallengeActivity")) {
            Intent i = new Intent(getApplicationContext(), ChallengeActivity.class);

            message.set(0,opponentChosen);
            i.putExtra("Opponent and Course", message);
            startActivity(i);
        }
    }

    /**
     * the beginning of developing search functionality
     * @param menu
     * @return
     */
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
        myFriends = SaveSharedData.getFriendList(OpponentActivity.this);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myFriends);
        friendsListView.setAdapter(arrayAdapter);
        // a listener for the friendlist
        friendsListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view, int position,long id){
                String opponentFriend = myFriends.get(position);
                toNextActivity(opponentFriend);
            }
        });







    }
}
