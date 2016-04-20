package com.example.coursequizard.coursequizard;





import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

//import CQ.*;

/**
 *  This class is the create question Activity
 *  from: A parameter
 *  courseiD: the course ID, important when generating a question
 *  genlistcounr: a counter for all the automatic question that has to be edited
 */
public class CreateQuestionActivity extends AppCompatActivity {
    public String from = "Empty";
    public String courseID ="";
    public int genlistcount =0  ;
    //public LinkedList<Question> questionList = new LinkedList<Question>();
  /*  public void setupPage(Question questionItems){
        EditText editAnswerButton = (EditText) findViewById(R.id.editAnswerButton);
        EditText editAlt1Button = (EditText) findViewById(R.id.editAlt1Button);
        EditText editAlt2Button = (EditText) findViewById(R.id.editAlt2Button);
        EditText editAlt3Button = (EditText) findViewById(R.id.editAlt3Button);
        EditText questionTextView = (EditText) findViewById(R.id.questionTextView);
        questionTextView.setText(questionItems.getQuestion());
        editAnswerButton.setText(questionItems.getAnswer());
        editAlt1Button.setText(questionItems.getAlt1());
        editAlt2Button.setText(questionItems.getAlt2());
        editAlt3Button.setText(questionItems.getAlt3());

    }
    */

    /**
     * determine what the previous activity was and execute from there
     */
   public void fromActivity(){
        TextView courseTextView = (TextView) findViewById(R.id.courseCreateQuestionTextView);
       ArrayList<String>  message = new ArrayList<String>();
        message = getIntent().getExtras().getStringArrayList("prevActivity");
       // if we have chosen the course for the question  we have to get the course ID
        if (message.get(0).equals("fromCourseActivity")){
            courseTextView.setText(message.get(1));
            courseID =message.get(2);
        }

    }

    /**
     *  When user is going to enter course, start backgrond service and get courselist from server
     * @param view edit course button
     */
    public void toCourseActivity(View view){
        String userID = "1337";
        //Intent i = new Intent(getApplicationContext(),CourseActivity.class);
        String type = "my courses";
        BackgroundWithServer bgws = new BackgroundWithServer(this);

        bgws.execute(type,userID);


       // String[] send = new String[]{"fromCreateQuestionActivity","placeholder"};
        //i.putExtra("prevActivity",send );
       // startActivity(i);
    }

    /**
     *  read all the parameters from the view and use them as the arguments for the php script to insert question in database
     * @param view add questionbutton
     */
    public void addQuestion(View view){
        EditText editAnswerButton = (EditText) findViewById(R.id.editAnswerButton);
        EditText editAlt1Button = (EditText) findViewById(R.id.editAlt1Button);
        EditText editAlt2Button = (EditText) findViewById(R.id.editAlt2Button);
        EditText editAlt3Button = (EditText) findViewById(R.id.editAlt3Button);
        EditText questionTextView = (EditText) findViewById(R.id.questionTextView);
        String question = questionTextView.getText().toString();
        String answer = editAnswerButton.getText().toString();
        String alt1 =        editAlt1Button.getText().toString();
        String alt2 = editAlt2Button.getText().toString();
        String alt3 = editAlt3Button.getText().toString();
        String type = "add question";

        BackgroundWithServer bgws = new BackgroundWithServer(this);

        bgws.execute(type,question,answer,alt1,alt2,alt3, courseID);
        //CQ cq = new CQ();
       // cq.connect();
        /*cq.sendQuestionToDB(questionTextView.getText().toString(),
                editAnswerButton.getText().toString(),
                editAlt1Button.getText().toString(),
                editAlt2Button.getText().toString(),
                editAlt3Button.getText().toString());
         */
       // Log.i("YO","LO");
        /*cq.sendQuestionToDB("a","b","c","d","e"); */
        /*
        if (from == "gfdgdhtutr"){
            genlistcount++;
            if (questionList.size() > genlistcount){
                setupPage(questionList.get(genlistcount));
            }
        }
        */


        //cq.disconnect();
    }

    /**
     * reset all the textfields
     * @param view the createquestionview
     */
    public void resetView(View view) {
        EditText editAnswerButton = (EditText) findViewById(R.id.editAnswerButton);
        EditText editAlt1Button = (EditText) findViewById(R.id.editAlt1Button);
        EditText editAlt2Button = (EditText) findViewById(R.id.editAlt2Button);
        EditText editAlt3Button = (EditText) findViewById(R.id.editAlt3Button);
        EditText questionTextView = (EditText) findViewById(R.id.questionTextView);
        questionTextView.setText("");
        editAnswerButton.setText("");
        editAlt1Button.setText("");
        editAlt2Button.setText("");
        editAlt3Button.setText("");

    }

    /**
     * go to the main menu
     */
    public void toMainActivity(){
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

    /**
     * edit the autogenerated questions
     * @param questionTypeString
     */
    public void generatedQuestionsMode(String questionTypeString) {
       // CQParser questionParser = new CQParser();

        //questionList = questionParser.generate(questionTypeString);
       // setupPage(questionList.getFirst());

    }

    /**
     * Tell what the previous activity was
     */
    public void fromPrevActivity() {
        String[] prevActivityArray = new String[2];
        prevActivityArray = getIntent().getExtras().getStringArray("prevActivity");
        String questionTypeString = new String();
        if (prevActivityArray[0] == "fromUploadTextActivity") {

            questionTypeString = prevActivityArray[1];
            generatedQuestionsMode(questionTypeString);
            from = prevActivityArray[0];
        }
        /*else {
            //ownQuestionsMode();
        }
        */
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        genlistcount=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);

        fromActivity();

    }
}
