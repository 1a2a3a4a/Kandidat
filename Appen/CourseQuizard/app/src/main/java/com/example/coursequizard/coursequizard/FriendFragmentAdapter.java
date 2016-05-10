package com.example.coursequizard.coursequizard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FriendFragmentAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private final ArrayList<String> mFragmentTitleList = new ArrayList<>();
    private  ArrayList<ArrayList<String>>    mFragmentFriendList =new ArrayList<ArrayList<String>>();

    public FriendFragmentAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle info1=new Bundle();
        info1.putInt("friendList", position);
      //  Log.i("aaaaaaaaaaa","aaaaaaaaaaaaa");
        mFragmentList.get(position).setArguments(info1);
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title, ArrayList<String> friendList) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
        mFragmentFriendList.add(friendList);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}