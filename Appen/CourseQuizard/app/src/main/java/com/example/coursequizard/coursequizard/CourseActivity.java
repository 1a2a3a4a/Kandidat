package com.example.coursequizard.coursequizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    public void fromActivity(String course){
        String[] message = new String[2];
        message = getIntent().getExtras().getStringArray("prevActivity");
        Log.i("message0", message[0]);
        Log.i("message1", message[1]);
        if (message[0].equals("fromChallengeActivity")){
            toChallengeActivity(message[1], course);
        }
        else if (message[0].equals("fromOpponentActivity")){
            toChallengeActivity(message[1], course);
        }

        else if (message[0].equals("fromCreateQuestionActivity")) {
            toCreateQuestionActivity(course);
        }

    }

    public void toChallengeActivity(String opponent, String course) {
        Intent i = new Intent(getApplicationContext(), ChallengeActivity.class);
        String[] send = new String[]{opponent, course};

        i.putExtra("Opponent and Course", send);
        startActivity(i);

    }
    public void toCreateQuestionActivity(String course){
        Intent i = new Intent(getApplicationContext(), CreateQuestionActivity.class);
        String[] send = new String[]{"fromCourseActivity", course};
        i.putExtra("prevActivity", send);
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
                fromActivity(opponentFriend);
            }
        });
    }
}
