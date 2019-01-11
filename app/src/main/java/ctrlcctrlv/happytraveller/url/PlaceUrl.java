package ctrlcctrlv.happytraveller.url;

/*
*<b>This is a model class for Place Api url.<b>
* This class create,initialize & prepare the proper
* url to be used in order to make a place api call.
*
* @param url  A private String variable that will hold the url link
* @param placeType A private String variable that will hold the place type to fill the attribute on <b>url</b> variable
* @param latLng A private String variable that will hold the latitude&longitude to fill the attribute on <b>url</b> variable
*
* @since 19 Nov 2018
*/
public class PlaceUrl {


    private  String url = null;
    private  String placeType = null;
    private  String latLng = null;

    /*
    * The main constructor <b>public PlaceUrl()</b>.
    * Initialize the url with the starter url link.
    */
    public PlaceUrl()
    {
        url = "https://maps.googleapis.com/maps/api/place/search/json?";
    }

    /*
    *The main method that builds the missing parts of url link.
    *It append into url variable the proper attributes that the link
    * needs.
    * @return url  It return the complete url link.
     */
    public String getUrl()
    {
        url+="location="+this.latLng;
        url+="&type="+this.placeType;
        url+="&radius=1000"; // TODO: 19/11/2018 fix standard radius value
        url+="&sensor=true";
        url+="&key=AIzaSyDI0zKd22JBJEGco2k9Thg2CZWLLsWfq7k";
        return url;
    }

    /*
    *A Method that return the placeType variable.
    * A public String method.
    * @return placeType This variable is used to pass attribute on getUrl() method.
     */
    public String getPlaceType() {
        return placeType;
    }
    /*
     *A Method that pass a value to placeType variable.
     * A public void method that pass the proper value on placeType attribute.
     * @param placeType This variable is used pass a value to this.placeType variable.
     */
    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }
    /*
     *A Method that return latLng variable.
     * A public String method.
     * @return latLng This variable is used to pass attribute on getUrl() method.
     */
    public String getLatLng() {
        return latLng;
    }
    /*
     *A Method that pass a value to latLng variable.
     * A public void method that pass the proper value on latLng attribute.
     * @param latLng This variable is used pass a value to this.latLng variable.
     */
    public void setLatLng(String latLng) {
        this.latLng = latLng;
    }
}
