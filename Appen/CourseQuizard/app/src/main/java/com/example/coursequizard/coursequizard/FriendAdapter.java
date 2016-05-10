package com.example.coursequizard.coursequizard;

import android.content.Context;
//import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class FriendAdapter extends ArrayAdapter<String> {
    //private ArrayList<Game> objects;
    private Context context1;
    private ArrayList<String> f = new ArrayList<String>();
    private int p;
    private int tab;
    private ArrayList<String> friends = new ArrayList<String>();

    public FriendAdapter(Context context, int textViewResourceId, ArrayList<String> objects, int t) {
        super(context, textViewResourceId, objects);
        //this.objects = objects;
        this.friends= objects;
        this.tab=t;
    }

    public FriendAdapter(Context context, int resource, int textViewResourceId, ArrayList<String> items,int t) {
        super(context, resource, textViewResourceId, items);
        this.context1 = context;
        this.friends= items;
        this.tab=t;
    }

    public String swapButton(ImageButton button, String tag){
        if (tag.equals("on")) {
            button.setImageResource(R.drawable.star_off);
            return "off";
        }
        else{
            button.setImageResource(R.drawable.star_on);
            return "on";

        }
    }

    // Fixa varje scenario
    private void sendFriendRequest(String friend){
        String username = SaveSharedData.getUserName(getContext());
        //type touser byuser 0 for remove 1 for accept
        BackgroundWithServer b = new BackgroundWithServer(getContext());
        String type = "friend request";
        b.execute(type, friend);
    }
    private void removeFriend(String friend){
        String username = SaveSharedData.getUserName(getContext());
        //type touser byuser 0 for remove or decline  1 for accept or send request
        BackgroundWithServer b = new BackgroundWithServer(getContext());
        String type = "update relation";
        b.execute(type, friend,username,"0");
    }
    private void declineSentRequest(String friend){
        String username = SaveSharedData.getUserName(getContext());
        //type touser byuser 0 for remove or decline  1 for accept or send request
        BackgroundWithServer b = new BackgroundWithServer(getContext());
        String type = "update relation";
        b.execute(type, username, friend, "0");
    }
    private void declineRecievedRequest(String friend){
        String username = SaveSharedData.getUserName(getContext());
        //type touser byuser 0 for remove or decline  1 for accept or send request
        BackgroundWithServer b = new BackgroundWithServer(getContext());
        String type = "update relation";
        b.execute(type, friend,username,"0");
    }
    private void acceptRecievedRequest(String friend){
        String username = SaveSharedData.getUserName(getContext());
        //type touser byuser 0 for remove or decline  1 for accept or send request
        BackgroundWithServer b = new BackgroundWithServer(getContext());
        String type = "update relation";
        b.execute(type, friend,username,"1");
    }

    public View myFriendsView(View fview, int position){
        String i = getItem(position);
        final ImageButton editFriend = (ImageButton) fview.findViewById(R.id.list_item_friend_button);
        editFriend.setImageResource(R.drawable.star_off);
        // courseStar.setTag(position);
        editFriend.setTag(R.id.friend_edit_position,position);
        editFriend.setTag(R.id.friend_edit_image,"off");
        if (tab ==0){
            editFriend.setImageResource(R.drawable.star_on);
            editFriend.setTag(R.id.friend_edit_image,"on");
        }

        editFriend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int pos = (Integer) view.getTag(R.id.friend_edit_position);
                String friendChosen = friends.get(pos);
             //   Log.i("pushed on friend", friendChosen);
                String editTag= swapButton(editFriend,(String) view.getTag(R.id.friend_edit_image));
                if (editTag.equals("off")) {
                    removeFriend(friendChosen);
                    // myFriends.add(friendChosen);}
                }
                if   (editTag.equals("on")){
                    sendFriendRequest(friendChosen);
                }
                view.setTag(R.id.friend_edit_image,editTag);
            }

        });


        if (i != null) {



            TextView friendName = (TextView) fview.findViewById(R.id.list_item_friend_name);
            if (friendName != null) {
                friendName.setText(i);
            }
        }
        return fview;
    }

    public View myFriendRequestsView(View view, int position){
        String i = getItem(position);
        final ImageButton editRequest = (ImageButton) view.findViewById(R.id.list_item_friend_request_button);
        editRequest.setImageResource(R.drawable.star_off);
        editRequest.setImageResource(R.drawable.star_off);
        // courseStar.setTag(position);
        editRequest.setTag(R.id.friend_edit_request_position,position);
        editRequest.setTag(R.id.friend_edit_request_image,"off");
        if (tab ==0){
            editRequest.setImageResource(R.drawable.star_on);
            editRequest.setTag(R.id.friend_edit_request_image,"on");
        }

        editRequest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int pos = (Integer) view.getTag(R.id.friend_edit_request_position);
                String friendChosen = friends.get(pos);
                //Log.i("pushed on friend", friendChosen);

                String editTag= swapButton(editRequest,(String) view.getTag(R.id.friend_edit_request_image));
                if (editTag.equals("off")) {
                    declineRecievedRequest(friendChosen);
                    // myFriends.add(friendChosen);}
                }
                if   (editTag.equals("on")){
                    acceptRecievedRequest(friendChosen);
                }
                view.setTag(R.id.friend_edit_request_image,editTag);
            }

        });


        if (i != null) {



            TextView friendName = (TextView) view.findViewById(R.id.list_item_friend_request_name);
            if (friendName != null) {
                friendName.setText(i);
            }
        }
        return view;
    }
    public View myFriendPendingView(View view, int position){
        String i = getItem(position);
        final ImageButton editRequest = (ImageButton) view.findViewById(R.id.list_item_friend_pending_button);
        editRequest.setImageResource(R.drawable.star_on);
        // courseStar.setTag(position);
        editRequest.setTag(R.id.friend_edit_pending_position,position);
        editRequest.setTag(R.id.friend_edit_pending_image,"on");
        editRequest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int pos = (Integer) view.getTag(R.id.friend_edit_pending_position);
                String friendChosen = friends.get(pos);
               // Log.i("pushed on friend", friendChosen);
                String editTag= swapButton(editRequest,(String) view.getTag(R.id.friend_edit_pending_image));
                if (editTag.equals("off")) {
                    declineSentRequest(friendChosen);
                    // myFriends.add(friendChosen);}
                }
                if   (editTag.equals("on")){
                    sendFriendRequest(friendChosen);
                }
                view.setTag(R.id.friend_edit_pending_image,editTag);
            }

        });


        if (i != null) {



            TextView friendName = (TextView) view.findViewById(R.id.list_item_friend_pending_name);
            if (friendName != null) {
                friendName.setText(i);
            }
        }
        return view;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        p =position;
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            switch (tab) {
                case (0):
                    v = inflater.inflate(R.layout.list_item_friend, null);
                    break;
                case (1):
                    v = inflater.inflate(R.layout.list_item_friend_request, null);

                    break;
                case (2):
                    v = inflater.inflate(R.layout.list_item_friend_pending, null);

                    break;
                default:
                    break;
            }
        }
        switch (tab) {
            case (0):
                v = myFriendsView(v, position);
                break;
            case (1):
                v = myFriendRequestsView(v, position);
                break;
            case (2):
                v = myFriendPendingView(v, position);
                break;
            default:
                break;
        }

        return v;
    }

}