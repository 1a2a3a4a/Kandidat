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
import java.util.regex.Pattern;

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
    public boolean invalidPattern(String tryString) {
        Pattern pattern = Pattern.compile("[^ÅÄÖåäö]{1,1000}$");
        if(pattern.matcher(tryString).matches()){
            return false;
        }
        return true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_text);
    }
        public void throughphp(String textToUpload){
            String type = "Upload text";
            SaveSharedData.clearGenQuestions(UploadTextActivity.this);
            SaveSharedData.setFromGenIndex(UploadTextActivity.this,0);
            SaveSharedData.setToGenIndex(UploadTextActivity.this,0);
            BackgroundWithServer bgws = new BackgroundWithServer(this);
            bgws.execute(type,textToUpload);
        }

        public void uploadTextToGenerate(View view){

            //Button generateButton = (Button) findViewById(R.id.generateButton);
            if(counter == 0) {

                EditText textEditText = (EditText) findViewById(R.id.textEditText);

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
                if (converted.length() > 0){
                    if (invalidPattern(converted)){
                        Toast.makeText(UploadTextActivity.this, "The text contains unwanted characters", Toast.LENGTH_SHORT).show();
                        counter =0;

                    }
                    else {
                        Toast.makeText(UploadTextActivity.this, "Generating... please wait a moment", Toast.LENGTH_SHORT).show();
                        counter = 1;
                        throughphp(converted);
                    }
                }
                else{
                    Toast.makeText(UploadTextActivity.this, "Too short to be generated", Toast.LENGTH_SHORT).show();
                    counter=0;
                }


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
