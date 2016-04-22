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
 * Not used
 * Not used
 * Not used
 * Not used
 * Not used
 * Not used
 * Not used
 * Not used
 *
 */
public class GetQuestionsFromServer extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    GetQuestionsFromServer (Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String addQuestionURL = "http://130.238.250.231/getquestionsfromdb.php";
        if(type.equals("get questions")) {
            try {
                String courseID = params[1];
                String answer = params[2];
                String alt1 = params[3];
                String alt2 = params[4];
                String alt3 = params[5];

                URL url = new URL(addQuestionURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("question","UTF-8")+"="+URLEncoder.encode(courseID,"UTF-8")+"&"
                        +URLEncoder.encode("answer","UTF-8")+"="+URLEncoder.encode(answer,"UTF-8") + "&"
                        +URLEncoder.encode("alt1","UTF-8")+"="+URLEncoder.encode(alt1,"UTF-8") + "&"
                        +URLEncoder.encode("alt2","UTF-8")+"="+URLEncoder.encode(alt2,"UTF-8") + "&"
                        +URLEncoder.encode("alt3","UTF-8")+"="+URLEncoder.encode(alt3,"UTF-8") + "&";
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
