package ctrlcctrlv.happytraveller.model;


import android.widget.ImageView;

public class PlaceData {


    String name;
    String address;
    String info;
    String category;
  //  ImageView[] images;
    // TODO: 15/11/2018  add more variable like phone number,web url,img ,etc


    public PlaceData(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public PlaceData()
    {
        this.name = "";
        this.address = "";
        this.info = "";
        this.category="";
    }
    public String getCategory() { return category;
    }

    public void setCategory(String category) { this.category = category;  }
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

    public void setNull() {
        this.name="";
        this.address="";
    }
}
