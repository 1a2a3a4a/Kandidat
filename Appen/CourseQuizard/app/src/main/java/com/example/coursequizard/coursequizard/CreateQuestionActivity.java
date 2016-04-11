package com.example.coursequizard.coursequizard;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.DataOutputStream;
import java.net.Socket;

public class CreateQuestionActivity extends AppCompatActivity {
    public String question = "Empty";
    private class MyAsyncTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPostExecute(Void result) {
            //Task you want to do on UIThread after completing Network operation
            //onPostExecute is called after doInBackground finishes its task.
        }

        @Override
        protected Void doInBackground(Void... params) {
            questionToServer(question);
            return null;
        }
    }
    public void questionToServer(String question){

        try {
            Log.i("Question","E");
            Socket socket = new Socket("130.238.92.16",4999);
            Log.i("Question","A");
            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
            Log.i("Question","B");
            //DOS.writeUTF(question);
            socket.getOutputStream().write(question.getBytes());
            Log.i("Question", "C");
            socket.close();
            Log.i("Question", "D");
        }
        catch (Exception e){
            Log.i("Question",e.toString());
        }
    }
    public void sendQuestion(View view){
        EditText questionEditText = (EditText) findViewById(R.id.questionEditText);
        question = questionEditText.getText().toString();
        MyAsyncTask clientTask = new MyAsyncTask();
        clientTask.execute();


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
    }
}
