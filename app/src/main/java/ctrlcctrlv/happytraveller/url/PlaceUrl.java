package ctrlcctrlv.happytraveller.url;

/**
*<h2>This is a model class for Place Api url.</h2>
*<p>This class create,initialize & prepare the proper
* url to be used in order to make a place api call.</p>
*
* @since 19 Nov 2018
*/
public class PlaceUrl {


    private  String url = null;
    private  String placeType = null;
    private  String latLng = null;

    /**
    *<h2>The main constructor <b>public PlaceUrl()</b>.</h2>
    *<p>Initialize the url with the starter url link.</p>
    */
    public PlaceUrl()
    {
        url = "https://maps.googleapis.com/maps/api/place/search/json?";
    }

    /**
    *<h2>The main method that builds the missing parts of url link.</h2>
    *<p>It append into url variable the proper attributes that the link
    * needs.</p>
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

    /**
    *<h2>A Method that return the place type.</h2>
    *<p>A public String method.That returns the placeType variable</p>
    * @return placeType This variable is used to pass attribute on getUrl() method.
     */
    public String getPlaceType() {
        return placeType;
    }
    /**
     *<h2>A Method that pass a value to placeType variable.</h2>
     *<p>A public void method that pass the proper value on placeType attribute.</p>
     * @param placeType This variable is used pass a value to this.placeType variable.
     */
    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }
    /**
     *<h2>A Method that return latitude & longitude.</h2>
     *<p>A public String method.It returns the variable latLng.</p>
     * @return latLng This variable is used to pass attribute on getUrl() method.
     */
    public String getLatLng() {
        return latLng;
    }
    /**
     *<h2>A Method that pass a value to latLng variable.</h2>
     *<p>A public void method that pass the proper value on latLng attribute.</p>
     * @param latLng This variable is used pass a value to this.latLng variable.
     */
    public void setLatLng(String latLng) {
        this.latLng = latLng;
    }
}
