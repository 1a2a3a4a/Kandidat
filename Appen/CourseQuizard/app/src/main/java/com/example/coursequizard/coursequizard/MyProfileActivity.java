package com.example.coursequizard.coursequizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

public class MyProfileActivity extends AppCompatActivity {
    LinkedList<String> universityNameList = new LinkedList<String>();
    LinkedList<University> universityLinkedList = new LinkedList<University>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        fromActivity();

    }
    public void editMyUniversity(View view){
        Spinner spinner = (Spinner)findViewById(R.id.universitySpinnerProfile);
        SaveSharedData.setMyUniversityName(MyProfileActivity.this,spinner.getSelectedItem().toString());
      //  Log.i("id","id");
      //  Log.i("id",String.valueOf(universityLinkedList.get(spinner.getSelectedItemPosition()).getU_ID()));
        SaveSharedData.setMyUniversityID(MyProfileActivity.this,String.valueOf(universityLinkedList.get(spinner.getSelectedItemPosition()).getU_ID()));
       // Log.i("saveds", SaveSharedData.getMyUniversityID(MyProfileActivity.this));
        Toast.makeText(MyProfileActivity.this, "Saved: " + SaveSharedData.getMyUniversityName(MyProfileActivity.this), Toast.LENGTH_SHORT).show();
    }
    public void toAllCoursesActivity(View view){
        BackgroundWithServer bgws = new BackgroundWithServer(this);
        String type = "get all courses";
        //TODO IMPLEMENT USER
        //String username = "Daniel";
        bgws.execute(type);
    }
    public void setCurrentSelect(int index){
        Spinner universitySpinner = (Spinner)findViewById(R.id.universitySpinnerProfile);
        universitySpinner.setSelection(index);
    }
    public void createSpinner(){
        String uniID = SaveSharedData.getMyUniversityID(MyProfileActivity.this);
        int index =0;
        for ( int j =0; j< universityLinkedList.size() ;j++){
            universityNameList.add(universityLinkedList.get(j).getName()) ;
            if (uniID.equals(String.valueOf(universityLinkedList.get(j).getU_ID()))){
                index =j;
            }
        }
        Spinner universitySpinner = (Spinner)findViewById(R.id.universitySpinnerProfile);
        ArrayAdapter<String> universityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, universityNameList);
        universitySpinner.setAdapter(universityAdapter);
        setCurrentSelect(index);
    }
    public void fromActivity(){
        ArrayList<String> message = new ArrayList<String>();
        message =  getIntent().getExtras().getStringArrayList("prevActivity");
        if(message.get(0).equals("fromMainActivity")){
            CQParser parser = new CQParser();
            universityLinkedList= parser.toUList(message.get(1));
             createSpinner();
            message.get(1);
        }
    }

    public void myFriends(View view){
        BackgroundWithServer bgws = new BackgroundWithServer(MyProfileActivity. this);
        bgws.execute("friendlist" );
        Intent i = new Intent(MyProfileActivity.this, FriendsActivity. class);
        startActivity(i);
    }
    public void addFriend(View view){
        Intent i = new Intent(MyProfileActivity.this, AddFriendActivity.class);
        startActivity(i);
    }
    public void toPendingActivity(View view){
        BackgroundWithServer bgws = new BackgroundWithServer(MyProfileActivity.this);
        bgws.execute("pending");
    }
    public void toFriendsActivity(View view){
        BackgroundWithServer bgws = new BackgroundWithServer(MyProfileActivity.this);
        bgws.execute("getrelationlists");
    }
}