package com.example.coursequizard.coursequizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
      public String opponentName   = "";
      public String courseName     = "";
      public String courseCode     = "";
      public String courseID       = "";
      public String universityName = "";
      public String universityID   = "";

    /**
     * This method is used for setting the public class variables, recieving them from the last activity
     * (or the BackgroundWithServer class)
     *
     */
    public  void courseAndOpponent(){
        ArrayList<String> courseAndOpponent = new ArrayList<String>();
        courseAndOpponent = getIntent().getExtras().getStringArrayList("Opponent and Course");
        if (courseAndOpponent.get(0).equals("fromCourseActivity")) {
            opponentName = courseAndOpponent.get(1);
            courseName = courseAndOpponent.get(2);
            courseCode = courseAndOpponent.get(3);
            courseID = courseAndOpponent.get(4);
            universityName = courseAndOpponent.get(5);
            universityID = courseAndOpponent.get(6);
        }
        else{
            opponentName = courseAndOpponent.get(0);
            courseName = courseAndOpponent.get(1);
            courseCode = courseAndOpponent.get(2);
            courseID = courseAndOpponent.get(3);
            universityName = courseAndOpponent.get(4);
            universityID = courseAndOpponent.get(5);
        }

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
        String type ="friendPlayerMode";
       // Log.i("type",type);
        // receiving the arguments
        courseAndOpponent();
        // do the backgroundcommunication with the server
        if(opponentName.toLowerCase().equals("random opponent")){
            type ="randomPlayerMode";
            String numberOfQuestions ="5";
            BackgroundWithServer bgws = new BackgroundWithServer(this);
            bgws.execute(type,courseID,numberOfQuestions);

        }
        else if(opponentName.toLowerCase().equals("singleplayer")){
          //  Log.i("Single","Sinlgeplayer");
            type ="singlePlayerMode";
            BackgroundWithServer bgws = new BackgroundWithServer(this);
            String numberOfQuestions ="5";
            bgws.execute(type,courseID,numberOfQuestions);
        }
        else {
           // Log.i("elsemode",type);
            type ="friendPlayerMode";
            BackgroundWithServer bgws = new BackgroundWithServer(this);
            bgws.execute(type, opponentName, courseID);
        }
        // Start the PlayGame Activity
    }
    /**
     * This method run when the edit Opponent Button is pressed
     * It will go back to the OpponentActivity.
     * @param view  for the edit opponent button
     */
    public void toOpponentActivity(View view){
        Intent i = new Intent(getApplicationContext(),OpponentActivity.class);
        //TextView courseTextView = (TextView) findViewById(R.id.courseTextView);
        //courseName = courseTextView.getText().toString();
        ArrayList<String> send = new ArrayList<String>();
        send.add("fromChallengeActivity");
        send.add(courseName);
        send.add(courseCode);
        send.add(courseID);
        send.add(universityName);
        send.add(universityID);
        i.putExtra("prevActivity",send );
        startActivity(i);
    }
    /**
     * This method run when the edit Course Button is pressed
     * It will go back to the CourseActivity and passing the opponent because it will not be changed.
     * @param view  for the edit course button
     */
    public void toCourseActivity(View view){
        String type = "my courses, from challenge";
        BackgroundWithServer bgws = new BackgroundWithServer(this);
        bgws.execute(type,opponentName);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
        // get the variables fdrom the previous Opponnent and Course activities
        courseAndOpponent();

        TextView courseTextView = (TextView) findViewById(R.id.courseNameTextView);
        TextView opponentTextView = (TextView) findViewById(R.id.opponentNameTextView);
        // Enter the course name and opponent name to the textfields to clearify for the user.
        courseTextView.setText(courseName);
        opponentTextView.setText(opponentName);
    }
}
