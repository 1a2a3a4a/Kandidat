package com.example.coursequizard.coursequizard;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
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
