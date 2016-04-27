package com.example.coursequizard.coursequizard;

import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {

    public void register(View view){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }


    public void login(View view) {
        EditText username = (EditText) findViewById(R.id.loginusernameText);
        EditText password = (EditText) findViewById(R.id.loginpwtext);
        String usernameString = username.getText().toString();
        String passwordString =  password.getText().toString();
        BackgroundWithServer bgws = new BackgroundWithServer(this);
        if (!(usernameString.matches("") || usernameString.matches(""))) {
            bgws.execute("login", usernameString, passwordString);
            //om det funkade ta mig till mainactivity
        }
        // om det inte funkade skriv en ruta och säga att det inte funkade
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Failed to login, invalid username or password")
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
    public void toMainActivity(View view){
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        ArrayList<String> send = new ArrayList<String>();
        send.add("fromLoginActivity");
        send.add("skip");
        i.putExtra("prevActivity",send );
        startActivity(i);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText username = (EditText) findViewById(R.id.loginusernameText);
        final EditText password = (EditText) findViewById(R.id.loginpwtext);
    }
    @Override
    public void onBackPressed() {
    }


}

