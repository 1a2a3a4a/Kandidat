package com.example.coursequizard.coursequizard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coursequizard.coursequizard.R;

import java.util.ArrayList;
import java.util.LinkedList;

class MyListAdapter extends ArrayAdapter<String> {
    private int layout;
    private int buttonmode = 1;
    private LinkedList<Course> courseList;


    /**
     *  To the challenge activity
     * @param opponentName the name of the opponent
     * @param courseName   the name of the course
     */
    public void toChallengeActivity(String opponentName, String courseName, String courseCode, String courseID, String universityName, String universityID) {
        Intent i = new Intent(getContext(), ChallengeActivity.class);
        ArrayList<String> send = new ArrayList<String>();
        send.add("fromCourseActivity");
        send.add(opponentName);
        send.add(courseName);
        send.add(courseCode);
        send.add(courseID);
        send.add(universityName);
        send.add(universityID);

        i.putExtra("Opponent and Course", send);
        getContext().startActivity(i);

    }

    /**
     * Start the createquestion Activity
     * @param courseName the course name
     * @param courseID   the course id
     */
    public void toCreateQuestionActivity(String courseName,String courseCode, String courseID, String universityName,String universityID){
        Intent i = new Intent(getContext(), CreateQuestionActivity.class);
        ArrayList<String> send = new ArrayList<String>();
        send.add("fromCourseActivity");
        send.add(courseName);
        send.add(courseCode);
        send.add(courseID);
        send.add(universityName);
        send.add(universityID);
        i.putExtra("prevActivity", send);
        getContext().startActivity(i);
    }

    /**
     *  Finds out what activity that was the previous one and executes from that.
     * @param courseName the name of the course
     * @param universityName   the course ID
     */
    public void fromActivity(String courseName, String courseCode,String courseID,String universityName,  String universityID){
        ArrayList<String> message = new ArrayList<String>();
        Intent intent = ((Activity)getContext()).getIntent();
        message = intent.getExtras().getStringArrayList("prevActivity");
      //  Log.i("message0", message.get(0));
        // Log.i("message1", message.get(1));
        if (message.get(0).equals("fromChallengeActivity")){
            // To challenge activity with opponent name and course name .
            String opponentName = message.get(1);
            toChallengeActivity(opponentName, courseName, courseCode, courseID, universityName,universityID);
        }
        else if (message.get(0).equals("fromOpponentActivity")){
            String opponentName = message.get(1);
            toChallengeActivity(opponentName, courseName, courseCode, courseID, universityName,universityID);
        }

        else if (message.get(0).equals("fromCreateQuestionActivity")) {
            // If we entered a course to create a question, just pass the course name and id
            toCreateQuestionActivity(courseName,courseCode,courseID,universityName,universityID);
        }

    }

    private void swapFavorite(int position){
        Course course = courseList.get(position);
        BackgroundWithServer b = new BackgroundWithServer(getContext());
        String type = "swap favorites";
        //TODO IMPLEMENT USER
        String user_name = "Daniel";
        String c_id = "" + course.getC_ID();
        b.execute(type, c_id, user_name);
    }

    public MyListAdapter(Context context, int resource, ArrayList<String> objects, LinkedList courseList) {
        super(context, resource, objects);
        layout = resource;
        this.courseList = courseList;
    }

    private void swapButton(ImageButton button){
        //button.setImageResource(R.drawable.star_off);
        if(buttonmode == 1) {
            button.setImageResource(R.drawable.star_off);
            buttonmode = 0;
        }
        if(buttonmode == 0){
            button.setImageResource(R.drawable.star_on);
            buttonmode = 1;
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        buttonmode = 1;
        ViewHolder mainView = null;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.button = (ImageButton) convertView.findViewById(R.id.list_item_button);
            //ImageButton ib = ad.findViewById(R.id.list_item_button);
            viewHolder.text = (TextView) convertView.findViewById(R.id.list_item_text);
            convertView.setTag(viewHolder);
        }
        mainView = (ViewHolder) convertView.getTag();
        final ImageButton iB = mainView.button;
        iB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                swapFavorite(position);
                swapButton(iB);
                //iB.setImageResource(R.drawable.star_off);
                //Toast.makeText(getContext(), "Button was clicked for list item" + position, Toast.LENGTH_SHORT).show();
            }
        });
        final TextView tI = mainView.text;
        tI.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Course courseChosen = courseList.get(position);
             //   Log.i("pushed on course", courseChosen.toStringName());

                fromActivity(courseChosen.getName(), String.valueOf(courseChosen.getCourse_code()), String.valueOf(courseChosen.getC_ID()), courseChosen.getUni_name(), String.valueOf(courseChosen.getUni_ID()));
            }

        });


        mainView.text.setText(getItem(position));

        return convertView;
    }
}