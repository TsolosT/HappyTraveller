package ctrlcctrlv.happytraveller.url;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;


//Url class has two constructor one just to truck the route and one if the user prefers a travel mode
public class CreateRoutesUrl
{

    private  String url = null;
    private  String origin = null;
    private  String destination = null;


    public CreateRoutesUrl()
    {
        url = "https://maps.googleapis.com/maps/api/directions/json?origin=";
      //  url = "https://www.google.com/maps/dir/?api=1&origin=";
    }

    public CreateRoutesUrl(String travelMode)
    {
        url = "https://maps.googleapis.com/maps/api/directions/json?"+"&travelmode="+travelMode+"&origin=";
    }

    public String getUrl(LatLng start, LatLng end ,android.content.Context context, java.util.Locale locale)
    {
        origin = getLocationsName(start,context,locale);
        destination = getLocationsName(end,context,locale);

       // url += origin+",IL&destination="+destination+"OK&key=AIzaSyDI0zKd22JBJEGco2k9Thg2CZWLLsWfq7k";
        url += origin+",&destination="+destination+"OK&key=AIzaSyDI0zKd22JBJEGco2k9Thg2CZWLLsWfq7k";
        return url;
    }



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
