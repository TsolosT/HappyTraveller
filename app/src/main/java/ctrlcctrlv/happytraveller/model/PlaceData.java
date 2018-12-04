package ctrlcctrlv.happytraveller.model;


import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class PlaceData
{
    String name;
    String address;
    String info;
    LatLng coordinates;
    String pinsIcon;
    Double latitude;
    Double longitude;



    int id;


    String cityCountry;


    ArrayList<PlacePhoto> placePhotos;
    PlacePhoto defaultImg;
    // TODO: 15/11/2018  add more variable like phone number,web url,img ,etc


    public PlaceData(String name, String address,ArrayList<PlacePhoto> placePhotos)
    {
        this.name = name;
        this.address = address;
        this.placePhotos=placePhotos;
    }
    public PlaceData(String name, String address)
    {
        this.name = name;
        this.address = address;
    }
    public PlaceData(String name, String address,PlacePhoto photo,Double latitude,Double longitude,String cityCountry,int id)
    {
        this.name = name;
        this.address = address;
        this.defaultImg = photo;
        this.latitude=latitude;
        this.longitude=longitude;
        this.cityCountry=cityCountry;
        this.id=id;
    }
    public PlaceData()
    {
        this.name = "";
        this.address = "";
        this.info = "";
        this.coordinates = null;
        this.pinsIcon = null ;
        this.placePhotos=new ArrayList<PlacePhoto>();
        this.defaultImg=new PlacePhoto();
        this.cityCountry="";


    }

    public void setCoordinates(LatLng coordinates) {this.coordinates = coordinates;}

    public LatLng getCoordinates() {return coordinates;}

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public ArrayList<PlacePhoto> getPlacePhotos() {
        return placePhotos;
    }

    public void setPlacePhotos(ArrayList<PlacePhoto> placePhotos) {
        this.placePhotos = placePhotos;
    }
    public PlacePhoto getDefaultImg() {
        return defaultImg;
    }

    public void setDefaultImg(PlacePhoto defaultImg) {
        this.defaultImg = defaultImg;
    }
    public void setLatitude() { this.latitude=latitude;}

    public Double getLatitude() { return latitude;}

    public void setLongitude() { this.longitude=longitude;}

    public Double getLongitude() { return longitude;}

    public String getCityCountry() {
        return cityCountry;
    }

    public void setCityCountry(String cityCountry) {
        this.cityCountry = cityCountry;
    }

    public int getId() {return id; }

    public void setId(int id) {
        this.id = id;
    }

}
