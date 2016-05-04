package com.example.coursequizard.coursequizard;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;








public class FriendsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<String> myFriends;
    private ArrayList<String> myFriendRequests;
    private ArrayList<String> myPendingFriends;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        toolbar = (Toolbar) findViewById(R.id.toolbarFriends);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        // getSupportActionBar().setDisplayShowTitleEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpagerFriends);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabsFriends);
        tabLayout.setupWithViewPager(viewPager);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabFriends);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddFriendActivity();
            }
        });
        fromActivityFab(fab);
    }
    private void fromActivityFab (FloatingActionButton fab){
        fab.setVisibility(View.INVISIBLE);
        ArrayList<String> message = new ArrayList<String>();
        message = getIntent().getExtras().getStringArrayList("prevActivity");
        if (message.get(0).equals("fromMainActivity") || message.get(0).equals("fromMyProfileActivity")){
            fab.setVisibility(View.VISIBLE);
        }
    }
    public void toAddFriendActivity(){
            ArrayList<String> message = new ArrayList<String>();
            Intent i = new Intent(getApplicationContext(), AddFriendActivity.class);
            message.add("FromFriendsActivity");
            i.putExtra("prevActivity", message);
            startActivity(i);
        }



    private void setupViewPager(ViewPager viewPager) {
        FriendFragmentAdapter adapter = new FriendFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new FriendFragment(), "myfriends", myFriends);
        adapter.addFragment(new FriendFragment(), " friend requests", myFriendRequests);
        adapter.addFragment(new FriendFragment(), "pending", myPendingFriends);
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
/*
public class FridendsActivity extends AppCompatActivity {

    public ArrayList<String> myFriends = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        ListView friendsListView = (ListView) findViewById(R.id.friendsListView);
        myFriends = SaveSharedData.getFriendList(FriendsActivity .this );
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout. simple_list_item_1,myFriends );
        //friendsListView.setAdapter(arrayAdapter);
        friendsListView.setAdapter(new MyListAdapter(this, R.layout.list_item_delete, myFriends));
    }
    private class MyListAdapter extends ArrayAdapter<String> {
        private int layout;

        public MyListAdapter(Context context, int resource, ArrayList<String> objects) {
            super(context, resource, objects);
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainView = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.button = (Button) convertView.findViewById(R.id.deleteFriendButton);
                viewHolder.text = (TextView) convertView.findViewById(R.id.friendName);
                convertView.setTag(viewHolder);
            }
            mainView = (ViewHolder) convertView.getTag();
            final Button iB = mainView.button;
            iB.setTag(position);
            iB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (Integer)iB.getTag(); // the position in the list index
                    iB.setClickable(false);
                    ArrayList<String> friendList = SaveSharedData.getFriendList(FriendsActivity.this);
                    String friendToDelete  = friendList.get(position);
                    BackgroundWithServer bgws = new BackgroundWithServer(FriendsActivity.this);
                    bgws.execute("delete friend", friendToDelete);
                }
            });
            mainView.text.setText(getItem(position));

            return convertView;
        }
    }
    public class ViewHolder{
        Button button;
        TextView text;
    }
}
*/

