package com.example.arpesh.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import java.io.BufferedOutputStream;
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
 * Created by arpesh on 4/2/18.
 */

public class BackGroundWorker extends AsyncTask<String,Void,String> {
    private Context mcontext ;
    private AlertDialog alertDialog;
    BackGroundWorker(Context ctx){
        mcontext= ctx;
    }
    @Override
    protected String doInBackground(String... params) {
      String type = params[0];
      String Login_url = "http://192.168.1.4/login.php";
      if(type.equals("Login")) {
          try {
              String user_name = params[1];
              String password = params[2];
              URL url = new URL(Login_url);
              HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
              httpURLConnection.setRequestMethod("POST");
              httpURLConnection.setDoOutput(true);
              httpURLConnection.setDoInput(true);
              OutputStream outputStream = httpURLConnection.getOutputStream();
              BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
              String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&"
                      + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
              bufferedWriter.write(post_data);
              bufferedWriter.flush();
              bufferedWriter.close();
              outputStream.close();
              InputStream inputStream = httpURLConnection.getInputStream();
              BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
              String result = "";
              String line;
              while ((line = bufferedReader.readLine()) != null) {
                  result += line;
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
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(mcontext).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("Connection successful")){
            Intent i = new Intent(mcontext,SecondActivity.class);
            mcontext.startActivity(i);

        }
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
