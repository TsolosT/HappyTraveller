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
    public PlaceData(String name, String address,PlacePhoto photo) {
        this.name = name;
        this.address = address;
        this.defaultImg = photo;
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

}
