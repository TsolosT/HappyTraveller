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
    String openHours = null;
    String distance = null;

    JsonParse parse = new JsonParse();


    {
        try {

            openHours = parse.execute("https://maps.googleapis.com/maps/api/place/search/json?location=41.0943488%2C23.5544576&type=museum&radius=10000&sensor=true&key=AIzaSyDI0zKd22JBJEGco2k9Thg2CZWLLsWfq7k&fbclid=IwAR23Ofldq-QyCYVPQ_IgCtK71UogM53bN6HXQjHs17zOk_0UlZbxe3qSXNg").get();

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
            try {


                JSONArray openingHoursArray = new JSONArray(openHours);
                for (int i=0; i<openingHoursArray.length(); i++){
                    JSONObject openHoursPart = openingHoursArray.getJSONObject(i);
                    // to open now einai to true h false
                    JSONObject opennow = openHoursPart.getJSONObject("open_now");




                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
