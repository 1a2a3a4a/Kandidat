package com.example.coursequizard.coursequizard;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by daniel.hellgren on 2016-04-21.
 */
public class MyCoursesActivity extends AppCompatActivity {

    private ArrayList<String> data = new ArrayList<String>();
    private LinkedList<Course> myCourses = new LinkedList<Course>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        this.fetchCourses();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses);
        ListView lv = (ListView) findViewById(R.id.listview);

        generateListContent();
        lv.setAdapter(new MyListAdapter(this, R.layout.list_item, data, myCourses));
    }

/*
    private void swapFavorite(int position){
        Course course = myCourses.get(position);
        BackgroundWithServer b = new BackgroundWithServer(this);
        String type = "swap favorites";
        //TODO IMPLEMENT USER
        String user_name = "Daniel";
        String c_id = "" + course.getC_ID();
        b.execute(type, c_id, user_name);
    }
*/
    private void fetchCourses() {
        ArrayList<String> message = new ArrayList<String>();
        message = getIntent().getExtras().getStringArrayList("prevActivity");
        String data = message.get(1);

        Log.i("data0", message.get(0));
        Log.i("data1",message.get(1));


        CQParser parser = new CQParser();

        myCourses = parser.toCList(data);
    }

    private void generateListContent(){
        for(int i =0; i < myCourses.size(); i++){
            data.add(myCourses.get(i).toStringName());
        }
    }

}