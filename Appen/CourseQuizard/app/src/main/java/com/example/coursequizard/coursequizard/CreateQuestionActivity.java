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
    public void addQuestion(View view){
        EditText editAnswerButton = (EditText) findViewById(R.id.editAnswerButton);
        EditText editAlt1Button = (EditText) findViewById(R.id.editAlt1Button);
        EditText editAlt2Button = (EditText) findViewById(R.id.editAlt2Button);
        EditText editAlt3Button = (EditText) findViewById(R.id.editAlt3Button);
        EditText questionTextView = (EditText) findViewById(R.id.questionTextView);
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

    }
}
