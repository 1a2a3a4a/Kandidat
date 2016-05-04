package com.example.coursequizard.coursequizard;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Pattern;

/*import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
*/
public class RegisterActivity extends AppCompatActivity {
    String[] invalidUsernames = {"single player","singleplayer","randomopponent", "random opponent", "admin", "simontest","simontest2","empty"};

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client;
    public boolean invalidUsername(String username){
        for (int i =0; i < invalidUsernames.length; i++){
            Log.i("invalidrunning","invalid");
            if (username.equals(invalidUsernames[i])){
                return true;
            }

        }
        return false;
    }
    public boolean invalidPattern(String tryString) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9_!]{1,50}$");
        if(pattern.matcher(tryString).matches()){
            return false;
        }
        return true;

    }
    public void register(View view) {
        String errorMessage = "Invalid registry information";
        EditText username = (EditText) findViewById(R.id.usernameText);
        EditText password = (EditText) findViewById(R.id.passwordText);
        EditText cpassword = (EditText) findViewById(R.id.confirmpwText);

        String usernameString = username.getText().toString();
        String passwordString =  password.getText().toString();
        String cpasswordString =  cpassword.getText().toString();
        usernameString = usernameString.replace(" ", "");

        passwordString = passwordString.replace(" ", "");
        cpasswordString = cpasswordString.replace(" ", "");
        BackgroundWithServer bgws = new BackgroundWithServer(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });


        if(invalidPattern(usernameString) || usernameString.matches("") ){
            errorMessage = "invalid username";
            builder.setMessage(errorMessage);
            AlertDialog alert = builder.create();
            alert.show();
        }
        else if (!(passwordString.equals(cpasswordString))){
            errorMessage = "password not equal";
            builder.setMessage(errorMessage);
            AlertDialog alert = builder.create();
            alert.show();
        }

        else if (passwordString.matches("") || cpasswordString.matches("") || invalidPattern(passwordString) || invalidPattern(cpasswordString)){
            errorMessage = "invalid password";
            builder.setMessage(errorMessage);
            AlertDialog alert = builder.create();
            alert.show();
        }

        else {
            bgws.execute("register", usernameString, passwordString, cpasswordString);
        }

            //få tillbaka ett ok sen gå tillbaka till loginaktiviteten eller att det redan finns och returna.

        //få en ruta som säger att det inte funkade
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //final EditText username = (EditText) findViewById(R.id.loginusernameText);
        //final EditText password = (EditText) findViewById(R.id.loginpwtext);
        //final EditText cpasssword = (EditText) findiewById(R.id.confirmpwText);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
/*
    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Register Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.coursequizard.coursequizard/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }
    */
/*
    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Register Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.coursequizard.coursequizard/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
    */
}