package ctrlcctrlv.happytraveller.jsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import ctrlcctrlv.happytraveller.model.PlaceData;
import ctrlcctrlv.happytraveller.model.PlacePhoto;

/*
*A parser  that is used to parse tha response of Place Api call.
*A public class parser that have two method that parse
* the json response to PlaceData array.
*
* @since 19 Nov 2018
 */
public class PlaceParser {

    /*
    *A parser method that parse the json response to an ArrayList<PlaceData>.
    * A public static ArrayList method that takes as param an String  json response and
    * parse it to PlaceData object and returns all these object as array.
    * @param response A final String variable that has as value the  json call response.
    *
    * @return temp An ArrayList that will have the PlaceData data from the json.
     */
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

    /*
    *A method that format a string to return only city & country value.
    *An public static string method that gets a string and split it proper
    * to get only the city and the country name for the place object.
    *@param x An String variable with value a json array as string.
    * @return cityAndCountry A String variable that return the proper string format.
     */
   public static String getCompountCode(String x)
   {
       //getting this a string example {"compound_code":"3HV3+JX Serres, Greece","global_code":"8GH53HV3+JX"}
       //then split it and return city and country
       String cityAndCountry="not available";
        String[] parts= x.split(" ");
       String[] subpart= parts[2].split("\"");

        cityAndCountry=parts[1]+subpart[0];

       return cityAndCountry;
   }

}
