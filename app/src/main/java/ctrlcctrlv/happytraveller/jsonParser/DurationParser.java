package ctrlcctrlv.happytraveller.jsonParser;

import org.json.JSONException;
import org.json.JSONObject;

public class DurationParser {
    public static String durationParse(JSONObject duration){
        String durationValue=null;
        try {
            JSONObject routes = duration.getJSONObject("routes");
            JSONObject legs = routes.getJSONObject("legs");
            JSONObject durationJson = legs.getJSONObject("duration");
            String durationValueJ = durationJson.getString("value");
            durationValue = durationValueJ;
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return durationValue;
    }
}
