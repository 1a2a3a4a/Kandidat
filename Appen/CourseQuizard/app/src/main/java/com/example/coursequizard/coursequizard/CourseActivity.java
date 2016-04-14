package com.example.coursequizard.coursequizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {
        private String course = new String();
    public ArrayList<String> courseList = new ArrayList<String>();
    public String getCourse(){
        return course;
    }
    private boolean canSkip(){
        return false;
    }

    public void toChallengeActivity(String course) {
        String opponent = getIntent().getExtras().getString("prevActivity");
        Intent i = new Intent(getApplicationContext(), ChallengeActivity.class);
        String[] send = new String[]{opponent, course};

        i.putExtra("Opponent and Course", send);
        startActivity(i);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (canSkip())
        {
            Intent intent = new Intent(this, ChallengeActivity.class);
            this.startActivity (intent);
            this.finishActivity (0);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        ListView coursesListView = (ListView) findViewById(R.id.coursesListView);
        //ArrayList<String> Courses = new ArrayList<String>();
        courseList.add("Algebra");
        courseList.add("Databas");
        courseList.add("Ioopm");
        courseList.add("A");
        courseList.add("B");
        courseList.add("C");
        courseList.add("D");
        courseList.add("E");
        courseList.add("F");
        courseList.add("G");
        courseList.add("H");
        courseList.add("I");
        courseList.add("J");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,courseList);
        coursesListView.setAdapter(arrayAdapter);
        coursesListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String opponentFriend = courseList.get(position);
                toChallengeActivity(opponentFriend);
            }
        });
    }
}
