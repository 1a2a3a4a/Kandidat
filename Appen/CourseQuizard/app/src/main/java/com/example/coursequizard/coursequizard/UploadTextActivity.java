package com.example.coursequizard.coursequizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Upload text for automatic question generation. text is uploaded to python server using sockets.
 */
public class UploadTextActivity extends AppCompatActivity {
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
    // for socket communication
    private class MyAsyncTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPostExecute(Void result) {
            //Task you want to do on UIThread after completing Network operation
            //onPostExecute is called after doInBackground finishes its task.
        }

        @Override
        protected Void doInBackground(Void... params) {
            textToServer(uploadText);

            return null;
        }
    }
    public String questionFromServer(Socket socket) {
        String response = new String();
        try {
            Log.i("Inputstream","E");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Log.i("Read string","F");
           response = in.readLine();

        } catch (Exception e) {
            Log.i("Recieve catch", e.toString());
        }
        return response;
    }
    public void textToServer(String text){
           String response = "";

        try {

            Log.i("Connect","Aha");
            Socket socket = new Socket("130.238.246.232",4999);
            Log.i("OutputStream","B");
            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
            Log.i("Send","C");
            //DOS.writeUTF(question);
            socket.getOutputStream().write(text.getBytes());
            Log.i("Recieve function","D");
            response = questionFromServer(socket);

            Log.i("Closing", "E");

            socket.close();


            Log.i("Closed", "F");

        }
        catch (Exception e){
            Log.i("Send catch",e.toString());
        }
        Log.i("From Tony", response);
        toCreateQuestionActivity(response);

    }

        public void uploadTextToGenerate(View view){
            //Button generateButton = (Button) findViewById(R.id.generateButton);
            EditText textEditText = (EditText) findViewById(R.id.textEditText);
            uploadText = textEditText.getText().toString();
            MyAsyncTask clientTask = new MyAsyncTask();
            clientTask.execute();




    }
}
