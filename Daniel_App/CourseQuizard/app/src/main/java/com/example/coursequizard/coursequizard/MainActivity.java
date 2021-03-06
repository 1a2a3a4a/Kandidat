package com.example.coursequizard.coursequizard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

/**
 * The main menu with a navigation drawer (press top left or swipe)
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public String Login;

    ////////////////////////////////
    // Daniels Kod

    //Slut på daniels kod
    //////////////////////////////////

    /**
     * To the opponent activity
     * @param view main view
     */
    public void cqToOpponentActivity(View view){
        Intent i = new Intent(getApplicationContext(),OpponentActivity.class);
        ArrayList<String> send = new ArrayList<String>();
        send.add("fromMainActivity");
        i.putExtra("prevActivity",send );
        startActivity(i);
    }

    /**
     * To uploadactivity where users can upload text to be autogenerated
     * @param view
     */
    public void toUploadTextActivity(View view){
        Intent i = new Intent(getApplicationContext(),UploadTextActivity.class);
        ArrayList<String> send = new ArrayList<String>();
        send.add("fromMainActivity");
        i.putExtra("prevActivity",send );
        startActivity(i);

    }

    /**
     * to the create question activity
     * @param view main view
     */

    public void toCreateQuestionActivity(View view){
        SaveSharedData.clearGenQuestions(MainActivity.this);
        SaveSharedData.clearCurrentQuestion(MainActivity.this);
        Intent i = new Intent(getApplicationContext(),CreateQuestionActivity.class);
        ArrayList<String> send = new ArrayList<String>();
        send.add("fromMainActivity");
        i.putExtra("prevActivity",send );
        startActivity(i);

    }
    public void toMyGamesActivity(View view){
        String type = "mygames";
        BackgroundWithServer bgws = new BackgroundWithServer(this);
        bgws.execute(type);
    }
    public void toLoginActivity(){
        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
        ArrayList<String> send = new ArrayList<String>();
        send.add("fromMainActivity");
        i.putExtra("prevActivity",send );
        startActivity(i);

    }
    public void fromActivity() {
        try {
            ArrayList<String> message = new ArrayList<String>();
            message = getIntent().getExtras().getStringArrayList("prevActivity");
            if (message.get(0).equals("fromLoginActivity")) {
                Login = message.get(1);
            }
        }
        catch(Exception e){

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(SaveSharedData.getUserName(MainActivity.this).length() == 0)
        {
            toLoginActivity();
        }
        else {

            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
            //navigationdrawer
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            BackgroundWithServer bgws = new BackgroundWithServer(this);
            bgws.execute("friendlist");
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_play) {
            // Handle the camera action
        } else if (id == R.id.nav_profile) {
            Intent i = new Intent(getApplicationContext(),MyProfileActivity.class);
            ArrayList<String> send = new ArrayList<String>();
            send.add("fromMainActivity");
            i.putExtra("prevActivity",send );
            startActivity(i);

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_create) {
            Intent i = new Intent(getApplicationContext(),CreateQuestionActivity.class);
            ArrayList<String> send = new ArrayList<String>();
            send.add("fromMainActivity");
            i.putExtra("prevActivity",send );
            startActivity(i);


        } else if (id == R.id.nav_share) {

        }
        else if (id == R.id.nav_signOut) {
                SaveSharedData.setUserName(MainActivity.this,"");
                SaveSharedData.setUserName(MainActivity.this,"");
            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
            ArrayList<String> send = new ArrayList<String>();
            send.add("fromMainActivity");
            i.putExtra("resetActivity",send );
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
