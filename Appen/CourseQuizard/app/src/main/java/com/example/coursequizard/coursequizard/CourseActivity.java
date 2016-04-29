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

    public LinkedList<Course> courseList = new LinkedList<Course>();
    private ArrayList<String> data = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        // Parse the string from the php script
        toParserHandler();

        // enable interaction with the courselistview
        ListView coursesListView = (ListView) findViewById(R.id.coursesListView);

        populateData();

        coursesListView.setAdapter(new MyListAdapter(this, R.layout.list_item, data, courseList));

        // Listener of the floating button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddCourseActivity();
            }
        });


    }

    /**
     * recieve the unparsed string and parse it to a linked list
     */
    public void toParserHandler() {
        Log.i("parserhandler", "");
        String data = "";
        ArrayList<String> message = new ArrayList<String>();

        message = getIntent().getExtras().getStringArrayList("prevActivity");
        Log.i("message 0:", message.get(0));
        if (message.get(0).equals("fromOpponentActivity")) {
            data = message.get(2);
        }
        else if (message.get(0).equals("fromCreateQuestionActivity")){
            data = message.get(1);
        }
        else if(message.get(0).equals("fromChallengeActivity")){
            data = message.get(2);
        }
        else if(message.get(0).equals("fromMyProfileActivity")){
            data = message.get(1);
        }
        CQParser parser = new CQParser();
        courseList = parser.toCList(data);
    }


    private void populateData() {
        for(int i =0; i < courseList.size(); i++) {
            data.add(courseList.get(i).toStringName());
        }
    }

    /**
     * Start the addcourse Actvitty
     */
    public void toAddCourseActivity(){
        String type = "universitylist";
        BackgroundWithServer bgws = new BackgroundWithServer(this);
        bgws.execute(type);

    }





}