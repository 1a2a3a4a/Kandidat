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
import java.util.LinkedList;

//import CQ.*;

public class CreateQuestionActivity extends AppCompatActivity {
    public String from = "Empty";
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
    public void fromActivity(){
        EditText courseTextView = (EditText) findViewById(R.id.courseCreateQuestionTextView);
        String [] message = new String[2];
        message = getIntent().getExtras().getStringArray("prevActivity");
        if (message[0].equals("fromCourseActivity")){
            courseTextView.setText(message[1]);
        }

    }
    public void toCourseActivity(View view){
        Intent i = new Intent(getApplicationContext(),CourseActivity.class);
        String[] send = new String[]{"fromCreateQuestionActivity","placeholder"};
        i.putExtra("prevActivity",send );
        startActivity(i);
    }
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

        bgws.execute(type,question,answer,alt1,alt2,alt3);
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
    public void toMainActivity(){
        Intent i = new Intent(getApplicationContext(),UploadTextActivity.class);
        startActivity(i);
    }
    public void generatedQuestionsMode(String questionTypeString) {
       // CQParser questionParser = new CQParser();

        //questionList = questionParser.generate(questionTypeString);
       // setupPage(questionList.getFirst());

    }
    public String getPrevActiivity(){
        return "hello";
    }
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
    public void sendQuestion(String question){


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        genlistcount=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
        fromActivity();

    }
}
