package ctrlcctrlv.happytraveller.jsonParser;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class OpenParser {
    String firstJson;
    String secondJson;
    String openHours = null;
    String distance = null;

    public OpenParser(String link) {
        firstJson = link;

    }

    public String getFirstJson() {
        return firstJson;
    }

    public void setFirstJson(String firstJson) {
        this.firstJson = firstJson;
    }

    public String getSecondJson() {
        return secondJson;
    }

    public void setSecondJson(String secondJson) {
        this.secondJson = secondJson;
    }

    JsonParse parse = new JsonParse();


    {
        try {

            openHours = parse.execute(firstJson).get();

        } catch (ExecutionException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

    }







    public class JsonParse extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
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
                    result += current;
                    data = reader.read();

                }

                return result;

            }
            catch (Exception e) {
                e.printStackTrace();
                return "FAILED";

            }
        }
        protected void onPostExecute(String openHours){
            super.onPostExecute(openHours);
            JsonParse distanceParse = new JsonParse();
            try {
                distance = distanceParse.execute(secondJson).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {


                JSONArray openingHoursArray = new JSONArray(openHours);
                for (int i=0; i<openingHoursArray.length(); i++){
                    JSONObject openHoursPart = openingHoursArray.getJSONObject(i);
                    // to open now einai to true h false
                    JSONObject opennow = openHoursPart.getJSONObject("open_now");





                }
                JSONArray distanceArray = new JSONArray(distance);
                for (int i=0;i<distanceArray.length();i++){
                    JSONObject routesPart = distanceArray.getJSONObject(i);
                    JSONObject legsPart = routesPart.getJSONObject("legs");
                    JSONObject distancePart = legsPart.getJSONObject("distance");
                    // ayto einai to distance
                    JSONObject distance = distancePart.getJSONObject("value");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
