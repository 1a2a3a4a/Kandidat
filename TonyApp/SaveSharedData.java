package com.example.coursequizard.coursequizard;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
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
        Log.i("getfromgenindex", "woop");
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
            editor.putString("GenAlt3_" + i, genQuestions.get(i).getAlt3());
        }
        editor.commit();
    }
    public static LinkedList<Question> getGenQuestions(Context ctx,int fromIndex, int toIndex){
        LinkedList<Question> questionList = new LinkedList<Question>();

        for (int i = fromIndex; i<= toIndex; i++){
            String question = getSharedPreferences(ctx).getString("GenQuestion_" +i, "");
            String answer  =  getSharedPreferences(ctx).getString("GenAnswer_" +i, "");
            String alt1  =  getSharedPreferences(ctx).getString("GenAlt1_" +i, "");
            String alt2  =  getSharedPreferences(ctx).getString("GenAlt2_" +i, "");
            String alt3  =  getSharedPreferences(ctx).getString("GenAlt3_" +i, "");
            Question oneQuestion = new Question(question,answer,alt1,alt2,alt3);
            questionList.add(oneQuestion);
        }
        return questionList;
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }
    //tony kod vvvv
    public static ArrayList<String> getFriendList(Context ctx){
           ArrayList<String> friendList = new ArrayList<String>();
           int n = getSharedPreferences(ctx).getInt("numberoffriends", 0);
           for(int i = 0; i < n; i++){
                friendList.add(getSharedPreferences(ctx).getString("friend_" + i, ""));
           }
        return friendList;
    }
    public static void setFriendList(Context ctx, ArrayList<String> friendList){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        int length = friendList.size();
        for(int i = 0; i < length; i++){
            editor.putString("friend_" + i, friendList.get(i));
        }
        editor.putInt("numberoffriends",friendList.size());
        editor.apply();
    }
    // tony kod ^^^^^^

}
