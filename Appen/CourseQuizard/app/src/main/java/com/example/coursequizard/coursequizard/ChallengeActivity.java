package com.example.coursequizard.coursequizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ChallengeActivity extends AppCompatActivity {

    public  String[] courseAndOpponent(){
        String [] courseAndOpponent = new String[2];
        courseAndOpponent = getIntent().getExtras().getStringArray("Opponent and Course");
        return courseAndOpponent;
    }
    public void toPlayGameActivity(View view){
        Intent i = new Intent(getApplicationContext(),PlayGameActivity.class);
        startActivity(i);
    }
    public void toOpponentActivity(View view){
        Intent i = new Intent(getApplicationContext(),OpponentActivity.class);
        TextView courseTextView = (TextView) findViewById(R.id.courseTextView);
        String[] send = new String[] {"fromChallengeActivity",courseTextView.getText().toString()};
        i.putExtra("prevActivity",send );
        startActivity(i);
    }
    public void toCourseActivity(View view){
        Intent i = new Intent(getApplicationContext(),CourseActivity.class);
        TextView opponentTextView = (TextView) findViewById(R.id.opponentTextView);
        String send = opponentTextView.getText().toString();
        i.putExtra("prevActivity",send );
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
        String [] courseAndOpponent = new String [2];
        courseAndOpponent = courseAndOpponent();
        String course = courseAndOpponent[1];
        String opponent = courseAndOpponent[0];
        TextView courseTextView = (TextView) findViewById(R.id.courseTextView);
        TextView opponentTextView = (TextView) findViewById(R.id.opponentTextView);
        courseTextView.setText(course);
        opponentTextView.setText(opponent);
    }
}
