package ctrlcctrlv.happytraveller.jsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import ctrlcctrlv.happytraveller.model.PlaceData;
import ctrlcctrlv.happytraveller.model.PlacePhoto;

public class PlaceParser {


    public static ArrayList parseGoogleParse(final String response) {

        ArrayList temp = new ArrayList();
        JSONObject geometryObject;
        JSONObject locationObject;
        JSONObject JsonObj;
        Double latitude=null;
        Double longitude=null;
        int id;
        try {
            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(response);

            // make an jsonObject in order to parse the response
            if (jsonObject.has("results"))
            {

                JSONArray jsonArray = jsonObject.getJSONArray("results");
                ArrayList<PlacePhoto> placePhotos=new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++)
                {

                    JsonObj = jsonArray.getJSONObject(i);
                    geometryObject = JsonObj.getJSONObject("geometry");
                    locationObject = geometryObject.getJSONObject("location");
                    latitude = locationObject.getDouble("lat");
                    longitude = locationObject.getDouble("lng");
                    id=i;
                        //ka8e new name = new json obj
                    if (jsonArray.getJSONObject(i).has("name"))
                    {
                        //get location with string not lat lng
                        String cityAndCountry= getCompountCode(jsonArray.getJSONObject(i).optString("plus_code"));
                        //at the moment get only one img for each place
                        if(jsonArray.getJSONObject(i).has("photos"))
                        {
                            JSONArray photos=jsonArray.getJSONObject(i).getJSONArray("photos");


                            temp.add(new PlaceData(jsonArray.getJSONObject(i).optString("name"), jsonArray.getJSONObject(i).optString("vicinity"),new PlacePhoto(((JSONObject) photos.get(0)).getString("photo_reference")),latitude,longitude,cityAndCountry));
                        }
                        else
                        {
                            temp.add(new PlaceData(jsonArray.getJSONObject(i).optString("name"), jsonArray.getJSONObject(i).optString("vicinity"),new PlacePhoto(null),latitude,longitude,cityAndCountry));
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
        return temp;

    }
    //getting this a string example {"compound_code":"3HV3+JX Serres, Greece","global_code":"8GH53HV3+JX"}
    //then split it and return city and country
   public static String getCompountCode(String x)
   {    String cityAndCountry="not available";
        String[] parts= x.split(" ");
       String[] subpart= parts[2].split("\"");

        cityAndCountry=parts[1]+subpart[0];

       return cityAndCountry;
   }

}
