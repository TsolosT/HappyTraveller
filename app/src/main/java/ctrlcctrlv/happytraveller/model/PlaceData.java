package ctrlcctrlv.happytraveller.model;


import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;



/**
 *<b>This class is a model for Place Api objects.
 *This class create,initialize the variables that
 * will be used to hold data from Place Api call.
 *
 *
 * @since 15 Nov 2018
 */
public class PlaceData implements Comparable<PlaceData>
{
    String name;
    String address;
    LatLng coordinates;
    String pinsIcon;
    Double latitude;
    Double longitude;
    String cityCountry;
    // TODO: 12/12/2018 #themis values to fill with
    int distance;
    int timeTillArrival;
    int id;
    ArrayList<PlacePhoto> placePhotos;
    PlacePhoto defaultImg;

    /**
     * The main constructor <b>public PlaceData(String name,String address)</b>.
     * Initialize the two basic variables that used on test
     * propose.
     * @param name A String variable that is used to pass value to the this.name variable.
     * @param address A String variable that is used to pass value to the this.address variable.
     */
    public PlaceData(String name, String address)
    {
        this.name = name;
        this.address = address;
    }
    /**
     * The main constructor of PlaceData </b>.
     * Initialize the basics variables that used
     * the PlaceParser and later on ListView  fragment to display some information to the user.
     * @param name A String variable that is used to pass value to the this.name variable.
     * @param address A String variable that is used to pass value to the this.address variable.
     * @param photo A PlacePhoto object  that is used to pass value to the this.defaultImg object.
     * @param latitude
     * @param longitude
     * @param cityCountry A String variable that is used to pass value to the this.cityCountry variable.
     */
    public PlaceData(String name, String address,PlacePhoto photo,Double latitude,Double longitude,String cityCountry)
    {
        this.name = name;
        this.address = address;
        this.defaultImg = photo;
        this.latitude=latitude;
        this.longitude=longitude;
        this.cityCountry=cityCountry;
        this.id=id;
    }
    /**
     * The main constructor <b>public PlacePhoto()</b>.
     * Initialize the variables that used on test
     * propose with null values.
     */
    public PlaceData()
    {
        this.name = "";
        this.address = "";
        this.coordinates = null;
        this.pinsIcon = null ;
        this.placePhotos=new ArrayList<PlacePhoto>();
        this.defaultImg=new PlacePhoto();
        this.cityCountry="";
    }

    public void setCoordinates(LatLng coordinates) {this.coordinates = coordinates;}

    public LatLng getCoordinates() {return coordinates;}

    /**
     *A Method that return the name variable.
     * A public String method.
     * @return name This variable is used as attribute on place object.
     */
    public String getName() {
        return name;
    }
    /**
     *A Method that pass a value to name variable.
     * A public void method that pass the proper value on name attribute.
     * @param name This variable is used pass a value to this.name variable.
     */
    public void setName(String name) {this.name = name;}

    /**
     *A Method that return the address variable.
     * A public String method.
     * @return address This variable is used as attribute on place object.
     */
    public String getAddress() {
        return address;
    }
    /**
     *A Method that pass a value to address variable.
     * A public void method that pass the proper value on address attribute.
     * @param address This variable is used pass a value to this.address variable.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *A Method that return the defaultImg variable.
     * A public PlacePhoto method.
     * @return defaultImg This variable is used as attribute on place object image.
     */
    public PlacePhoto getDefaultImg() {
        return defaultImg;
    }

    public Double getLatitude() { return latitude;}

    public Double getLongitude() { return longitude;}

    /**
     *A Method that return the cityCountry variable.
     * A public String method.
     * @return cityCountry This variable is used as attribute on place object.
     */
    public String getCityCountry() {
        return cityCountry;
    }
    /**
     *A Method that pass a value to cityCountry variable.
     * A public void method that pass the proper value on cityCountry attribute.
     * @param cityCountry This variable is used pass a value to this.cityCountry variable.
     */
    public void setCityCountry(String cityCountry) {
        this.cityCountry = cityCountry;
    }
    /**
     *A Method that return the id variable.
     * A public int method.
     * @return id This variable is used as attribute on place object.
     */
    public int getId() {return id; }
    /**
     *A Method that pass a value to id variable.
     * A public void method that pass the proper value on id attribute.
     * @param id This variable is int and is  used pass a value to this.id variable.
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(PlaceData placeData)
    {
        return (this.timeTillArrival - placeData.timeTillArrival);
    }
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setTimeTillArrival(int timeTillArrival) {
        this.timeTillArrival = timeTillArrival;
    }

    public int getTimeTillArrival()
    {
        return timeTillArrival;
    }
}
