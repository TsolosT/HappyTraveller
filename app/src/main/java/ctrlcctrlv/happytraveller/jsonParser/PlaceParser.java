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
        double latitude=0.0;
        double longitude=0.0;
        try {
            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(response);


            // make an jsonObject in order to parse the response
            if (jsonObject.has("results")) {

                JSONArray jsonArray = jsonObject.getJSONArray("results");
                ArrayList<PlacePhoto> placePhotos=new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JsonObj = jsonArray.getJSONObject(i);
                    geometryObject = JsonObj.getJSONObject("geometry");
                    locationObject = geometryObject.getJSONObject("location");
                    latitude = locationObject.getDouble("lat");
                    longitude = locationObject.getDouble("lng");
                        //ka8e new name = new json obj
                    if (jsonArray.getJSONObject(i).has("name"))
                    {   //at the moment get only one img for each place
                        if(jsonArray.getJSONObject(i).has("photos"))
                        {
                            JSONArray photos=jsonArray.getJSONObject(i).getJSONArray("photos");
                            //todo get all img
//                            for(int j=0;j<jsonArray.getJSONObject(i).getJSONArray("photos").length();j++)
//                            {
//                                placePhotos.add(new PlacePhoto(((JSONObject) photos.get(j)).getString("photo_reference")));
//                            }
                            temp.add(new PlaceData(jsonArray.getJSONObject(i).optString("name"), jsonArray.getJSONObject(i).optString("vicinity"),new PlacePhoto(((JSONObject) photos.get(0)).getString("photo_reference")),latitude,longitude));
                        }
                        else
                        {
                            temp.add(new PlaceData(jsonArray.getJSONObject(i).optString("name"), jsonArray.getJSONObject(i).optString("vicinity"),new PlacePhoto(null),latitude,longitude));
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

}
