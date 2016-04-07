package com.example.coursequizard.coursequizard;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import java.io.DataOutputStream;
import java.net.Socket;
import android.util.Log;
import android.os.AsyncTask;

public class MainActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
