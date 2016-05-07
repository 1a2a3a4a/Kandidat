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
import android.widget.Toast;

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
    public LinkedList<Question> genQuestionList = new LinkedList<Question>();
    public Question currentQuestion = new Question("","","","","");
    public int toGenIndex =0;
    public int fromGenIndex = 0;
    public int localGenIndex =0;

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
       View skipGenButton = findViewById(R.id.skipGenButton);
       skipGenButton.setVisibility(View.INVISIBLE);
       ArrayList<String>  message = new ArrayList<String>();
        message = getIntent().getExtras().getStringArrayList("prevActivity");
       // if we have chosen the course for the question  we have to get the course ID

        if (message.get(0).equals("fromCourseActivity")){
            currentQuestion =SaveSharedData.getCurrentQuestion(CreateQuestionActivity.this);
            courseTextView.setText(message.get(1));
            courseID =message.get(3);
            if (haveGenQuestions()){
                skipGenButton.setVisibility(View.VISIBLE);
            }

        }
       else if (message.get(0).equals("fromAddCourseActivity")) {
            currentQuestion =SaveSharedData.getCurrentQuestion(CreateQuestionActivity.this);
            courseTextView.setText(message.get(1));
            courseID = message.get(3);
            if (haveGenQuestions()){
                skipGenButton.setVisibility(View.VISIBLE);
            }
       }
       else if(message.get(0).equals("fromUploadTextActivity")){
            skipGenButton.setVisibility(View.VISIBLE);
            CQParser parser = new CQParser();
            Log.i("toparser",message.get(1));
               genQuestionList = parser.generateQuestions(message.get(1));
            Log.i("question",genQuestionList.get(0).getQuestion());
            SaveSharedData.setGenQuestions(CreateQuestionActivity.this,genQuestionList);
            Log.i("question2",genQuestionList.get(0).getQuestion());
            SaveSharedData.setFromGenIndex(CreateQuestionActivity.this,0);
            SaveSharedData.setToGenIndex(CreateQuestionActivity.this,genQuestionList.size());
            LinkedList<Question> temp = SaveSharedData.getGenQuestions(CreateQuestionActivity.this,0,0);
            Log.i("question3",genQuestionList.get(0).getQuestion());
            Log.i("tempsave",temp.get(0).getQuestion());
            SaveSharedData.setToGenIndex(CreateQuestionActivity.this,genQuestionList.size());
              toGenIndex = genQuestionList.size();
              setupGenView(0);
            }
        }

    /**
     *  When user is going to enter course, start backgrond service and get courselist from server
     * @param view edit course button
     */
    public void toCourseActivity(View view){
        String userID = "1337";
        //Intent i = new Intent(getApplicationContext(),CourseActivity.class);
        String type = "all courses";
        Log.i("addquestion","add");
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
        currentQuestion.setQuestion(question);
        currentQuestion.setAnswer(answer);
        currentQuestion.setAlt1(alt1);
        currentQuestion.setAlt2(alt2);
        currentQuestion.setAlt3(alt3);
        SaveSharedData.setCurrentQuestion(CreateQuestionActivity.this,currentQuestion);
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
        Log.i("addquestion","add");
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
        if (anyStringEmpty(question,answer,alt1,alt2,alt3,courseID)){
            String message ="";
            if (question.equals("")){
                message+="question field ";

            }
            if (answer.equals("")){
                message+="answer field ";

            }
            if (alt1.equals("")){
                message+="alt1 field ";

            }
            if (alt2.equals("")){
                message+="alt2 field ";

            }
            if (alt3.equals("")){
                message+="alt3 field ";

            }
            if (courseID.equals("")){
                message +="course field ";

            }
            if (message.length() > 17){
                message +="are empty";
            }
            if (message.length() <= 17){
                message += "is empty";
            }
            Toast.makeText(CreateQuestionActivity.this, message, Toast.LENGTH_SHORT).show();
        }
        else {
            fromGenIndex++;
            localGenIndex++;
            SaveSharedData.setFromGenIndex(CreateQuestionActivity.this, fromGenIndex);
            LinkedList<Question> oneQuestion = SaveSharedData.getGenQuestions(CreateQuestionActivity.this, localGenIndex, localGenIndex);
            SaveSharedData.setCurrentQuestion(CreateQuestionActivity.this, oneQuestion.get(0));
            BackgroundWithServer bgws = new BackgroundWithServer(this);

            bgws.execute(type, question, answer, alt1, alt2, alt3, courseID);

            setupCurrentQuestionView();
        }
        //setupGenView(localGenIndex);
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
    private boolean anyStringEmpty(String ... params){
        int length =  params.length;
        for (int i =0; i< length; i++){
          if (params[i].equals("")){
              return true;
          };
        }
        return false;
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
    public void setupCurrentQuestionView(){
        EditText editAnswerButton = (EditText) findViewById(R.id.editAnswerButton);
        EditText editAlt1Button = (EditText) findViewById(R.id.editAlt1Button);
        EditText editAlt2Button = (EditText) findViewById(R.id.editAlt2Button);
        EditText editAlt3Button = (EditText) findViewById(R.id.editAlt3Button);
        EditText questionTextView = (EditText) findViewById(R.id.questionTextView);
        currentQuestion = SaveSharedData.getCurrentQuestion(CreateQuestionActivity.this);
        if(currentQuestion.getQuestion().equals("")){
            View skipGenButton = findViewById(R.id.skipGenButton);
            skipGenButton.setVisibility(View.INVISIBLE);
        }
        questionTextView.setText(currentQuestion.getQuestion());
        editAnswerButton.setText(currentQuestion.getAnswer());
        editAlt1Button.setText(currentQuestion.getAlt1());
        editAlt2Button.setText(currentQuestion.getAlt2());
        editAlt3Button.setText(currentQuestion.getAlt3());
    }
    public void setupGenView(int i){
        EditText editAnswerButton = (EditText) findViewById(R.id.editAnswerButton);
        EditText editAlt1Button = (EditText) findViewById(R.id.editAlt1Button);
        EditText editAlt2Button = (EditText) findViewById(R.id.editAlt2Button);
        EditText editAlt3Button = (EditText) findViewById(R.id.editAlt3Button);
        EditText questionTextView = (EditText) findViewById(R.id.questionTextView);
        Question oneQuestion = genQuestionList.get(i);
        questionTextView.setText(oneQuestion.getQuestion());
        editAnswerButton.setText(oneQuestion.getAnswer());
        editAlt1Button.setText(oneQuestion.getAlt1());
        editAlt2Button.setText(oneQuestion.getAlt2());
        editAlt3Button.setText(oneQuestion.getAlt3());
    }

    /**
     * go to the main menu
     */
    public void toMainActivity(View view){
        SaveSharedData.clearGenQuestions(CreateQuestionActivity.this);
        SaveSharedData.setFromGenIndex(CreateQuestionActivity.this,0);
        SaveSharedData.setToGenIndex(CreateQuestionActivity.this,0);
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);


    }
    public void skipGenQuestion(View view){
        fromGenIndex++;
        localGenIndex++;
        SaveSharedData.setFromGenIndex(CreateQuestionActivity.this,fromGenIndex);
        LinkedList<Question> oneQuestion = SaveSharedData.getGenQuestions(CreateQuestionActivity.this,localGenIndex,localGenIndex);
        SaveSharedData.setCurrentQuestion(CreateQuestionActivity.this,oneQuestion.get(0));
        setupCurrentQuestionView();
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
     public boolean haveGenQuestions() {
         Log.i("bool","bool");
         LinkedList<Question> have = new LinkedList<Question>();
         have = SaveSharedData.getGenQuestions(CreateQuestionActivity.this, 0, 0);
         Log.i("have",have.get(0).getQuestion());
         if (have.get(0).getQuestion().equals("")) {
             Log.i("bool","false");
             return false;
         }
         Log.i("bool","true");
         return true;

     }
    @Override
    public void onBackPressed() {
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        genlistcount=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);

        setupCurrentQuestionView();
        Log.i("oncreate","oncreate");
        if (haveGenQuestions()){
            Log.i("true","true");


            toGenIndex = SaveSharedData.getToGenIndex(CreateQuestionActivity.this);
            Log.i("prefromgenindex",String.valueOf(fromGenIndex));
            fromGenIndex =  SaveSharedData.getFromGenIndex(CreateQuestionActivity.this);
            Log.i("postfromgenindex",String.valueOf(fromGenIndex));
            genQuestionList= SaveSharedData.getGenQuestions(CreateQuestionActivity.this,fromGenIndex,toGenIndex);
            Log.i("Getting it ", genQuestionList.get(0).getQuestion());
            //setupGenView(0);
        }
        fromActivity();




    }
}
