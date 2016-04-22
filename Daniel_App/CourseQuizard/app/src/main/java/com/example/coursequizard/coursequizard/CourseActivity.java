package com.example.coursequizard.coursequizard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class is for the CourseActivity: A scrollable list of all available courses, if the specific course does not exist then the users add one by tapping the floating button.
 */
public class CourseActivity extends AppCompatActivity {
        private String course = new String();
    public LinkedList<String> courseNameList = new LinkedList<String>();
    public LinkedList<Course> courseList = new LinkedList<Course>();

    /**
     *  Finds out what activity that was the previous one and executes from that.
     * @param course the name of the course
     * @param cid    the course ID
     */
    public void fromActivity(String course, String cid){
        ArrayList<String> message = new ArrayList<String>();
        message = getIntent().getExtras().getStringArrayList("prevActivity");
        Log.i("message0", message.get(0));
        Log.i("message1", message.get(1));
        if (message.get(0).equals("fromChallengeActivity")){
            // To challenge activity with opponent name and course name .
            toChallengeActivity(message.get(1), course);
        }
        else if (message.get(0).equals("fromOpponentActivity")){
            // I do not know why
            toChallengeActivity(message.get(2), course);
        }

        else if (message.get(0).equals("fromCreateQuestionActivity")) {
            // If we entered a course to create a question, just pass the course name and id
            toCreateQuestionActivity(course,cid);
        }

    }

    /**
     *  To the challenge activity
     * @param opponent the name of the opponent
     * @param course    the name of the course
     */
    public void toChallengeActivity(String opponent, String course) {
        Intent i = new Intent(getApplicationContext(), ChallengeActivity.class);
        ArrayList<String> send = new ArrayList<String>();
        send.add(opponent);
        send.add(course);
        i.putExtra("Opponent and Course", send);
        startActivity(i);

    }

    /**
     * Start the createquestion Activity
     * @param course the course name
     * @param cid    the course id
     */
    public void toCreateQuestionActivity(String course, String cid){
        Intent i = new Intent(getApplicationContext(), CreateQuestionActivity.class);
        ArrayList<String> send = new ArrayList<String>();
        send.add("fromCourseActivity");
        send.add(course);
        send.add(cid);
        i.putExtra("prevActivity", send);
        startActivity(i);
    }

    /**
     * Start the addcourse Actvitty
     */
    public void toAddCourseActivity(){
        Intent i = new Intent(getApplicationContext(), AddCourseActivity.class);
        ArrayList<String> send = new ArrayList<String>();
        send.add("fromCourseActivity");
        i.putExtra("prevActivity", send);
        startActivity(i);
    }

    /**
     * recieve the unparsed string and parse it to a linked list
     * @param userID the userID
     */
        public void toBackgroundWithServer(String userID) {
            ArrayList<String> message = new ArrayList<String>();
            message = getIntent().getExtras().getStringArrayList("prevActivity");
            String data = message.get(1);

           CQParser parser = new CQParser();
            Log.i("data0",message.get(0));
                    Log.i("data1",message.get(1));
           courseList = parser.toClist(data);
        }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        String userID ="1337";
        // Parse the string from the php script
        toBackgroundWithServer(userID);
        // enable interaction with the courselistview
        ListView coursesListView = (ListView) findViewById(R.id.coursesListView);
        //ArrayList<String> Courses = new ArrayList<String>();
        /*courseNameList.add("Algebra");
        courseNameList.add("Databas");
        courseNameList.add("Ioopm");
        courseNameList.add("A");
        courseNameList.add("B");
        courseNameList.add("C");
        courseNameList.add("D");
        courseNameList.add("E");
        courseNameList.add("F");
        courseNameList.add("G");
        courseNameList.add("H");
        courseNameList.add("I");
        courseNameList.add("J");
        */
        // add all courses to the courselistview
        for ( int j =0; j< courseList.size() ;j++){
            courseNameList.add(courseList.get(j).getname()) ;
        }
        // Listener of the floating button
       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddCourseActivity();
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,courseNameList);
        coursesListView.setAdapter(arrayAdapter);
        // crate a listnener for the courselistview, so ww know what course that was chosen.
        coursesListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Course courseChosen = courseList.get(position);
                     // get the name anf the id of the pressed course
                fromActivity(courseChosen.getname(), String.valueOf(courseChosen.getC_ID()));
            }
        });
    }
}
