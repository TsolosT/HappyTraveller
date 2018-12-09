package ctrlcctrlv.happytraveller.jsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DistanceParser {
    public static String distanceParse(JSONObject distance){
        String distanceValue=null;
        try {
            JSONObject routes = distance.getJSONObject("routes");
            JSONObject legs = routes.getJSONObject("legs");
            JSONObject distanceJson = legs.getJSONObject("distance");
            String distanceValueJ = distanceJson.getString("value");
            distanceValue = distanceValueJ;
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return distanceValue;
    }
}
