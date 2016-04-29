package com.example.coursequizard.coursequizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MyProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

    }
    public void toAllCoursesActivity(View view){
        BackgroundWithServer bgws = new BackgroundWithServer(this);
        String type = "get all courses";
        //TODO IMPLEMENT USER
        //String username = "Daniel";
        bgws.execute(type);
    }

    public void toMyCoursesActivity(View view){
        //Intent i = new Intent(getApplicationContext(), MyCoursesActivity.class);
        // ArrayList<String> send = new ArrayList<String>();
        //send.add(opponent);
        //send.add(course);
        //i.putExtra("Opponent and Course", send);
        //startActivity(i);
        BackgroundWithServer bgws = new BackgroundWithServer(this);
        String type = "get my courses";
        //TODO IMPLEMENT USER
        String username = SaveSharedData.getUserName(MyProfileActivity.this);
        bgws.execute(type, username);
    }
    public void myFriends(View view){
        BackgroundWithServer bgws = new BackgroundWithServer(MyProfileActivity. this);
        bgws.execute("friendlist" );
        Intent i = new Intent(MyProfileActivity.this, FriendsActivity. class);
        startActivity(i);
    }
    public void addFriend(View view){
        Intent i = new Intent(MyProfileActivity.this, FriendRequestActivity.class);
        startActivity(i);
    }
}