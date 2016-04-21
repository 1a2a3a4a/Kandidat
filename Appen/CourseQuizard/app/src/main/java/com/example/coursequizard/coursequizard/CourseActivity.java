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
        //private String course = new String();
    public String userID ="1337";
    public LinkedList<String> courseNameList = new LinkedList<String>();
    public LinkedList<Course> courseList = new LinkedList<Course>();

    /**
     *  Finds out what activity that was the previous one and executes from that.
     * @param courseName the name of the course
     * @param universityName   the course ID
     */
    public void fromActivity(String courseName, String courseCode,String courseID,String universityName,  String universityID){
        ArrayList<String> message = new ArrayList<String>();
        message = getIntent().getExtras().getStringArrayList("prevActivity");
        Log.i("message0", message.get(0));
        Log.i("message1", message.get(1));
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

    /**
     *  To the challenge activity
     * @param opponentName the name of the opponent
     * @param courseName   the name of the course
     */
    public void toChallengeActivity(String opponentName, String courseName, String courseCode, String courseID, String universityName, String universityID) {
        Intent i = new Intent(getApplicationContext(), ChallengeActivity.class);
        ArrayList<String> send = new ArrayList<String>();
        send.add("fromCourseActivity");
        send.add(opponentName);
        send.add(courseName);
        send.add(courseCode);
        send.add(courseID);
        send.add(universityName);
        send.add(universityID);

        i.putExtra("Opponent and Course", send);
        startActivity(i);

    }

    /**
     * Start the createquestion Activity
     * @param courseName the course name
     * @param courseID   the course id
     */
    public void toCreateQuestionActivity(String courseName,String courseCode, String courseID, String universityName,String universityID){
        Intent i = new Intent(getApplicationContext(), CreateQuestionActivity.class);
        ArrayList<String> send = new ArrayList<String>();
        send.add("fromCourseActivity");
        send.add(courseName);
        send.add(courseCode);
        send.add(courseID);
        send.add(universityName);
        send.add(universityID);
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
        public void toParserHandler(String userID) {
            String data ="";
            ArrayList<String> message = new ArrayList<String>();
            message = getIntent().getExtras().getStringArrayList("prevActivity");
            if (message.get(0).equals("fromOpponentActivity")) {
                data = message.get(2);
            }
            else if (message.get(0).equals("fromCreateQuestionActivity")){
                data = message.get(1);
            }
            else if(message.get(0).equals("fromChallengeActivity")){
                data = message.get(2);
            }
           CQParser parser = new CQParser();
           courseList = parser.getGeneratedCList(data);
        }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        userID ="1337";
        // Parse the string from the php script
        toParserHandler(userID);
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
            courseNameList.add(courseList.get(j).getName()) ;
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
                fromActivity(courseChosen.getName(), String.valueOf(courseChosen.getCourse_code()),String.valueOf(courseChosen.getC_ID()),courseChosen.getUni_name(),String.valueOf(courseChosen.getUni_ID()));

            }
        });
    }
}
