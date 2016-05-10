package com.example.coursequizard.coursequizard;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class PlayGameActivity extends AppCompatActivity {
    AlertDialog.Builder alertDialog;
    public int questionIndex =0;
    public String wrong = "";
    public String gameID ="";
    public int wrongCount = 0;
    public int numberOfQuestions = 5;
    public ArrayList<String> userAnswers= new ArrayList<String>();
    public ArrayList<String> correctAnswers = new ArrayList<String>();
    private Toolbar toolbar;
    public boolean haveVotedUp = false;
    public boolean haveVotedDown = false;



    LinkedList<Question> questionList = new LinkedList<Question>();

      public void fromActivity(){
          CQParser questionParser = new CQParser();
          ArrayList<String> message = new ArrayList<String>();
          message = getIntent().getExtras().getStringArrayList("prevActivity");
          if (message.get(0).equals("fromSPChallengeActivity")){

             // Log.i("to parse",message.get(1) );
              questionList = questionParser.toQList(message.get(1));

              }

          else if(message.get(0).equals("fromRPChallengeActivity")){

          }
          else if(message.get(0).equals("fromFPChallengeActivity")){
              questionList = questionParser.toQList(message.get(1));
          }
          else if(message.get(0).equals("fromMyGamesActivity")){
              questionList = questionParser.toQList(message.get(1));
              gameID = message.get(2);
          }
          numberOfQuestions = questionList.size();

      }
        public ArrayList<String>  randomAlternativeList(ArrayList<String> alternativeList){
            ArrayList<String> randomizedList = new ArrayList<String>();
            while ( alternativeList.size() > 0) {
            //    Log.i("looop","looopar");
                int randomIndex = new Random().nextInt(alternativeList.size());
                randomizedList.add(alternativeList.get(randomIndex));
                alternativeList.remove(randomIndex);
            }
            return randomizedList;
        }
    public void quizPrinter(){
        TextView questionTextView = (TextView) findViewById(R.id.questionTextView);
        Button   alt1Button       =  (Button) findViewById(R.id.alt1Button);
        Button   alt2Button       = (Button) findViewById(R.id.alt2Button);
        Button   alt3Button       = (Button) findViewById(R.id.alt3Button);
        Button   alt4Button       = (Button) findViewById(R.id.alt4Button);
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
       // Log.i("questiomstring",questionString);
      //  Log.i("answer",answerString);
       // Log.i("alt1",alt1String);
        //Log.i("alt2",alt2String);
      //  Log.i("alt3",alt3String);
        questionTextView.setText(questionString);
        alternativeList = randomAlternativeList(alternativeList);

        alt1Button.setText(alternativeList.get(0));
        alt2Button.setText(alternativeList.get(1));
        alt3Button.setText(alternativeList.get(2));
        alt4Button.setText(alternativeList.get(3));
    }
    public void answer(View view ){
        Button   alt1Button       =  (Button) findViewById(R.id.alt1Button);
        Button   alt2Button       = (Button) findViewById(R.id.alt2Button);
        Button   alt3Button       = (Button) findViewById(R.id.alt3Button);
        Button   alt4Button       = (Button) findViewById(R.id.alt4Button);
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
        Button   alt1Button       =  (Button) findViewById(R.id.alt1Button);
        Button   alt2Button       = (Button) findViewById(R.id.alt2Button);
        Button   alt3Button       = (Button) findViewById(R.id.alt3Button);
        Button   alt4Button       = (Button) findViewById(R.id.alt4Button);
        String alt1buttonstring = alt1Button.getText().toString();
      //  Log.i("alt1string","innan");
       // Log.i("alt1string",alt1buttonstring);
      //  Log.i("alt1string","efter");


        alt1Button.setBackgroundColor(Color.BLUE);

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
    public void donothing(){
        TextView questionTextView = (TextView) findViewById(R.id.questionTextView);
        Button   alt1Button       =  (Button) findViewById(R.id.alt1Button);
        Button   alt2Button       = (Button) findViewById(R.id.alt2Button);
        Button   alt3Button       = (Button) findViewById(R.id.alt3Button);
        Button   alt4Button       = (Button) findViewById(R.id.alt4Button);
    }
    public void checkAnswer(String userAnswer,int alt) {
        Button userButton = (Button) findViewById(alt);
        String answerString = questionList.get(questionIndex).getAnswer();
        String questionString = questionList.get(questionIndex).getQuestion();
        userAnswers.add(userAnswer);
        correctAnswers.add(answerString);
        //doGreen(answerString);
        if (!(userAnswer.equals(answerString))) {
           //doRed(userButton);
            wrongCount++;
            wrong += "Question "  + String.valueOf(questionIndex + 1)  + ":\n"
                    + questionString + "\n"
                    + "Your answer: " + userAnswer + "\n"
                    + "Correct answer: " + answerString + "\n";
        }


        nextQuestion();
    }
    public void unclick(){
        Button   alt1Button       =  (Button) findViewById(R.id.alt1Button);
        Button   alt2Button       = (Button) findViewById(R.id.alt2Button);
        Button   alt3Button       = (Button) findViewById(R.id.alt3Button);
        Button   alt4Button       = (Button) findViewById(R.id.alt4Button);
        ImageButton   voteUpButton     =  (ImageButton) findViewById(R.id.voteUpButton);
        ImageButton   voteDownButton     =  (ImageButton) findViewById(R.id.voteDownButton);

        alt1Button.setEnabled(false);
        alt2Button.setEnabled(false);
        alt3Button.setEnabled(false);
        alt4Button.setEnabled(false);
        voteUpButton.setEnabled(false);
        voteDownButton.setEnabled(false);
    }
    public void sendResults(){
        String type = "sendresult";
        String score = String.valueOf(numberOfQuestions - wrongCount);
        BackgroundWithServer bgws =new BackgroundWithServer(this);
        bgws.execute(type,gameID,score,userAnswers.get(0),userAnswers.get(1),userAnswers.get(2),userAnswers.get(3),userAnswers.get(4));
    }
    public void nextQuestion(){
        ImageButton down = (ImageButton)findViewById(R.id.voteDownButton);
        ImageButton up = (ImageButton)findViewById(R.id.voteUpButton);
        String add = "";
        rateQuestion();
        haveVotedUp = false;
        haveVotedDown = false;
        up.setBackgroundColor(Color.WHITE);
        down.setBackgroundColor(Color.WHITE);

        /*
        TextView questionTextView = (TextView) findViewById(R.id.textView);
        Button   alt1Button       =  (Button) findViewById(R.id.alt1Button);
        Button   alt2Button       = (Button) findViewById(R.id.alt2Button);
        Button   alt3Button       = (Button) findViewById(R.id.alt3Button);
        Button   alt4Button       = (Button) findViewById(R.id.alt4Button);
        */
        questionIndex++;

        if (questionIndex >= numberOfQuestions){
            if (!(gameID.equals(""))) {
                sendResults();
            }
            if (wrongCount == 0){
                add = "You answered everything correctly :D\n";
            }
            if (wrongCount == 1){
                add = String.valueOf(wrongCount) + " answer was wrong\n";

            }
            if (wrongCount > 1){
                add  = String.valueOf(wrongCount) + " answers was wrong\n";

            }
            alertDialog.setMessage(" You made " + String.valueOf(numberOfQuestions- wrongCount)  + " of "  +String.valueOf(numberOfQuestions)  + " questions \n"  + add  + wrong );
            alertDialog.show();
            View gameDoneButton = findViewById(R.id.gameDoneButton);
            gameDoneButton.setVisibility(View.VISIBLE);
            unclick();
        }
        else {
            /*
            alt1Button.setBackgroundColor(Color.GRAY);
            alt2Button.setBackgroundColor(Color.GRAY);
            alt3Button.setBackgroundColor(Color.GRAY);
            alt4Button.setBackgroundColor(Color.GRAY);
            */

            quizPrinter();

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        /*toolbar = (Toolbar) findViewById(R.id.toolbarplaygame);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        // getSupportActionBar().setDisplayShowTitleEnabled(false);

        */
        View gameDoneButton = findViewById(R.id.gameDoneButton);
        gameDoneButton.setVisibility(View.GONE);
        alertDialog = new AlertDialog.Builder(PlayGameActivity.this);
        alertDialog.setTitle("Your result");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do things
            }
        });
        fromActivity();
        quizPrinter();

    }
    @Override
    public void onBackPressed() {
    }
    public void toMainActivity(View view){
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        ArrayList<String> send = new ArrayList<String>();
        send.add("fromPlayGameActivity");
        i.putExtra("prevActivity",send );
        startActivity(i);

    }
    public void upvote(View view){
        haveVotedDown=false;
        ImageButton down = (ImageButton)findViewById(R.id.voteDownButton);
        ImageButton up = (ImageButton)findViewById(R.id.voteUpButton);
        down.setBackgroundColor(Color.WHITE);
        if (!(haveVotedUp)) {
           // rateQuestion("1");
            haveVotedUp=true;
            up.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
            Toast.makeText(PlayGameActivity.this,"Upvoted", Toast.LENGTH_SHORT).show();


        }
        else{
            haveVotedUp=false;
            up.setBackgroundColor(Color.WHITE);
            Toast.makeText(PlayGameActivity.this,"Vote undone", Toast.LENGTH_SHORT).show();
        }
    }
    public void downvote(View view){
        haveVotedUp=false;
        ImageButton down = (ImageButton)findViewById(R.id.voteDownButton);
        ImageButton up = (ImageButton)findViewById(R.id.voteUpButton);
        up.setBackgroundColor(Color.WHITE);
        if (!(haveVotedDown)) {
            //rateQuestion("-1");
            haveVotedDown=true;
            down.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
            Toast.makeText(PlayGameActivity.this,"Downvoted", Toast.LENGTH_SHORT).show();

        }
        else{
            haveVotedDown=false;
            down.setBackgroundColor(Color.WHITE);
            Toast.makeText(PlayGameActivity.this,"Vote undone", Toast.LENGTH_SHORT).show();
        }
    }
    public void rateQuestion(){
            String type = "Rating";
            String vote ="0";
             String qID = String.valueOf(questionList.get(questionIndex).getQ_id());
            if (haveVotedDown){
                vote = "-1";
                BackgroundWithServer bgws = new BackgroundWithServer(this);
                bgws.execute(type, qID, vote);
            }
        else if(haveVotedUp){
                vote ="1";
                BackgroundWithServer bgws = new BackgroundWithServer(this);
                bgws.execute(type, qID, vote);
            }



    }
}
