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
class MyFragmentAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private final ArrayList<String> mFragmentTitleList = new ArrayList<>();
    private  ArrayList<ArrayList<Game>>    mFragmentGameList =new ArrayList<ArrayList<Game>>();

    public MyFragmentAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle info1=new Bundle();
        info1.putInt("gameList", position);
       // Log.i("aaaaaaaaaaa","aaaaaaaaaaaaa");
        mFragmentList.get(position).setArguments(info1);
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title, ArrayList<Game> gameList) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
        mFragmentGameList.add(gameList);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
