package com.example.coursequizard.coursequizard;

import android.os.AsyncTask;
import android.app.AlertDialog;
import android.content.Context;
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

/**
 * Created by Simon on 2016-04-18.
 */
public class BackgroundWithServer extends AsyncTask<String,Void,String> {
    String operationURL="";
    String post_data = "";
    Context context;
    AlertDialog alertDialog;
    BackgroundWithServer (Context ctx) {
        context = ctx;
    }

    private void addQuestion(String... params) {

        String question = params[1];
        String answer = params[2];
        String alt1 = params[3];
        String alt2 = params[4];
        String alt3 = params[5];
        try {
            post_data = URLEncoder.encode("question", "UTF-8") + "=" + URLEncoder.encode(question, "UTF-8") + "&"
                    + URLEncoder.encode("answer", "UTF-8") + "=" + URLEncoder.encode(answer, "UTF-8") + "&"
                    + URLEncoder.encode("alt1", "UTF-8") + "=" + URLEncoder.encode(alt1, "UTF-8") + "&"
                    + URLEncoder.encode("alt2", "UTF-8") + "=" + URLEncoder.encode(alt2, "UTF-8") + "&"
                    + URLEncoder.encode("alt3", "UTF-8") + "=" + URLEncoder.encode(alt3, "UTF-8") + "&";


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        if(type.equals("add question")) {
            addQuestion(params);
            operationURL = "http://130.238.250.231/addquestiontodb.php";
        }
            try {
                URL url = new URL(operationURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                StringBuilder sb = new StringBuilder();
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                    sb.append(line + "\n");
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
        alertDialog.setTitle("Question status");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}