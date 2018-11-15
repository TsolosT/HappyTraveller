package ctrlcctrlv.happytraveller.model;


import android.widget.ImageView;

public class PlaceData {


    String name;
    String address;
    String info;
  //  ImageView[] images;
    // TODO: 15/11/2018  add more variable like phone number,web url,img ,etc


    public PlaceData(String name, String address, String info) {
        this.name = name;
        this.address = address;
        this.info = info;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
