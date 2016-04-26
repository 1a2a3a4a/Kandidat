package com.example.coursequizard.coursequizard;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.LinkedList;

/**
 * Created by Simon on 2016-04-22.
 */
public class SaveSharedData
{
    static final String PREF_USER_NAME= "username";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }
    public static void clearGenQuestions(Context ctx) {
        int toIndex= getSharedPreferences(ctx).getInt("toGenIndex", 0);
        setToGenIndex(ctx,0);
        setFromGenIndex(ctx,0);
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        for (int i =0; i<= toIndex; i++){
            editor.putString("GenQuestion_" + i,"");
            editor.putString("GenAnswer_" + i,"");
            editor.putString("GenAlt1_" + i,"");
            editor.putString("GenAlt2_" + i,"");
            editor.putString("GenAlt3_" + i,"");
        }
        editor.commit();
    }
    public static void clearCurrentQuestion(Context ctx){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString("currentQuestionQuestion","");
        editor.putString("currentQuestionAnswer","Answer");
        editor.putString("currentQuestionAlt1","Alt1");
        editor.putString("currentQuestionAlt2","Alt2");
        editor.putString("currentQuestionAlt3","Alt3");
        editor.commit();
    }
    public static void setCurrentQuestion(Context ctx, Question currentQuestion){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString("currentQuestionQuestion",currentQuestion.getQuestion());
        editor.putString("currentQuestionAnswer",currentQuestion.getAnswer());
        editor.putString("currentQuestionAlt1",currentQuestion.getAlt1());
        editor.putString("currentQuestionAlt2",currentQuestion.getAlt2());
        editor.putString("currentQuestionAlt3",currentQuestion.getAlt3());
        editor.commit();
    }
    public static Question getCurrentQuestion(Context ctx){
        Question currentQuestion = new Question("","Answer","Alt1","Alt2","Alt3");
        currentQuestion.setQuestion(getSharedPreferences(ctx).getString("currentQuestionQuestion",""));
        currentQuestion.setAnswer(getSharedPreferences(ctx).getString("currentQuestionAnswer","Answer"));
        currentQuestion.setAlt1(getSharedPreferences(ctx).getString("currentQuestionAlt1","Alt1"));
        currentQuestion.setAlt2(getSharedPreferences(ctx).getString("currentQuestionAlt2","Alt2"));
        currentQuestion.setAlt3(getSharedPreferences(ctx).getString("currentQuestionAlt3","Alt3"));
        return currentQuestion;

    }
    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }
    public static void setToGenIndex(Context ctx, int toGenIndex)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt("toGenIndex", toGenIndex);
        editor.commit();
    }
    public static int getToGenIndex(Context ctx)
    {
        return getSharedPreferences(ctx).getInt("toGenIndex", 0);
    }
    public static void setFromGenIndex(Context ctx, int fromGenIndex)
    {
        Log.i("setfromgenindex","random");
        Log.i("setfromgenindex",String.valueOf(fromGenIndex));
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt("fromGenIndex", fromGenIndex);
        editor.commit();
    }
    public static int getFromGenIndex(Context ctx)
    {
        Log.i("getfromgenindex","woop");
        return getSharedPreferences(ctx).getInt("fromGenIndex", 0);
    }
    public static void setGenQuestions(Context ctx, LinkedList<Question> genQuestions){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        for (int i =0; i< genQuestions.size(); i++){
            Log.i("setloop",genQuestions.get(i).getQuestion());
            editor.putString("GenQuestion_" + i,genQuestions.get(i).getQuestion());
            editor.putString("GenAnswer_" + i,genQuestions.get(i).getAnswer());
            editor.putString("GenAlt1_" + i,genQuestions.get(i).getAlt1());
            editor.putString("GenAlt2_" + i,genQuestions.get(i).getAlt2());
            editor.putString("GenAlt3_" + i,genQuestions.get(i).getAlt3());
        }
        editor.commit();
    }
    public static LinkedList<Question> getGenQuestions(Context ctx,int fromIndex, int toIndex){
        LinkedList<Question> questionList = new LinkedList<Question>();

        for (int i = fromIndex; i<= toIndex; i++){
            String question = getSharedPreferences(ctx).getString("GenQuestion_" +i, "");
            String answer  =  getSharedPreferences(ctx).getString("GenAnswer_" +i, "Answer");
            String alt1  =  getSharedPreferences(ctx).getString("GenAlt1_" +i, "Alt1");
            String alt2  =  getSharedPreferences(ctx).getString("GenAlt2_" +i, "Alt2");
            String alt3  =  getSharedPreferences(ctx).getString("GenAlt3_" +i, "Alt3");
            Question oneQuestion = new Question(question,answer,alt1,alt2,alt3);
            questionList.add(oneQuestion);
        }
        return questionList;
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }
}
