package com.example.coursequizard.coursequizard;

import android.app.ListFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.coursequizard.coursequizard.dummy.DummyContent;
import com.example.coursequizard.coursequizard.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/*
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link onListFragmentInteractionListener}
 * interface.
 */
public class GameItemFragment extends ListFragment {

    // TODO: Customize parameter argument names
    //private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    //private int mColumnCount = 1;
   // private OnListFragmentInteractionListener mListener;
    private ArrayList<GameItem> myGames = new ArrayList<GameItem>();
    private Runnable viewParts;
    private GameItemAdapter myAdapter;
    private int mIndex;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GameItemFragment(){
    }
    public void toExtendedGameInfo(String gID){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gameitem_list, container, false);
        myAdapter = new GameItemAdapter(getActivity(),R.layout.fragment_gameitem, myGames);


        return rootView;

    }
    @Override
    public void onListItemClick(ListView l,View v,int position,long id){
        GameItem gameItemChosen = myAdapter.getItem(position);
        String gID = String.valueOf(gameItemChosen.getGameID());
        toExtendedGameInfo(gID);
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
