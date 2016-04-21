package com.example.coursequizard.coursequizard;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class PlayGameActivity extends AppCompatActivity {
    AlertDialog alertDialog;
    public int questionIndex =0;
    public int numberOfQuestions = 5;
    public TextView questionTextView = (TextView) findViewById(R.id.questionTextView);
    public Button   alt1Button       = (Button) findViewById(R.id.alt1Button);
    public Button   alt2Button       = (Button) findViewById(R.id.alt2Button);
    public Button   alt3Button       = (Button) findViewById(R.id.alt3Button);
    public Button   alt4Button       = (Button) findViewById(R.id.alt4Button);
    LinkedList<Question> questionList = new LinkedList<Question>();

      public void fromActivity(){
          ArrayList<String> message = new ArrayList<String>();
          message = getIntent().getExtras().getStringArrayList("prevActivity");
          if (message.get(0).equals("fromSPChallengeActivity")){
              QuestionParser questionParser = new QuestionParser();
              questionList = questionParser.QList(message.get(1));

              }

          else if(message.get(0).equals("fromRPChallengeActivity")){

          }
          else if(message.get(0).equals("fromFPChallengeActivity")){

          }
          numberOfQuestions = questionList.size();
      }
        public ArrayList<String>  randomAlternativeList(ArrayList<String> alternativeList){
            ArrayList<String> randomizedList = new ArrayList<String>();
            while ( alternativeList.size() > 0) {
                int randomIndex = new Random().nextInt(alternativeList.size());
                randomizedList.add(alternativeList.get(randomIndex));
                alternativeList.remove(randomIndex);
            }
            return randomizedList;
        }
    public void quizPrinter(){
        String questionString = questionList.get(questionIndex).getQuestion();
        String answerString   = questionList.get(questionIndex).getAnswer();
        String alt1String     = questionList.get(questionIndex).getAlt1();
        String alt2String     = questionList.get(questionIndex).getAlt2();
        String alt3String     = questionList.get(questionIndex).getAlt3();
        ArrayList<String>  alternativeList = new ArrayList<String>();
        alternativeList.add(answerString);
        alternativeList.add(alt1String);
        alternativeList.add(alt2String);
        alternativeList.add(alt3String);
        alternativeList = randomAlternativeList(alternativeList);
        questionTextView.setText(questionString);
        alt1Button.setText(alternativeList.get(0));
        alt2Button.setText(alternativeList.get(1));
        alt3Button.setText(alternativeList.get(2));
        alt4Button.setText(alternativeList.get(3));
    }
    public void answer(View view ){
        String userAnswer = "";
        int alt = view.getId();
        switch(view.getId())
        {
            case R.id.alt1Button:
                userAnswer = alt1Button.getText().toString();


// handle button A click;
                break;
            case R.id.alt2Button:
                userAnswer = alt2Button.getText().toString();
// handle button B click;
                break;
            case R.id.alt3Button:
                userAnswer = alt3Button.getText().toString();

                break;
            case R.id.alt4Button:
                userAnswer = alt4Button.getText().toString();
                break;
            default:
                throw new RuntimeException("Unknow button ID");
        }
        checkAnswer(userAnswer, alt);
    }

    public void doGreen(String answerString){

        if (alt1Button.getText().toString().equals(answerString)){
            alt1Button.setBackgroundColor(Color.GREEN);


        }
        else if (alt2Button.getText().toString().equals(answerString)){
            alt2Button.setBackgroundColor(Color.GREEN);
        }
        else if (alt3Button.getText().toString().equals(answerString)){
            alt3Button.setBackgroundColor(Color.GREEN);

        }
        else if (alt4Button.getText().toString().equals(answerString)){
            alt4Button.setBackgroundColor(Color.GREEN);

        }
    }
    public void doRed(Button userButton){

        userButton.setBackgroundColor(Color.RED);

    }

    public void checkAnswer(String userAnswer,int alt) {
        Button userButton = (Button) findViewById(alt);
        String answerString = questionList.get(questionIndex).getAnswer();
        doGreen(answerString);
        if (!(userAnswer.equals(userAnswer))) {
            doRed(userButton);
        }
        try {
            Thread.sleep(3000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        nextQuestion();
    }
    public void nextQuestion(){
        questionIndex++;
        if (questionIndex >= numberOfQuestions){
            alertDialog.setMessage("Quiz is done :D");
            alertDialog.show();
        }
        else {
            alt1Button.setBackgroundColor(Color.GRAY);
            alt2Button.setBackgroundColor(Color.GRAY);
            alt3Button.setBackgroundColor(Color.GRAY);
            alt4Button.setBackgroundColor(Color.GRAY);
            quizPrinter();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        alertDialog = new AlertDialog.Builder(getApplicationContext()).create();
        alertDialog.setTitle("Your result");
        alt1Button.setBackgroundColor(Color.GRAY);
        alt2Button.setBackgroundColor(Color.GRAY);
        alt3Button.setBackgroundColor(Color.GRAY);
        alt4Button.setBackgroundColor(Color.GRAY);
        alt1Button.setTransformationMethod(null);
        alt2Button.setTransformationMethod(null);
        alt3Button.setTransformationMethod(null);
        alt4Button.setTransformationMethod(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        fromActivity();
        quizPrinter();

    }
}
