package com.example.coursequizard.coursequizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Upload text for automatic question generation. text is uploaded to python server using sockets.
 */
public class UploadTextActivity extends AppCompatActivity {
    int counter = 0;
    public String uploadText = new String();
    public void toCreateQuestionActivity(String questions){
        Intent i = new Intent(getApplicationContext(),CreateQuestionActivity.class);
        ArrayList<String> send = new ArrayList<String>();
        send.add("fromUploadTextActivity");
        send.add(questions);
        i.putExtra("prevActivity",send );
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_text);
    }
        public void throughphp(String textToUpload){
            String type = "Upload text";
            BackgroundWithServer bgws = new BackgroundWithServer(this);
            bgws.execute(type,textToUpload);
        }

        public void uploadTextToGenerate(View view){

            //Button generateButton = (Button) findViewById(R.id.generateButton);
            if(counter == 0) {
                EditText textEditText = (EditText) findViewById(R.id.textEditText);
                counter = 1;
                String convertThisText = textEditText.getText().toString();
                String converted ="";
                String tempString ="";

                BufferedReader reader = new BufferedReader(
                        new StringReader(convertThisText));

                try {
                    while ((tempString = reader.readLine()) != null) {

                        if (tempString.length() > 0){

                            converted +=tempString;
                        }
                        else{
                            converted +=" ";
                        }

                    }

                } catch(IOException e) {
                    e.printStackTrace();
                }
                throughphp(converted);

            }
            else{
                counter++;
            }
            if(counter >= 10){
                Toast.makeText(UploadTextActivity.this, "DIBIDBDIDBI DONT touch that...", Toast.LENGTH_SHORT).show();
            }
           // MyAsyncTask clientTask = new MyAsyncTask();
           // clientTask.execute();




    }
}
