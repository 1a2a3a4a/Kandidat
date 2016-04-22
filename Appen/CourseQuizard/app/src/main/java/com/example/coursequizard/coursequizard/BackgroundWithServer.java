package com.example.coursequizard.coursequizard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.app.AlertDialog;
import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

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
    ArrayList<String> passCourseParameters = new ArrayList<String>();
    String operationURL="";
    String IP = "http://130.238.250.231";
    String post_data = "";
    Context context;
    String type = "";
    String opponentName="";
    String courseName="";
    String courseCode ="";
    String courseID ="";
    String universityName="";
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

    private void addCourse(String ... params) {
        courseName = params[1];
        courseCode = params[2];
        universityName = params[3];

        try {
            post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(courseName, "UTF-8") + "&"
                    + URLEncoder.encode("course_code", "UTF-8") + "=" + URLEncoder.encode(courseCode, "UTF-8") + "&"
                    + URLEncoder.encode("uni_name", "UTF-8") + "=" + URLEncoder.encode(universityName, "UTF-8") + "&";


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Fitting the arguments to the specific URL
     * @param params all paramters neccecary for adding a question to the database
     */

    private void myCourseList(String ... params ){
        String userID = params[1];

    }
    private void allUniversties(String ... params){
    }
    private void myCourseListFromChallenge(String ... params){
        opponentName = params[1];
    }
    /**
     * Fitting the arguments to the specific URL
     * @param params all paramters neccecary for adding a question to the database
     */

    private void myCourseListFromOpponent(String ... params){
        opponentName = params[1];
    }

    private void getSinglePlayerQuiz(String ... params){
               courseID = params[1];
               String limit = params[2];
        try {
            post_data = URLEncoder.encode("c_id", "UTF-8") + "=" + URLEncoder.encode(courseID, "UTF-8") + "&"
                        + URLEncoder.encode("limit", "UTF-8") + "=" + URLEncoder.encode(limit, "UTF-8") + "&";


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void register(String ... params) {
        String username =  params[1];
        String password = params[2];
        String cpassword = params[3];
        try {
            post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                    + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&"
                    + URLEncoder.encode("password_confirm", "UTF-8") + "=" + URLEncoder.encode(cpassword, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void userlogin(String ... params){
        String username = params[1];
        String password = params[2];
        Log.i("username",username);
        Log.i("password",password);
        try{post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

        } catch(Exception e){
            e.printStackTrace();
        }
    }





    @Override
    /**
     * Communicates with the database server. First writes to the server, then reads the output.
     * @param params all paramters neccecary for adding a question to the database
     * @returns the output from the php script
     */

    protected String doInBackground(String... params) {
        type = params[0];
        switch(type){
            case("login"):

            userlogin(params);
            operationURL = IP + "/login.php";
                break;
            case("register"):
                register(params);
                operationURL = IP + "/addusertodb.php";
                break;
            case("add question"):
                addQuestion(params);
                operationURL = IP +"/addquestiontodb.php";
                break;
            case("university list"):
                allUniversties(params);
                operationURL = IP + "/getuniversitiesfromdb.php";
                break;
            case("added course"):
                addCourse(params);
                operationURL = IP + "/addcoursetodb.php";
                break;
            case("all courses"):
                myCourseList(params);
                operationURL = IP + "getcoursesfromdb.php";
                break;
            case("my courses, from opponent"):
                myCourseListFromOpponent(params);
                operationURL = IP + "/getcoursesfromdb.php";
                break;
            case("my courses, from challenge"):
                myCourseListFromChallenge(params);
                operationURL= IP + "/getcoursesfromdb.php";
                break;
            case("singlePlayerMode"):
                getSinglePlayerQuiz(params);
                operationURL= IP + "/getquestionsfromdb.php";
                break;
            default:
                operationURL = IP;



        }
        /*
        if(type.equals("add question")) {
            addQuestion(params);
            operationURL = IP +"/addquestiontodb.php";
        }
        else if (type.equals("university list")){
            allUniversties(params);
            operationURL = IP + "/getuniversitiesfromdb.php";

        }
        else if (type.equals("added course")){
            addCourse(params);
            operationURL = IP + "/addcoursetodb.php";
    }
        else if(type.equals("all courses")){
            myCourseList(params);
            operationURL = IP + "getcoursesfromdb.php";

        }
        else if(type.equals("my courses, from opponent")){
            myCourseListFromOpponent(params);
            operationURL = IP + "/getcoursesfromdb.php";

        }
        else if(type.equals("my courses, from challenge")){
            myCourseListFromChallenge(params);
            operationURL= IP + "/getcoursesfromdb.php";
        }
        else if(type.equals("my courses, from add courses")){

        }
        else if(type.equals("singlePlayerMode")){
            getSinglePlayerQuiz(params);
            operationURL= IP + "/getquestionsfromdb.php";
        }
        */
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
        alertDialog.setTitle("Status");
    }

    /**
     * This method post a popup for the user containing info if success or fail.
     * Also starts the next method depending on the type of arguments recieved from one of the Activities.
     *
     * @param result the output from the php script
     */
    @Override
    protected void onPostExecute(String result) {
        if (type.equals("all courses")){
            // send a string containing all the courses in the database and start the Course Activity
            Intent i = new Intent(context,CourseActivity.class);
            ArrayList<String> send = new ArrayList<String>();
            send.add("fromCreateQuestionActivity");
            send.add(result);
            i.putExtra("prevActivity",send );
            context.startActivity(i);

        }
        else if (type.equals("register")){
            if(!(result.equals("User created"))){
                alertDialog.setMessage(result);
                alertDialog.show();
            }
            else{
                Intent i = new Intent(context, LoginActivity.class);
                context.startActivity(i);
            }
        }
        else if(type.equals("login")){
            if(result.equals("Login successful!")){
                Intent i = new Intent(context, MainActivity.class);
                context.startActivity(i);
            }
            else{
               alertDialog.setMessage(result);
                alertDialog.show();
            }

        }
        else if (type.equals("my courses, from opponent")){
            /* send a string containing all the courses in the database
            / and a string containing the information about the opponent
             and start the Course Activity
             */
            Intent i = new Intent(context, CourseActivity.class);
            Log.i("hello","hello");
            Log.i("opponent",opponentName);
            Log.i("result",result);
            ArrayList<String> send = new ArrayList<String>();
            send.add("fromOpponentActivity");
            send.add(opponentName);
            // send the coursestring
            send.add(result);
            i.putExtra("prevActivity", send);
            context.startActivity(i);
        }
        else if (type.equals("my courses, from challenge")){
            Intent i = new Intent(context,CourseActivity.class);
            ArrayList<String> send = new ArrayList<String>();
            send.add("fromChallengeActivity");
            send.add(opponentName);
            send.add(result);
            i.putExtra("prevActivity",send );
            context.startActivity(i);
        }
        else if(type.equals("add question")){
            Intent i = new Intent(context,CreateQuestionActivity.class);
            ArrayList<String> send = new ArrayList<String>();
            send.add("fromQuestionActivity");
            send.add(opponentName);
            send.add(result);
            i.putExtra("prevActivity",send );
            context.startActivity(i);
        }
        else if(type.equals("added course")){
            Intent i = new Intent(context,CreateQuestionActivity.class);
            ArrayList<String> send = new ArrayList<String>();
            send.add("fromAddCourseActivity");
            send.add(courseName);
            send.add(courseCode);
            courseID = result;
            send.add(courseID);
            send.add(universityName);
            Log.i("Background","I do somethong right");
            Log.i("coursename",courseName);
            Log.i("coursecode",courseCode);
           // Log.i("courseID",courseID);
            Log.i("universityname",universityName);
            i.putExtra("prevActivity",send );
            context.startActivity(i);
        }
        else if (type.equals("university list")){

        }
        else if (type.equals("singlePlayerMode")){
            Intent i = new Intent(context,PlayGameActivity.class);
            ArrayList<String> send = new ArrayList<String>();
            send.add("fromSPChallengeActivity");
            send.add(result);
            i.putExtra("prevActivity",send );
            context.startActivity(i);
        }
             // show a popup with the output
            else {
            Log.i("Result", result);
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}