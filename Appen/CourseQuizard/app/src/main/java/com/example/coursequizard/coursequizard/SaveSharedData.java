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
        editor.putString("currentQuestionAnswer","");
        editor.putString("currentQuestionAlt1","");
        editor.putString("currentQuestionAlt2","");
        editor.putString("currentQuestionAlt3","");
        editor.commit();
    }
    public static void clearAll(Context ctx){
        clearGenQuestions(ctx);
        clearCurrentQuestion(ctx);
        clearUser(ctx);
        clearMyUniversity(ctx);
        clearFriendList(ctx);
    }
    public static void clearUser(Context ctx){
        setUserName(ctx,"");
    }
    public static void clearMyUniversity(Context ctx){
        setMyUniversityName(ctx,"Uppsala University");
        setMyUniversityID(ctx,"1");


    }
    public static void clearFriendList(Context ctx){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
       int  length =  getSharedPreferences(ctx).getInt("numberoffriends",0);
        editor.putInt("numberoffriends",0);

        for(int i = 0; i < length; i++){
            editor.putString("friend_" + i, "");
        }
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
        currentQuestion.setAnswer(getSharedPreferences(ctx).getString("currentQuestionAnswer",""));
        currentQuestion.setAlt1(getSharedPreferences(ctx).getString("currentQuestionAlt1",""));
        currentQuestion.setAlt2(getSharedPreferences(ctx).getString("currentQuestionAlt2",""));
        currentQuestion.setAlt3(getSharedPreferences(ctx).getString("currentQuestionAlt3",""));
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
    /*
    public static void setMyCourses(Context ctx, ArrayList<Course> myCourses){

        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        int j =0;
        for (int i =0; i< myCourses.size(); i++){
            Log.i("setloop",myCourses.get(i).getName());
            editor.putInt("MyCoursesC_ID_" + i,myCourses.get(i).getC_ID());
            editor.putInt("MyCoursesUni_ID_" + i,myCourses.get(i).getUni_ID());
            editor.putString("MyCoursesUni_name_" + i,myCourses.get(i).getUni_name());
            editor.putString("MyCoursesCourse_code_" + i,myCourses.get(i).getCourse_code());
            editor.putString("MyCoursesName_" + i,myCourses.get(i).getName());
            j=i;
        }
        setMyCoursesSize(ctx,j+1);
        editor.commit();
    }
    public static ArrayList<Course> getMyCourses(Context ctx){
        ArrayList<Course> myCourses = new ArrayList<Course>();
        int myCoursesSize = getSharedPreferences(ctx).getInt("MyCoursesSize", 0);
        for (int i = 0; i< myCoursesSize ; i++){
            int courseID = getSharedPreferences(ctx).getInt("MyCoursesC_ID_" +i, -1);
            int uniID  =  getSharedPreferences(ctx).getInt("MyCoursesUni_ID_" +i, -1);
            String uniName  =  getSharedPreferences(ctx).getString("MyCoursesUni_name_" +i, "");
            String courseCode  =  getSharedPreferences(ctx).getString("MyCoursesCourse_Code_" +i, "");
            String courseName  =  getSharedPreferences(ctx).getString("MyCoursesName_" +i, "");
            Course oneCourse = new Course(courseID,uniID,uniName,courseCode,courseName);
        }
        return myCourses;
    }
    public static void addMyCourse(Context ctx, Course myCourse){
        int i = getSharedPreferences(ctx).getInt("MyCoursesSize", 0);
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt("MyCoursesC_ID_" + i,myCourse.getC_ID());
        editor.putInt("MyCoursesUni_ID_" + i,myCourse.getUni_ID());
        editor.putString("MyCoursesUni_name_" + i,myCourse.getUni_name());
        editor.putString("MyCoursesCourse_code_" + i,myCourse.getCourse_code());
        editor.putString("MyCoursesName_" + i,myCourse.getName());
        editor.putInt("MyCoursesSize", i+1);
        editor.commit();
    }
    public static void removeMyCourse(Context ctx, Course myCourse){
        int i = getSharedPreferences(ctx).getInt("MyCoursesSize", 0);
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt("MyCoursesC_ID_" + i,myCourse.getC_ID());
        editor.putInt("MyCoursesUni_ID_" + i,myCourse.getUni_ID());
        editor.putString("MyCoursesUni_name_" + i,myCourse.getUni_name());
        editor.putString("MyCoursesCourse_code_" + i,myCourse.getCourse_code());
        editor.putString("MyCoursesName_" + i,myCourse.getName());
        editor.putInt("MyCoursesSize", i+1);
        editor.commit();
    }
    public static void  setMyCoursesSize(Context ctx, int myCoursesSize){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt("MyCoursesSize", myCoursesSize);
        editor.commit();
    }
    public static int  getMyCoursesSize(Context ctx){
        return getSharedPreferences(ctx).getInt("MyCoursesSize", 0);
    }
*/
    public static void setMyUniversityName(Context ctx, String universityName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString("MyUniversityName", universityName);
        editor.commit();
    }
    public static void setMyUniversityID(Context ctx, String universityID)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString("MyUniversityID", universityID);
        editor.commit();
    }
    public static String getMyUniversityName(Context ctx)
    {
        return getSharedPreferences(ctx).getString("MyUniversityName", "Uppsala University");
    }
    public static String getMyUniversityID(Context ctx)
    {
        return getSharedPreferences(ctx).getString("MyUniversityID", "1");
    }
}
