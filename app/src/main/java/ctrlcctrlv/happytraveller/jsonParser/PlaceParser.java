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
                    PlaceData place = new PlaceData();
                        //ka8e new name = new json obj
                    if (jsonArray.getJSONObject(i).has("name")) {
                        //todo add more detail  & photo
                        //    temp.add(new PlaceData(jsonArray.getJSONObject(i).optString("name"), jsonArray.getJSONObject(i).optString("vicinity")));
                        place.setName(jsonArray.getJSONObject(i).optString("name"));
                        place.setAddress(jsonArray.getJSONObject(i).optString("vicinity"));


                        if (jsonArray.getJSONObject(i).has("types")) {
                            JSONArray typesArray = jsonArray.getJSONObject(i).getJSONArray("types");

                            for (int j = 0; j < typesArray.length(); j++) {
                                place.setCategory(typesArray.getString(j) + ", " + place.getCategory());
                            }
                        }
                    }
                    temp.add(place);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
        return temp;

    }

}
