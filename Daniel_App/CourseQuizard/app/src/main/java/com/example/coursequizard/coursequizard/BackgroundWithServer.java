package com.example.coursequizard.coursequizard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.app.AlertDialog;
import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * The following code is based on http://programmingknowledgeblog.blogspot.se/2016/01/android-mysql-database-tutorial-2.html
 * This class handles the communication with the database server
 * operationURL The URL to the specific php script
 *  post_data: A url encoded string to add as argument for the operationURL
 *  context: This background service
 *  Type: what kind of arguments we recieve from the activities
 *  Opponent: the opponent variable is necessary when creating a game
 *  alertDialog: The popup that can be used so the users can recieve a confirm or an error message
 *
 *
 */
public class BackgroundWithServer extends AsyncTask<String,Void,String> {
    private String connection = "http://130.238.250.231/";
    String operationURL="";
    String post_data = "";
    Context context;
    String type = "";
    String opponent ="";
    AlertDialog alertDialog;
    BackgroundWithServer (Context ctx) {
        context = ctx;
    }


    /**
     * Fitting the arguments to the specific URL
     * @param params all paramters neccecary for adding a question to the database
     */

    private void addQuestion(String... params) {

        String question = params[1];
        String answer = params[2];
        String alt1 = params[3];
        String alt2 = params[4];
        String alt3 = params[5];
        String cID  = params[6];
        try {
            post_data = URLEncoder.encode("question", "UTF-8") + "=" + URLEncoder.encode(question, "UTF-8") + "&"
                    + URLEncoder.encode("answer", "UTF-8") + "=" + URLEncoder.encode(answer, "UTF-8") + "&"
                    + URLEncoder.encode("alt1", "UTF-8") + "=" + URLEncoder.encode(alt1, "UTF-8") + "&"
                    + URLEncoder.encode("alt2", "UTF-8") + "=" + URLEncoder.encode(alt2, "UTF-8") + "&"
                    + URLEncoder.encode("alt3", "UTF-8") + "=" + URLEncoder.encode(alt3, "UTF-8") + "&"
                    + URLEncoder.encode("c_id", "UTF-8") + "=" + URLEncoder.encode(cID, "UTF-8") + "&";


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Fitting the arguments to the specific URL
     * @param params all paramters neccecary for adding a question to the database
     */

    private void addCourse(String ... params){
        String courseName = params[1];
        String courseID   = params[2];
        String uniID      =params[3];
        try {
            post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(courseName, "UTF-8") + "&"
                    + URLEncoder.encode("course_code", "UTF-8") + "=" + URLEncoder.encode(courseID, "UTF-8") + "&"
                    + URLEncoder.encode("uni_id", "UTF-8") + "=" + URLEncoder.encode(uniID, "UTF-8") + "&";


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Fitting the arguments to the specific URL
     * @param params all paramters neccecary for adding a question to the database
     */
    //////////////////////////////////
    //DANIELS
    private void getMyCourses(String ... params){
        String user_name = params[1];
        try {
            this.post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void swapFavCourses(String[] params) {
        String c_id = params[1];
        String user_name = params[2];
        try {
            this.post_data = URLEncoder.encode("c_id", "UTF-8") + "=" + URLEncoder.encode(c_id, "UTF-8") + "&"
                            +URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //SLUT PÅ DANIELS
    ////////////////////////////////
    private void myCourseList(String ... params ){
        String user_name = params[1];

    }
    /**
     * Fitting the arguments to the specific URL
     * @param params all paramters neccecary for adding a question to the database
     */

    private void myCourseListFromOpponent(String ... params){
        opponent = params[1];
    }
    @Override
    /**
     * Communicates with the database server. First writes to the server, then reads the output.
     * @param params all paramters neccecary for adding a question to the database
     * @returns the output from the php script
     */

    protected String doInBackground(String... params) {
        type = params[0];
        switch (type) {
            case "add question": {
                addQuestion(params);
                operationURL = this.connection + "addquestiontodb.php";
                break;
            }
            case "add course": {
                addCourse(params);
                operationURL = this.connection + "addcoursetodb.php";
                break;
            }
            case "my courses": {
                myCourseList(params);
                operationURL = this.connection + "getcoursesfromdb.php";
            }
                break;
            case "my courses, from opponent": {
                myCourseListFromOpponent(params);
                operationURL = this.connection + "getcoursesfromdb.php";

                break;
            }
            //DANIELS
            case "get my courses": {
                getMyCourses(params);
                operationURL = this.connection + "getmycoursesfromdb.php";
                break;
            }
            case "swap favorites": {
                swapFavCourses(params);
                operationURL = this.connection + "swapfavoritestodb.php";
                break;
            }

            //SLUT PÅ DANIELS
        }
            try {
                URL url = new URL(operationURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                // the arguments to the URL
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                //StringBuilder sb = new StringBuilder();
                String result="";
                String line="";
                //Read line by line the output from the server.
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                    Log.i("resultat",line);
                    //sb.append(line + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



        return null;
    }


    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Message");
    }

    /**
     * This method post a popup for the user containing info if success or fail.
     * Also starts the next method depending on the type of arguments recieved from one of the Activities.
     *
     * @param result the output from the php script
     */
    @Override
    protected void onPostExecute(String result) {
        switch (type) {
            case "my courses": {
                // send a string containing all the courses in the database and start the Course Activity
                Intent i = new Intent(context, CourseActivity.class);
                String type = "my courses";
                ArrayList<String> send = new ArrayList<String>();
                send.add("fromCreateQuestionActivity");
                send.add(result);
                i.putExtra("prevActivity", send);
                context.startActivity(i);

                break;
            }
            case "my courses, from opponent": {
            /* send a string containing all the courses in the database
            / and a string containing the information about the opponent
             and start the Course Activity
             */
                Intent i = new Intent(context, CourseActivity.class);
                Log.i("hello", "hello");
                Log.i("opponent", opponent);
                Log.i("result", result);
                ArrayList<String> send = new ArrayList<String>();
                send.add("fromOpponentActivity");
                send.add(result);
                send.add(opponent);
                i.putExtra("prevActivity", send);
                context.startActivity(i);
                break;
            }
            /////////////////////////////////
            //DANIELS KOD
            case "get my courses": {
                Intent i = new Intent(context, MyCoursesActivity.class);
                Log.i("result", result);
                ArrayList<String> send = new ArrayList<String>();
                send.add("fromMyProfileActivity");
                send.add(result);
                i.putExtra("prevActivity", send);
                context.startActivity(i);
                break;
            }
            case "swap favorites": {
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

                Log.i("swap res", result);
               // alertDialog.setMessage(result);
                //Toast.show();
                break;
            }
            //SLUT PÅ DANIEL
            ///////////////////////////////////
            // show a popup with the output
            default: {
                //Log.i("Result", result);
                alertDialog.setMessage(result);
                alertDialog.show();
                break;
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}