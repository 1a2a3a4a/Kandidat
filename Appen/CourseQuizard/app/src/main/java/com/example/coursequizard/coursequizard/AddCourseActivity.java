package com.example.coursequizard.coursequizard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class AddCourseActivity extends AppCompatActivity {

    public void fromActivity(){
            String [] message = new String[2];
            message = getIntent().getExtras().getStringArray("prevActivity");
            if (message[0].equals("fromCourseActivity")){
               addCourseView();
            }

        }
    public void addCourseView(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
    }
}
