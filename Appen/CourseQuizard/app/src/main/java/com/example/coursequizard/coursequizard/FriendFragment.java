package com.example.coursequizard.coursequizard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FriendFragment extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private ArrayList<String> myFriends = new ArrayList<String>();
    private ArrayList<String> myFriendRequests = new ArrayList<String>();
    private ArrayList<String> myFriendPending = new ArrayList<String>();
    private ArrayList<ArrayList<String>> friendLists = new ArrayList<ArrayList<String>>();
    private int myInt;
    private FriendAdapter friendAdapter;




    /*  public void fromActivityFab (FloatingActionButton fab){
          fab.setVisibility(View.INVISIBLE);
          ArrayList<String> message = new ArrayList<String>();
          message = getActivity().getIntent().getExtras().getStringArrayList("prevActivity");
          if (message.get(0).equals("fromCreateQuestionActivity")){
              fab.setVisibility(View.VISIBLE);
          }
      }
      */
    public FriendFragment() {
        // Required empty public constructor
    }
    public void clickar(View view){
        Log.i("clion","clicon");
    }

    public void fromActivity() {
        ArrayList<String> message = new ArrayList<String>();
        Intent intent = ((Activity) getContext()).getIntent();
        message = intent.getExtras().getStringArrayList("prevActivity");
        Log.i("message0", message.get(0));
        Log.i("message1", message.get(1));
        if (message.get(0).equals("fromMainActivity")) {
            // To challenge activity with opponent name and course name .
            String friendListString = message.get(1);

        }
    }


    // TODO: Rename and change types and number of parameters
    public static FriendFragment newInstance(String param1, String param2) {
        FriendFragment fragment = new FriendFragment();
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
        if (message.get(0).equals("fromMainActivity")) {
            datas = message.get(1);
        }
        else if (message.get(0).equals("fromMyProfileActivity")) {
            datas = message.get(1);
        }
        Log.i("unparsedfriendlists","unp");
        Log.i("unparsedfriendlists",datas);
        CQParser parser = new CQParser();
        // allCourses = parser.toCList(datas);
        friendLists = parser.toFriendLists(datas);
        myFriends   = friendLists.get(0);
        myFriendRequests   = friendLists.get(1);
        myFriendPending   = friendLists.get(2);



    }
    public void toAddFriendActivity(){
        ArrayList<String> message = new ArrayList<String>();
        Intent i = new Intent(getActivity(), AddFriendActivity.class);
        message.add("FromFriendsActivity");
        i.putExtra("prevActivity", message);
        startActivity(i);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_gameitem_list, container, false);
        Bundle bundle = this.getArguments();
        myInt = bundle.getInt("friendList", 3);
        myFriendsSort();



        switch(myInt){
            case(0):
                ListView lva = (ListView) rootView.findViewById(android.R.id.list);
                Log.i("myFriends", "mycFriends");
                Log.i("mycfriends size", String.valueOf(myFriends.size()));
                friendAdapter = new FriendAdapter(getActivity(), R.layout.list_item_friend, android.R.id.list, myFriends, myInt);
                lva.setAdapter(friendAdapter);
                break;
            case(1):
                ListView lvb = (ListView) rootView.findViewById(android.R.id.list);
                Log.i("myFriendRequests", "myFriendRequests");
                Log.i("myFriendRequests size", String.valueOf(myFriendRequests.size()));
                friendAdapter = new FriendAdapter(getActivity(), R.layout.list_item_friend_request, android.R.id.list, myFriendRequests,myInt);
                lvb.setAdapter(friendAdapter);
                break;
            case(2):
                ListView lvc = (ListView) rootView.findViewById(android.R.id.list);
                Log.i("myFriendsPending", "myFriendsPending");
                Log.i("myFriendsPending size", String.valueOf(myFriends.size()));
                friendAdapter = new FriendAdapter(getActivity(), R.layout.list_item_friend_pending, android.R.id.list, myFriendPending,myInt);
                lvc.setAdapter(friendAdapter);
                break;
            default:
                break;
        }
        return rootView;
    }
    public void onListItemClick(ListView l,View v,int position,long id) {
        Log.i("clickclick", "click");
        String friendChosen = friendAdapter.getItem(position);
        fromActivity();
    }
    public void myFriendsSort(){
        toParserHandler();
    }

}