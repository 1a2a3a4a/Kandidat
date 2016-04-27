package com.example.coursequizard.coursequizard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Simon on 2016-04-27.
 */
public class GameItemAdapter extends ArrayAdapter<GameItem> {
    private ArrayList<GameItem> objects;
    public GameItemAdapter(Context context, int textViewResourceId, ArrayList<GameItem> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.fragment_gameitem, null);
        }
        GameItem i = objects.get(position);
        if (i != null) {

            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.
            /*
            private int opponentScore;
            private int userScore;
            private int gameID;
            private String courseName;
            private int state;
            private String stateMessage;
             */

            TextView gameScore = (TextView) v.findViewById(R.id.scoreTextView);
            TextView courseName = (TextView) v.findViewById(R.id.courseNameTextView);
            TextView stateMessage = (TextView) v.findViewById(R.id.opponentNameTextView);

            // check to see if each individual textview is null.
            // if not, assign some text!
            if (gameScore != null){
                String opponentScore = String.valueOf(i.getOpponentScore());
                String userScore     = String.valueOf(i.getOpponentScore());
                String score      = userScore + " - " + opponentScore;
                gameScore.setText(score);
            }
            if (courseName!= null){
                courseName.setText(i.getCourseName());
            }
            if (stateMessage!= null){
                stateMessage.setText(i.getStateMessae());
            }
        }
        return v;
    }
}
