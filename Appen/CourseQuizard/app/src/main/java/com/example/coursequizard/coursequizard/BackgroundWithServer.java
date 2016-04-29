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
    ArrayList<String> passCourseParameters = new ArrayList<String>();
    String operationURL="";
    String IP = "http://www.borrowit.itvt16.student.it.uu.se/Dropbox";
    String post_data = "";
    Context context;
    String type = "";
    String opponentName="";
    String courseName="";
    String courseCode ="";
    String courseID ="";
    String universityName="";
    String userName="";
    String gameID="";
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
        Log.i("question",question);
        Log.i("answer",answer);
        Log.i("alt1",alt1);
        Log.i("alt2",alt2);
        Log.i("alt3",alt3);
        Log.i("cid",cID);
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
        String universityID=params[4];

        try {
            post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(courseName, "UTF-8") + "&"
                    + URLEncoder.encode("course_code", "UTF-8") + "=" + URLEncoder.encode(courseCode, "UTF-8") + "&"
                    + URLEncoder.encode("uni_name", "UTF-8") + "=" + URLEncoder.encode(universityName, "UTF-8") + "&"
                    + URLEncoder.encode("uni_id", "UTF-8") + "=" + URLEncoder.encode(universityID, "UTF-8") + "&"        ;


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
    private void uploadText(String ... params){
        String text ="\"" + params[1] + "\"";
        try{post_data = URLEncoder.encode("text", "UTF-8") + "=" + URLEncoder.encode(text, "UTF-8") + "&";

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void sendResult(String ... params){
        String username = SaveSharedData.getUserName(context);
        String gameID = params[1];
        String score  = params [2];
        try {
            post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                    + URLEncoder.encode("game_id", "UTF-8") + "=" + URLEncoder.encode(gameID, "UTF-8") + "&"
                    + URLEncoder.encode("score", "UTF-8") + "=" + URLEncoder.encode(score, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void friendGame(String ... params){
       String userName = SaveSharedData.getUserName(context);
       String opponentName = params[1];
       String courseID = params[2];
        try {
            post_data = URLEncoder.encode("by_user", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8") + "&"
                    + URLEncoder.encode("to_user", "UTF-8") + "=" + URLEncoder.encode(opponentName, "UTF-8") + "&"
                    + URLEncoder.encode("c_id", "UTF-8") + "=" + URLEncoder.encode(courseID, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void myGames(String ... params){
        String userName = SaveSharedData.getUserName(context);
        try {
            post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void userlogin(String ... params){
        userName = params[1];
        String password = params[2];
        Log.i("username",userName);
        Log.i("password",password);
        try{post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8") + "&"
                + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

        } catch(Exception e){
            e.printStackTrace();
        }
    }
    private void getFriendlist(String ... params){
        userName = SaveSharedData.getUserName(context);
        Log.i("username", userName);
        try{post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8");

        } catch(Exception e){
            e.printStackTrace();
        }

    }
    private void friendRequest(String addFriend){
        userName = SaveSharedData.getUserName(context);
        Log.i("username friend request", userName);
        Log.i("im adding friend:", addFriend);
        try{post_data = URLEncoder.encode("by_user", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8") + "&"
                + URLEncoder.encode("to_user", "UTF-8") + "=" + URLEncoder.encode(addFriend, "UTF-8");

        } catch(Exception e){
            e.printStackTrace();
        }
    }
    private void quizFromMyGames(String ... params){
        String username = SaveSharedData.getUserName(context);
        gameID = params[1];
        try {
            post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                    + URLEncoder.encode("game_id", "UTF-8") + "=" + URLEncoder.encode(gameID, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void updateState(String ... params){
        String username = SaveSharedData.getUserName(context);
        String gameID = params[1];
        try {
            post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                    + URLEncoder.encode("game_id", "UTF-8") + "=" + URLEncoder.encode(gameID, "UTF-8") + "&"
                    + URLEncoder.encode("score", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
        } catch (Exception e) {
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
            case("universitylist"):
                allUniversties(params);
                operationURL = IP + "/getuniversitiesfromdb.php";
                break;
            case("added course"):
                addCourse(params);
                operationURL = IP + "/addcoursetodb.php";
                break;
            case("all courses"):
                Log.i("innantryblock",params[0]);
                myCourseList(params);
                operationURL = IP + "/getcoursesfromdb.php";
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
            case ("get my courses"):
                getMyCourses(params);
                operationURL = IP + "/getmycoursesfromdb.php";
                break;

            case ("swap favorites"):
                swapFavCourses(params);
                operationURL = IP + "/swapfavoritestodb.php";
                break;
            case ("Upload text"):{
                uploadText(params);
                operationURL = IP + "/generatequestions.php";
                break;
            }
            case ("friendPlayerMode"):
                friendGame(params);
                operationURL = IP + "/addgametodb.php";
                break;
            case("sendresult"):
                sendResult(params);
                operationURL = IP + "/updategamestatustodb.php";
                break;
            case("mygames"):
                myGames(params);
                operationURL = IP + "/getmygamesfromdb.php";
                break;
            case("friendlist"):
                getFriendlist();
                operationURL = IP + "/getfriendlistfromdb.php";
                break;
            case "get all courses":{
                operationURL = IP + "/getcoursesfromdb.php";
                break;
            }
            case("friend request"):
                friendRequest(params[1]);
                operationURL = IP + "/addfriendrequesttodb.php";
                break;

            case("quiz from my games"):
                 quizFromMyGames(params);
                Log.i("quizfrommygames","quizfrommygames");
                 operationURL = IP + "/getgamequestionsfromdb.php";
                break;
            case("updatestate"):
                updateState(params);
                operationURL = IP + "/updategamestatustodb.php";
                break;
            default:
                Log.i("nocase","nocase");
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
                Log.i("tryblock",params[0]);
                URL url = new URL(operationURL);
                Log.i("tryblock2",params[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                Log.i("tryblock3",params[0]);
                httpURLConnection.setRequestMethod("POST");
                Log.i("tryblock4",params[0]);
                httpURLConnection.setDoOutput(true);
                Log.i("tryblock5",params[0]);
                httpURLConnection.setDoInput(true);
                Log.i("tryblock6",params[0]);
                Log.i("postdata",post_data);
                Log.i("url",operationURL);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                Log.i("tryblock7",params[0]);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                // the arguments to the URL
                Log.i("innanwrite",params[0]);
                bufferedWriter.write(post_data);

                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                Log.i("efterwrite",params[0]);
                InputStream inputStream = httpURLConnection.getInputStream();
                Log.i("efterinit",params[0]);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                Log.i("efterread",params[0]);
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
        ArrayList<String> send = new ArrayList<String>();
        switch (type) {
            case "all courses":
                Log.i("postexecuteblock","weee");
                // send a string containing all the courses in the database and start the Course Activity
                Intent allcoursesi = new Intent(context, CourseActivity.class);
                send.add("fromCreateQuestionActivity");
                send.add(result);
                allcoursesi.putExtra("prevActivity", send);
                context.startActivity(allcoursesi);

                break;

            case "register":
                if (!(result.equals("User created"))) {
                    alertDialog.setMessage(result);
                    alertDialog.show();
                } else {
                    Intent registeri = new Intent(context, LoginActivity.class);
                    context.startActivity(registeri);
                }
                break;
            case "login":
                if (result.equals("Login successful!")) {
                    Intent logini = new Intent(context, MainActivity.class);
                    SaveSharedData.setUserName(context,userName);
                    context.startActivity(logini);
                } else {
                    alertDialog.setMessage(result);
                    alertDialog.show();
                }

                break;
            case "my courses, from opponent":
            /* send a string containing all the courses in the database
            / and a string containing the information about the opponent
             and start the Course Activity
             */
                Intent mycoursesfromopponenti = new Intent(context, CourseActivity.class);
               // Log.i("hello", "hello");
               // Log.i("opponent", opponentName);
              //  Log.i("result", result);
                send.add("fromOpponentActivity");
                send.add(opponentName);
                // send the coursestring
                send.add(result);
                mycoursesfromopponenti.putExtra("prevActivity", send);
                context.startActivity(mycoursesfromopponenti);
                break;

            case "my courses, from challenge":
                Intent mycoursesfromchallengei = new Intent(context, CourseActivity.class);
                send.add("fromChallengeActivity");
                send.add(opponentName);
                send.add(result);
                mycoursesfromchallengei.putExtra("prevActivity", send);
                context.startActivity(mycoursesfromchallengei);
                break;

            case "add question":
              /*Intent addquestioni = new Intent(context, CreateQuestionActivity.class);
                send.add("fromQuestionActivity");
                send.add(opponentName);
                send.add(result);
                addquestioni.putExtra("prevActivity", send);
                context.startActivity(addquestioni);
               */ break;

            case "added course":
                Intent addedcoursei = new Intent(context, CreateQuestionActivity.class);
                send.add("fromAddCourseActivity");
                send.add(courseName);
                send.add(courseCode);
                courseID = result;
                send.add(courseID);
                send.add(universityName);
                Log.i("Background", "I do somethong right");
                Log.i("coursename", courseName);
                Log.i("coursecode", courseCode);
                // Log.i("courseID",courseID);
                Log.i("universityname", universityName);
                addedcoursei.putExtra("prevActivity", send);
                context.startActivity(addedcoursei);
                break;

            case "singlePlayerMode":
                Intent singleplayermodei = new Intent(context, PlayGameActivity.class);
                send.add("fromSPChallengeActivity");
                send.add(result);
                singleplayermodei.putExtra("prevActivity", send);
                context.startActivity(singleplayermodei);
                break;

            case "get my courses":
                Intent getmycoursesi = new Intent(context, MyCoursesActivity.class);
                Log.i("result", result);
                send.add("fromMyProfileActivity");
                send.add(result);
                getmycoursesi.putExtra("prevActivity", send);
                context.startActivity(getmycoursesi);
                break;


            case "swap favorites":
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

                Log.i("swap res", result);
                // alertDialog.setMessage(result);
                //Toast.show();
                break;

            case ("universitylist"):
                Intent universitylisti = new Intent(context, AddCourseActivity.class);
                send.add("fromCourseActivity");
                send.add(result);
                universitylisti.putExtra("prevActivity", send);
                context.startActivity(universitylisti);
            // show a popup with the output
                break;
            case ("Upload text"):
             Intent uploadtexti = new Intent(context,CreateQuestionActivity.class);
                Log.i("Second case","case");
                send.add("fromUploadTextActivity");
                send.add(result);
                uploadtexti.putExtra("prevActivity",send );
                context.startActivity(uploadtexti);
                break;

            case ("friendPlayerMode"):
                Intent friendplayermodei = new Intent(context,MainActivity.class);
                send.add("fromFPChallengeActivity");
                send.add(result);
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                friendplayermodei.putExtra("prevActivity", send);
                context.startActivity(friendplayermodei);

                /*
                Intent friendplayermodei = new Intent(context,PlayGameActivity.class);
                send.add("fromFPChallengeActivity");
                send.add(result);
                friendplayermodei.putExtra("prevActivity", send);
                context.startActivity(friendplayermodei);
                */
                break;
            case("mygames"):
                Intent mygamesi = new Intent(context,MyGamesActivity.class);
                send.add("fromMainActivity");
                send.add(result);
                Log.i("mygamesunparsed",result);
                mygamesi.putExtra("prevActivity", send);
                context.startActivity(mygamesi);
                break;
            case("friendlist"):
                if(result == null){
                    result = "Nofriends";
                }

                Log.i("result", result);
                ArrayList<String> friend = new ArrayList<String>();
                String[] friends = result.split("%U%");
                int length = friends.length; // length of coded friendlist string[]
                Log.i("friendlistlength", String.valueOf(length));
                for(int i = 0; i < length; i++){
                    friend.add(friends[i]); //add all friends in friendlist
                }
                SaveSharedData.setFriendList(context, friend);
                Log.i("friendlist done!", "hej");
                break;
            case "get all courses":{
                Log.i("result", result);
                Intent allCi = new Intent(context, CourseActivity.class);
                send.add("fromMyProfileActivity");
                send.add(result);
                allCi.putExtra("prevActivity", send);
                context.startActivity(allCi);
                break;
            }
            case("friend request"):
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                break;
            case("quiz from my games"):
                Intent qfmgi = new Intent(context, PlayGameActivity.class);
                send.add("fromMyGamesActivity");
                send.add(result);
                send.add(gameID);
                qfmgi.putExtra("prevActivity", send);
                context.startActivity(qfmgi);
                break;
            case("sendresult"):
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                break;
            case("updatestate"):

                break;
            default:
                // Log.i("Result", result);
                //alertDialog.setMessage(result);
                Log.i("Error","default");
                alertDialog.show();
                break;
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}