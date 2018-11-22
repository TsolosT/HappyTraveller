package ctrlcctrlv.happytraveller.jsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import ctrlcctrlv.happytraveller.model.PlaceData;

public class PlaceParser {


    public static ArrayList parseGoogleParse(final String response) {

        ArrayList temp = new ArrayList();

        try {
            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(response);


            // make an jsonObject in order to parse the response
            if (jsonObject.has("results")) {

                JSONArray jsonArray = jsonObject.getJSONArray("results");


                for (int i = 0; i < jsonArray.length(); i++)
                {
                        //ka8e new name = new json obj
                    if (jsonArray.getJSONObject(i).has("name")) {
                        //todo add more detail  & photo
                            temp.add(new PlaceData(jsonArray.getJSONObject(i).optString("name"), jsonArray.getJSONObject(i).optString("vicinity")));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
        return temp;

    }

}
