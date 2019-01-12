package ctrlcctrlv.happytraveller.url;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;



public class RoutesUrl
{

    private  String url = null;
    private  String origin = null;
    private  String destination = null;


    /**
     * Initialize the half url with the travel mode user picked
     * @param travelMode
     */
    public RoutesUrl(String travelMode)
    {
        url = "https://maps.googleapis.com/maps/api/directions/json?"+"&mode="+travelMode+"&origin=";
    }

    /**
     * Initialize the other half url with a start and end point
     * @param start
     * @param end
     * @param context
     * @param locale
     * @return the whole url
     */
    public String getUrl(LatLng start, LatLng end ,android.content.Context context, java.util.Locale locale)
    {
        origin = getLocationsName(start,context,locale);
        destination = getLocationsName(end,context,locale);

        url += origin+",&destination="+destination+"OK&key=AIzaSyDI0zKd22JBJEGco2k9Thg2CZWLLsWfq7k";
        return url;
    }


    /**
     *
     * @param area a latitude and longitude
     * @param context
     * @param locale
     *
     * @return the name that the specific area (lat,lng) has .
     */
    private String getLocationsName(LatLng area , android.content.Context context ,java.util.Locale locale)
    {
        Geocoder originLocation ;
        List<Address> list;
        Address address;
        String result = null;

        originLocation = new Geocoder(context, locale);
        {
            try
            {
                list = originLocation.getFromLocation(area.latitude,area.longitude,1);
                address=list.get(0);
                result=address.getAddressLine(0) + "," + address.getLocality();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

}
