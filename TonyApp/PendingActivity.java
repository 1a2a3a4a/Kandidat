package com.example.coursequizard.coursequizard;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PendingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending);
        ArrayList<String> pendingList = getPendingListfromIntent();
        ListView pendingListView = (ListView) findViewById(R.id.pendingListView);
        if(!(pendingList.get(0).equals("EMPTY"))) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pendingList);
            pendingListView.setAdapter(new MyListAdapter(this, R.layout.list_item_pending, pendingList, pendingList));
        }
       else{ Toast.makeText(PendingActivity.this, "No current pending friend requests", Toast.LENGTH_LONG).show();}

    }


    public ArrayList<String> getPendingListfromIntent(){
        ArrayList<String> message = new ArrayList<String>();
        message =  getIntent().getExtras().getStringArrayList("prevActivity");
        String result = message.get(1);
        ArrayList<String> pending = new ArrayList<String>();
        String[] pendings = result.split("%U%");
        int pendingLength = pendings.length; // length of coded friendlist string[]
        Log.i("pendinglistlength", String.valueOf(pendingLength));
        for(int i = 0; i < pendingLength; i++){
            pending.add(pendings[i]); //add all friends in friendlist
        }
    return pending;
    }

    private class MyListAdapter extends ArrayAdapter<String> {
        private int layout;
        private ArrayList<String> pendingList;
        public MyListAdapter(Context context, int resource, ArrayList<String> objects, ArrayList<String> pendingList) {
            super(context, resource, objects);
            layout = resource;
            this.pendingList = pendingList;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainView = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.button = (Button) convertView.findViewById(R.id.acceptFriendButton);
                viewHolder.text = (TextView) convertView.findViewById(R.id.pendingName);
                convertView.setTag(viewHolder);
            }
            mainView = (ViewHolder) convertView.getTag();
            final Button iB = mainView.button;
            iB.setTag(position);
            iB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iB.setClickable(false);
                    int position = (Integer)iB.getTag(); // the position in the list index
                    String friendToAdd  = pendingList.get(position);

                    BackgroundWithServer bgws = new BackgroundWithServer(PendingActivity.this);
                    bgws.execute("friend request", friendToAdd);
                }
            });

            mainView.text.setText(getItem(position));

            return convertView;
        }
        public class ViewHolder{
            Button button;
            TextView text;
        }
    }

}
