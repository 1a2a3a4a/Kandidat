package com.example.coursequizard.coursequizard;

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

/*
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link onListFragmentInteractionListener}
 * interface.
 */
public class GameItemFragment extends ListFragment {
    public static final String ARG_SECTION_NUMBER = "section_number";

    // TODO: Customize parameter argument names
    //private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    //private int mColumnCount = 1;
   // private OnListFragmentInteractionListener mListener;
    private ArrayList<Game> myGames = new ArrayList<Game>();
    private ArrayList<Game> opTurnGames = new ArrayList<Game>();
    private ArrayList<Game> myTurnGames = new ArrayList<Game>();
    private ArrayList<Game> finishedGames = new ArrayList<Game>();
    private Runnable viewParts;
    private GameItemAdapter myAdapter;
    private int mIndex;
    private int myInt =3;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GameItemFragment(){

    }
    public void toExtendedGameInfo(String gID){

    }
    public void fromActivity(){
        ArrayList<String> message = new ArrayList<String>();
        message = getActivity().getIntent().getExtras().getStringArrayList("prevActivity");
        if (message.get(0).equals("fromMainActivity")){
            String unparsedMyGames = message.get(1);
            CQParser parser = new CQParser();
            Log.i("intent",unparsedMyGames);
            myGames= parser.toGList(unparsedMyGames);
            Log.i("intent",myGames.get(0).getUser_1());
            myGamesSort();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle extras = getActivity().getIntent().getExtras();
        //myGames  = extras.getParcelableArrayList("arraylist");
        fromActivity();
        Log.i("first game",myGames.get(0).getUser_2());
        View rootView = inflater.inflate(R.layout.fragment_gameitem_list, container, false);
        Bundle bundle = this.getArguments();
        myInt = bundle.getInt("gameList", 3);


        Log.i("beforeswitch", "beforeswitch");
        Log.i("totalsize", String.valueOf(myGames.size()));

        /*myTurnGames.clear();
        opTurnGames.clear();
        finishedGames.clear();
        */
        switch(myInt){
            case(0):
                ListView lva = (ListView) rootView.findViewById(android.R.id.list);
                Log.i("myturn", "myturn");
                Log.i("myturn size", String.valueOf(myTurnGames.size()));
                myAdapter = new GameItemAdapter(getActivity(), R.layout.fragment_gameitem, android.R.id.list, myTurnGames);

                lva.setAdapter(myAdapter);
                break;
            case(1):
                ListView lvb = (ListView) rootView.findViewById(android.R.id.list);
                Log.i("opturn", "opturn");
                Log.i("opturn size", String.valueOf(opTurnGames.size()));
                myAdapter = new GameItemAdapter(getActivity(), R.layout.fragment_gameitem, android.R.id.list, opTurnGames);
                lvb.setAdapter(myAdapter);
                break;
            case(2):
                ListView lvc = (ListView) rootView.findViewById(android.R.id.list);
                Log.i("finito", "finito");
                Log.i("finished size", String.valueOf(finishedGames.size()));
                myAdapter = new GameItemAdapter(getActivity(), R.layout.fragment_gameitem, android.R.id.list, finishedGames);
                lvc.setAdapter(myAdapter);
                break;
            default:
                ListView lvd = (ListView) rootView.findViewById(android.R.id.list);
                myAdapter = new GameItemAdapter(getActivity(), R.layout.fragment_gameitem, android.R.id.list, myGames);
                lvd.setAdapter(myAdapter);
                break;
        }

        Log.i("afterinit","woohooh");


        return rootView;

    }
    @Override
    public void onListItemClick(ListView l,View v,int position,long id){
        Log.i("clickclick","click");
        Game gameItemChosen = myAdapter.getItem(position);
        String gID = String.valueOf(gameItemChosen.getG_id());
        int stateInt = gameItemChosen.getGame_status();
        Log.i("gid",gID);
        Log.i("tab",String.valueOf(myInt));
        Log.i("state",String.valueOf(stateInt));
        if (myInt ==0) {
            if (stateInt==0 || stateInt ==2)
            toPlayGameActivity(gID);
            else{
                String type = "updatestate";
                BackgroundWithServer bgws =new BackgroundWithServer(getActivity());
                bgws.execute(type,gID);
                Toast.makeText(getActivity(), "You have canceled this game, 0 score have been sent", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void toPlayGameActivity(String qID){
        String type = "quiz from my games";
        BackgroundWithServer bgws =new BackgroundWithServer(getActivity());
        bgws.execute(type,qID);
    }
    public void myGamesSort(){
       ArrayList<Game> tempopTurnGames = new ArrayList<Game>();
         ArrayList<Game> tempmyTurnGames = new ArrayList<Game>();
         ArrayList<Game> tempfinishedGames = new ArrayList<Game>();
        String userName = SaveSharedData.getUserName(getActivity());
        int size = myGames.size();
        for (int j =0; j<  size; j++){
            Game g = myGames.get(j);
            Log.i("gameid",String.valueOf(g.getG_id()));
            Log.i("User1 Score",String.valueOf(g.getUser1_score()));
            Log.i("User2 Score",String.valueOf(g.getUser2_score()));
            Log.i("gamelist sent by",g.getSent_by());
            Log.i("gamelist state",String.valueOf(g.getGame_status()));
            switch(g.getGame_status()){
                //pending
                case(0):
                    if (userName.equals(g.getUser_1())){
                        tempopTurnGames.add(g);
                    }
                    else {
                        tempmyTurnGames.add(g);
                    }
                    break;
                // user 2 turn
                case(1):
                    if (userName.equals(g.getUser_1())){
                        tempopTurnGames.add(g);
                    }
                    else {
                        tempmyTurnGames.add(g);
                    }
                    break;
                // user 1 turn
                case(2):
                    if (userName.equals(g.getUser_1())){
                        tempmyTurnGames.add(g);
                    }
                    else {
                        tempopTurnGames.add(g);
                    }
                    break;
                //finished
                case(3):
                    if (userName.equals(g.getUser_1())){
                        tempmyTurnGames.add(g);
                    }
                    else {
                        tempopTurnGames.add(g);
                    }
                    break;
                case(4):
                    tempfinishedGames.add(g);
                    break;
                default :
                    break;



            }
        }
        finishedGames= tempfinishedGames;
        opTurnGames= tempopTurnGames;
        myTurnGames = tempmyTurnGames;
        //tempfinishedGames.clear();
        //tempopTurnGames.clear();
        //tempmyTurnGames.clear();

    }

/*
    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static GameItemFragment newInstance(int columnCount) {
        GameItemFragment fragment = new GameItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gameitem_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
           //
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    /*
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
    */
}
