package com.example.coursequizard.coursequizard;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.LinkedList;

/**
This is the Add course Activity, here users can add non existing courses.


 */
public class AddCourseActivity extends AppCompatActivity {
    public LinkedList<University> universityLinkedList = new LinkedList<University>();
    public LinkedList<String> universityNameList = new LinkedList<String>();
    /**
     * This method is used to figure out what the previous Activity(page) was.
     * The first string has a tag that says from where it came from.
     * The other strings can contain arguments that can be useful for this activity.
     *
     *
     */

    public void fromActivity(){
            ArrayList<String>  message = new ArrayList<String>();
            message = getIntent().getExtras().getStringArrayList("prevActivity");
            if (message.get(0).equals("fromCourseActivity")){
               addCourseView();
                CQParser parser = new CQParser();
                universityLinkedList= parser.toUList(message.get(1));
            }

        }
    public void addCourseView(){

    }
    /**
     * This method is used  to read the textfields from the view what the users have entered in.
     *
     * @Param view from the GUI
     *
     */

    public void addCourseMethod(View view){
        // Standard way to eble to  work with the graphical objects.
        String uniID = "1";
        String uniName ="Uppsala";
        EditText newCourseNameEditText = (EditText) findViewById(R.id.newCourseNameEditText);
        EditText newCourseCodeEditText = (EditText) findViewById(R.id.newCourseCodeEditText);
        String courseName = newCourseNameEditText.getText().toString();
        String courseCode = newCourseCodeEditText.getText().toString();
        Spinner spinner = (Spinner)findViewById(R.id.universitySpinner);
        uniName = spinner.getSelectedItem().toString();
        uniID  =  String.valueOf(universityLinkedList.get(spinner.getSelectedItemPosition()).getU_ID());

        toBackGroundWithServer(courseName,courseCode, uniName, uniID);
    }

    /**
     * This method call a class that works in the background with the mysql server by sending arguments to a specific url and read the data from the database server
     *
     * @param courseName the course name
     * @param courseCode the course code
     *
     */
    public void toBackGroundWithServer(String courseName,String courseCode,String universityName, String universityID) {
        String type = "added course";
        BackgroundWithServer bgws = new BackgroundWithServer(this);

        bgws.execute(type,courseName,courseCode,universityName,universityID);
    }
    public void setCurrentSelect(int index){
        Spinner universitySpinner = (Spinner)findViewById(R.id.universitySpinnerProfile);
        universitySpinner.setSelection(index);
    }
    /**
     * This method is similar to a main method. The first method that runs for the Activity
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        fromActivity();
        String uniID = SaveSharedData.getMyUniversityID(AddCourseActivity.this);
        int index =0;
        for ( int j =0; j< universityLinkedList.size() ;j++){
            universityNameList.add(universityLinkedList.get(j).getName());
            if (uniID.equals(String.valueOf(universityLinkedList.get(j).getU_ID()))){
                index =j;
            }
        }
        // Getting the spinner
        Spinner universitySpinner = (Spinner)findViewById(R.id.universitySpinner);
        //String[] universities = new String[]{"Lund", "Stockholm", "Uppsala"};
        // Inserting the List to the spinner
        ArrayAdapter<String> universityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, universityNameList);
        universitySpinner.setAdapter(universityAdapter);
        setCurrentSelect(index);

    }
}
