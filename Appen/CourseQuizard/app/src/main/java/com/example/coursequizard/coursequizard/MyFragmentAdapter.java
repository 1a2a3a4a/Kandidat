package com.example.coursequizard.coursequizard;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by Simon on 2016-04-27.
 * This class create tabs :D
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    final int pageCount=3;
    //Bundle info;
    ArrayList<Game> myTurnList = new ArrayList<Game>();
    ArrayList<Game> opTurnList = new ArrayList<Game>();
    ArrayList<Game> finishedList = new ArrayList<Game>();
    public MyFragmentAdapter(FragmentManager fm){

        super(fm);
    }
    @Override
    public Fragment getItem(int pos) {
        // TODO Auto-generated method stub




        //CHECK SELECTED TAB

        switch (pos) {
            case 0:
                GameItemFragment myTurn =new GameItemFragment();
                Bundle info1=new Bundle();
                info1.putInt("gameList", 0);
                Log.i("aaaaaaaaaaa","aaaaaaaaaaaaa");
                myTurn.setArguments(info1);
                //opponentTurn.setArguments(info);
                //info.putParcelableArrayList("gameList", myTurnList);
                return myTurn;

            case 1:
                Bundle info2=new Bundle();
                GameItemFragment opponentTurn=new GameItemFragment();
                // info.putInt("currentPage", pos++);
                info2.putInt("gameList", 1);
                Log.i("bbbbbbbbbbbb","bbbbbbbbbbbbbbb");
                opponentTurn.setArguments(info2);
                //myTurn.setArguments(info);
                //info.putParcelableArrayList("gameList", opTurnList);
                return opponentTurn;
            case 2:
                Bundle info3=new Bundle();
                GameItemFragment history=new GameItemFragment();
                info3.putInt("gameList", 2);
                Log.i("cccccccccccc","ccccccccccc");
                history.setArguments(info3);
                //info.putInt("currentPage", pos++);
                //history.setArguments(info);
                // info.putParcelableArrayList("gameList", finishedList);
                return history;
            case 3:
                Log.i("ddddddddddddd","ddddddddddddddd");

                return null;
            default:
                Log.i("default","default");


        }

        return null;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return pageCount;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if (position ==0){
            return "My turn";
        }
        if (position ==1){
            return "Opponents Turn";
        }
        if (position ==2){
            return "Finished";
        }
        if (position ==3){
            return "Test";
        }
        return "False";
    }
}
