package ctrlcctrlv.happytraveller.url;


public class PlaceUrl {


    private  String url = null;
    private  String placeType = null;
    private  String latLng = null;


    public PlaceUrl()
    {
        url = "https://maps.googleapis.com/maps/api/place/search/json?";
    }


    public String getUrl()
    {
        url+="location="+this.latLng;
        url+="&type="+this.placeType;
        url+="&radius=1000"; // TODO: 19/11/2018 fix standard radius value
        url+="&sensor=true";
        url+="&key=AIzaSyDI0zKd22JBJEGco2k9Thg2CZWLLsWfq7k";
        return url;
    }


    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String getLatLng() {
        return latLng;
    }

    public void setLatLng(String latLng) {
        this.latLng = latLng;
    }
}
