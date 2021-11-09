package com.example.androidassignments;

import static android.view.View.VISIBLE;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import android.graphics.BitmapFactory;



public class WeatherForecast extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    protected static final String ACTIVITY_NAME = "WeatherForecast";
    String[] cities = {"Toronto","Montreal","Vancouver","Calgary","Edmonton","Ottawa","Mississauga","Winnipeg","Quebec City","Hamilton","Brampton"};
    TextView current,min,max;
    ProgressBar progressbar;
    ImageView weatherimg;
    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        current = (TextView) findViewById(R.id.current);
        min = (TextView) findViewById(R.id.min);
        max = (TextView) findViewById(R.id.max);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        weatherimg = (ImageView) findViewById(R.id.weatherimg);
        spin = (Spinner) findViewById(R.id.spinner);
        progressbar.setVisibility(VISIBLE);

        spin.setOnItemSelectedListener(this);

        ArrayAdapter list = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cities);
        list.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(list);
    }

    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    public InputStream downloadUrl(String urlString) throws IOException{
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String s = "http://api.openweathermap.org/data/2.5/weather?q=" + cities[i] + ",ca&APPID=412603f468b764d4913404ff1c2ef996&mode=xml&units=metric";
        ForecastQuery fq = new ForecastQuery();
        fq.execute(s);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        ForecastQuery fq = new ForecastQuery();
        fq.execute();
    }

    private class ForecastQuery extends AsyncTask<String, Integer, String>{

        protected String min_temp;
        protected String max_temp;
        protected String current_temp;
        protected Bitmap weather_img;

        @Override
        protected String doInBackground(String... strings){

            String urlString = strings[0];
            try {
                InputStream istream = downloadUrl(urlString);
                XmlPullParser parser = Xml.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(istream,null);

                int eventType = parser.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if(eventType == XmlPullParser.START_TAG) {
                        if (parser.getName().equals("temperature")) {
                            current_temp = parser.getAttributeValue(null, "value");
                            publishProgress(25);
                            min_temp = parser.getAttributeValue(null, "min");
                            publishProgress(50);
                            max_temp = parser.getAttributeValue(null, "max");
                            publishProgress(75);


                        }
                        else if (parser.getName().equals("weather")){
                            String iconName = parser.getAttributeValue(null,"icon");
                            if (fileExistance(iconName+".png")){
                                FileInputStream fis = null;
                                try {
                                    fis = openFileInput(iconName+".png");
                                }
                                catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                Bitmap image = BitmapFactory.decodeStream(fis);
                                FileOutputStream outputStream = openFileOutput( iconName + ".png", Context.MODE_PRIVATE);
                                image.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                                outputStream.flush();
                                outputStream.close();
                                weather_img = image;
                            }
                            else{
                                String urlString2 = "http://openweathermap.org/img/w/" + iconName + ".png";
                                URL url = new URL(urlString2);
                                HttpURLConnection connection = null;
                                Bitmap image = null;
                                try {
                                    connection = (HttpURLConnection) url.openConnection();
                                    connection.connect();
                                    int responseCode = connection.getResponseCode();
                                    if (responseCode == 200) {
                                        image = BitmapFactory.decodeStream(connection.getInputStream());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    if (connection != null) {
                                        connection.disconnect();
                                    }
                                }
                                FileOutputStream outputStream = openFileOutput( iconName + ".png", Context.MODE_PRIVATE);
                                image.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                                outputStream.flush();
                                outputStream.close();
                            }
                            publishProgress(100);

                        }
                    }
                    eventType = parser.next();
                }
            } catch (IOException | XmlPullParserException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressbar.setVisibility(View.VISIBLE);
            values[0] = progressbar.getProgress();
        }
        @SuppressLint("WrongThread")
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            weatherimg.setImageBitmap(weather_img);
            current.setText("Current temperature: " + current_temp + "°C");
            min.setText("Minimum temperature: " + min_temp + "°C");
            max.setText("Maximum temperature: " + max_temp + "°C");
            progressbar.setVisibility(View.INVISIBLE);

        }

    }

    public boolean fileExistance(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

}
