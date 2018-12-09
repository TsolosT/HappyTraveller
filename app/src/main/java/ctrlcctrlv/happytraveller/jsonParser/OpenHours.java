package ctrlcctrlv.happytraveller.jsonParser;

import org.json.JSONException;
import org.json.JSONObject;

public class OpenHours {
    public static String openHoursParse(JSONObject openHours){
        String returnOpenNow=null;
        try {
            JSONObject data = openHours.getJSONObject("results");
            JSONObject openHoursPart = data.getJSONObject("opening_hours");
           String openNow = openHoursPart.getString("open_now");
           returnOpenNow = openNow;
            
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return returnOpenNow;
    }
}
