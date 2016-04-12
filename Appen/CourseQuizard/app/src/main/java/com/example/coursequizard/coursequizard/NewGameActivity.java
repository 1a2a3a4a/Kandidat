package com.example.coursequizard.coursequizard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class NewGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        Spinner oponnentSpinner = (Spinner)findViewById(R.id.oponnentSpinner);
        String[] oponnents = new String[]{"1 player", "Friend challenge", "Random oponnent"};
        ArrayAdapter<String> oponnentAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, oponnents);
        oponnentSpinner.setAdapter(oponnentAdapter);
        Spinner courseSpinner = (Spinner)findViewById(R.id.courseSpinner);
        //String [] userCourses = getUserCourses;
        //String[] allCourses = getAllCourses;
        String[] courses = new String[]{"Algebra1", "Algebra2", "Sigsys"};
        ArrayAdapter<String> courseAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, courses);
        courseSpinner.setAdapter(courseAdapter);
    }
}
