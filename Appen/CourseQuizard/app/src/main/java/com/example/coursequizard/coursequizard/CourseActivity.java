package com.example.coursequizard.coursequizard;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CourseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Course> myCourses;
    private ArrayList<Course> uniCourses;
    private ArrayList<Course> allCourses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddCourseActivity();
            }
        });
        fromActivityFab(fab);
    }
    private void fromActivityFab (FloatingActionButton fab){
        fab.setVisibility(View.INVISIBLE);
        ArrayList<String> message = new ArrayList<String>();
        message = getIntent().getExtras().getStringArrayList("prevActivity");
        if (message.get(0).equals("fromCreateQuestionActivity")){
            fab.setVisibility(View.VISIBLE);
        }
    }
    public void toAddCourseActivity(){
        String type = "universitylist";
        BackgroundWithServer bgws = new BackgroundWithServer(this);
        bgws.execute(type);

    }


    private void setupViewPager(ViewPager viewPager) {
        CourseFragmentAdapter adapter = new CourseFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new CourseFragment(), "my courses", myCourses);
        adapter.addFragment(new CourseFragment(), " uni courses", uniCourses);
        adapter.addFragment(new CourseFragment(), "all", allCourses);
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return true;
    }
}