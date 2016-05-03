package com.example.coursequizard.coursequizard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.ListFragment;
import android.support.v4.app.ListFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.coursequizard.coursequizard.dummy.DummyContent;
import com.example.coursequizard.coursequizard.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 * Use the {@link CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseFragment extends ListFragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private ArrayList<Course> myCourses = new ArrayList<Course>();
    private ArrayList<Course> universityCourses = new ArrayList<>();
    private ArrayList<Course> allCourses = new ArrayList<>();
    private ArrayList<ArrayList<Course>> courseLists = new ArrayList<ArrayList<Course>>();
    private int myInt;
    private CourseAdapter myAdapter;




  /*  public void fromActivityFab (FloatingActionButton fab){
        fab.setVisibility(View.INVISIBLE);
        ArrayList<String> message = new ArrayList<String>();
        message = getActivity().getIntent().getExtras().getStringArrayList("prevActivity");
        if (message.get(0).equals("fromCreateQuestionActivity")){
            fab.setVisibility(View.VISIBLE);
        }
    }
    */
    public CourseFragment() {
        // Required empty public constructor
    }
    public void toChallengeActivity(String opponentName, String courseName, String courseCode, String courseID, String universityName, String universityID) {
        Intent i = new Intent(getContext(), ChallengeActivity.class);
        ArrayList<String> send = new ArrayList<String>();
        send.add("fromCourseActivity");
        send.add(opponentName);
        send.add(courseName);
        send.add(courseCode);
        send.add(courseID);
        send.add(universityName);
        send.add(universityID);

        i.putExtra("Opponent and Course", send);
        getContext().startActivity(i);

    }
    public void clickar(View view){
        Log.i("clion","clicon");
    }
    /**
     * Start the createquestion Activity
     * @param courseName the course name
     * @param courseID   the course id
     */
    public void toCreateQuestionActivity(String courseName,String courseCode, String courseID, String universityName,String universityID){
        Intent i = new Intent(getContext(), CreateQuestionActivity.class);
        ArrayList<String> send = new ArrayList<String>();
        send.add("fromCourseActivity");
        send.add(courseName);
        send.add(courseCode);
        send.add(courseID);
        send.add(universityName);
        send.add(universityID);
        i.putExtra("prevActivity", send);
        getContext().startActivity(i);
    }

    public void fromActivity(String courseName, String courseCode,String courseID,String universityName,  String universityID){
        ArrayList<String> message = new ArrayList<String>();
        Intent intent = ((Activity)getContext()).getIntent();
        message = intent.getExtras().getStringArrayList("prevActivity");
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
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseFragment newInstance(String param1, String param2) {
        CourseFragment fragment = new CourseFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public void toParserHandler() {
        Log.i("parserhandler", "");
        String datas = "";
        ArrayList<String> message = new ArrayList<String>();

        message = getActivity().getIntent().getExtras().getStringArrayList("prevActivity");
        Log.i("message 0:", message.get(0));
        if (message.get(0).equals("fromOpponentActivity")) {
            datas = message.get(2);
        }
        else if (message.get(0).equals("fromCreateQuestionActivity")){
            datas = message.get(1);
        }
        else if(message.get(0).equals("fromChallengeActivity")){
            datas = message.get(2);
        }
        else if(message.get(0).equals("fromMyProfileActivity")){
            datas = message.get(1);
        }
        Log.i("unparsedcourselists","unp");
        Log.i("unparsedcourselists",datas);
        CQParser parser = new CQParser();
      // allCourses = parser.toCList(datas);
        courseLists = parser.toCLists(datas);
        myCourses = courseLists.get(0);
        universityCourses = courseLists.get(1);
        allCourses =courseLists.get(2);


    }
    public void toAddCourseActivity(){
        String type = "universitylist";
        BackgroundWithServer bgws = new BackgroundWithServer(getActivity());
        bgws.execute(type);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_gameitem_list, container, false);
        Bundle bundle = this.getArguments();
        myInt = bundle.getInt("courseList", 3);
        myCoursesSort();



        switch(myInt){
            case(0):
                ListView lva = (ListView) rootView.findViewById(android.R.id.list);
                Log.i("mycourses", "mycourses");
                Log.i("mycourses size", String.valueOf(myCourses.size()));
                myAdapter = new CourseAdapter(getActivity(), R.layout.list_item_course, android.R.id.list, myCourses,myCourses);
                lva.setAdapter(myAdapter);
                break;
            case(1):
                ListView lvb = (ListView) rootView.findViewById(android.R.id.list);
                Log.i("unicourses", "unicourses");
                Log.i("unicourses size", String.valueOf(universityCourses.size()));
                myAdapter = new CourseAdapter(getActivity(), R.layout.list_item_course, android.R.id.list, universityCourses,myCourses);
                lvb.setAdapter(myAdapter);
                break;
            case(2):
                ListView lvc = (ListView) rootView.findViewById(android.R.id.list);
                Log.i("allcourses", "allcourses");
                Log.i("allcourses size", String.valueOf(allCourses.size()));
                myAdapter = new CourseAdapter(getActivity(), R.layout.list_item_course, android.R.id.list, allCourses,myCourses);
                lvc.setAdapter(myAdapter);
                break;
            default:
                break;
        }
        return rootView;
    }
    public void onListItemClick(ListView l,View v,int position,long id) {
        Log.i("clickclick", "click");
        Course courseChosen = myAdapter.getItem(position);
        fromActivity(courseChosen.getName(), String.valueOf(courseChosen.getCourse_code()), String.valueOf(courseChosen.getC_ID()), courseChosen.getUni_name(), String.valueOf(courseChosen.getUni_ID()));
    }
    public void myCoursesSort(){
        toParserHandler();
    }

    }





