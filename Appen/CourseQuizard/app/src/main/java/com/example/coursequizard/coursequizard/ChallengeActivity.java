package com.example.coursequizard.coursequizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *  This class is the Activity where the users confirm a challenge
 *  opponent: Name of the opponent, for printing purposes
 *  course: Name of the course, for printing purposes
 *  opponentID: The ID of the opponent, for database purposes
 *  courseID: The ID of the course, for database purposes
 *
 */
public class ChallengeActivity extends AppCompatActivity {
      public String opponent = "";
      public String course   = "";
      public String opponentID ="";
     public String  courseID ="";

    /**
     * This method is used for setting the public class variables, recieving them from the last activity
     * (or the BackgroundWithServer class)
     *
     */
    public  void courseAndOpponent(){
        ArrayList<String> courseAndOpponent = new ArrayList<String>();
        courseAndOpponent = getIntent().getExtras().getStringArrayList("Opponent and Course");
         opponent = courseAndOpponent.get(0);
        course = courseAndOpponent.get(1);
        opponentID = courseAndOpponent.get(2);
        courseID = courseAndOpponent.get(3);

    }

    /**
     * This method run when the playbutton is pressed
     * It will tell the server to start a game and will recieve questions to be able to play
     * @param view the playbutton
     *
     *
     *
     */
    public void toPlayGameActivity(View view){
        String type ="get questions";
        // receiving the arguments
        courseAndOpponent();
        // do the backgroundcommunication with the server
        BackgroundWithServer bgws = new BackgroundWithServer(this);
        bgws.execute(type,opponentID,courseID);
        // Start the PlayGame Activity
        Intent i = new Intent(getApplicationContext(),PlayGameActivity.class);
        startActivity(i);
    }
    /**
     * This method run when the edit Opponent Button is pressed
     * It will go back to the OpponentActivity.
     * @param view  for the edit opponent button
     */
    public void toOpponentActivity(View view){
        Intent i = new Intent(getApplicationContext(),OpponentActivity.class);
        TextView courseTextView = (TextView) findViewById(R.id.courseTextView);
        course = courseTextView.getText().toString();
        ArrayList<String> send = new ArrayList<String>();
        send.add("fromChallengeActivity");
        send.add(course);
        i.putExtra("prevActivity",send );
        startActivity(i);
    }
    /**
     * This method run when the edit Course Button is pressed
     * It will go back to the CourseActivity and passing the opponent because it will not be changed.
     * @param view  for the edit course button
     */
    public void toCourseActivity(View view){
        Intent i = new Intent(getApplicationContext(),CourseActivity.class);
        TextView opponentTextView = (TextView) findViewById(R.id.opponentTextView);
        opponent = opponentTextView.getText().toString();
        ArrayList<String> send = new ArrayList<String>();
        send.add("fromChallengeActivity");
        send.add(opponent);
        i.putExtra("prevActivity",send );
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
        // get the variables fdrom the previous Opponnent and Course activities
        courseAndOpponent();

        TextView courseTextView = (TextView) findViewById(R.id.courseTextView);
        TextView opponentTextView = (TextView) findViewById(R.id.opponentTextView);
        // Enter the course name and opponent name to the textfields to clearify for the user.
        courseTextView.setText(course);
        opponentTextView.setText(opponent);
    }
}
