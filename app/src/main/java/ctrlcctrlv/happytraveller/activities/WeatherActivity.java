package ctrlcctrlv.happytraveller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import ctrlcctrlv.happytraveller.R;

public class WeatherActivity extends AppCompatActivity
{

    private Intent intent;
    // j1=JSON for location of weather
    String j1 = null;
    //j2=JSON for weather of that location
    String j2 = null;
    //Key from the json of the location
    String key;
    TextView weatherReport;

    public void checkWeather (View view)
    {
        //parse for the JSON

        DownloadTask task = new DownloadTask();
        HomeActivity homeActivity = new HomeActivity();

        try {
            j1 = task.execute("http://dataservice.accuweather.com/locations/v1/cities/geoposition/search?apikey=jq2F4GlI2Q5e4JuDkghiWDOcrVBApCJH&q="+homeActivity.getUsersLocation().latitude+","+homeActivity.getUsersLocation().longitude).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        init();
    }

    public class DownloadTask extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... urls)
        {

            StringBuilder result = new StringBuilder();
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result.append(current);
                    data = reader.read();

                }
                return result.toString();

            }
            catch (Exception e) {
                e.printStackTrace();
                return "FAILED";
            }
        }
        @Override
        protected void onPostExecute(String j1)
        {
            super.onPostExecute(j1);
            // print key value
            try {
                JSONObject theKey  = new JSONObject(j1);

                key = theKey.getString("Key");

                try {
                    DownloadTask weather = new DownloadTask();
                    j2 = weather.execute("http://dataservice.accuweather.com/currentconditions/v1/"+ key + ".json?language=en&apikey=5LGnlvGAhG9hCLTxO33ASOrDkv7JZtOc").get();

                    JSONArray weatherArray = new JSONArray(j2);
                    //all data for weather from the JSON
                    for (int i = 0; i<weatherArray.length(); i++){

                        JSONObject weatherPart = weatherArray.getJSONObject(i);
                        JSONObject temp = weatherPart.getJSONObject("Temperature");
                        String tempC = temp.getJSONObject("Metric").getString("Value");

                        weatherReport.setText(weatherPart.getString("WeatherText")+ " " +tempC+"C" );

                    }

                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                } catch (ExecutionException e)
                {
                    e.printStackTrace();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void init()
    {
        intent=getIntent();
        weatherReport = (TextView) findViewById(R.id.weatherReport);
    }
}