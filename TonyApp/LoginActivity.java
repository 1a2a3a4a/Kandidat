package com.example.coursequizard.coursequizard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    public void register(View view){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }


    public void login(View view) {
        EditText username = (EditText) findViewById(R.id.loginusernameText);
        EditText password = (EditText) findViewById(R.id.loginpwtext);
        BackgroundWithServer bgws = new BackgroundWithServer();
        if (!(username.getText().length() == 0 || password.getText().length() == 0)) {
            bgws.execute("login", username.getText().toString(), password.getText().toString());
            //om det funkade ta mig till mainactivity
        }
        // om det inte funkade skriv en ruta och säga att det inte funkade
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText username = (EditText) findViewById(R.id.loginusernameText);
        final EditText password = (EditText) findViewById(R.id.loginpwtext);
    }


}
