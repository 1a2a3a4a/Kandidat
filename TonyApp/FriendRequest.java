package com.example.coursequizard.coursequizard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FriendRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);
    }

    public void addFriend(View view){
        EditText friendText = (EditText) findViewById(R.id.friendText);
        String friendTextString = friendText.getText().toString();

        if(!(friendTextString.matches(""))){
            BackgroundWithServer bgws = new BackgroundWithServer(FriendRequest.this);
            bgws.execute("friend request", friendTextString);
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("invalid name")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

    }
}
