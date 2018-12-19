package ctrlcctrlv.happytraveller.model;


public class PlacePhoto
{


    private String width;
    private String photoReference;
    private String imgUrl;

    public PlacePhoto()
    {
        this.width="";
        this.photoReference="";
        this.imgUrl="https://maps.googleapis.com/maps/api/place/photo?" +
                "maxwidth=146" +//this.width
                "&photoreference=" +this.photoReference+
                "&key=AIzaSyDI0zKd22JBJEGco2k9Thg2CZWLLsWfq7k";
    }
    public PlacePhoto(String imgReference)
    {
        this.width="";
        this.photoReference=imgReference;
        this.imgUrl="https://maps.googleapis.com/maps/api/place/photo?" +
                "maxwidth=146" +//this.width
                "&photoreference=" +this.photoReference+
                "&key=AIzaSyDI0zKd22JBJEGco2k9Thg2CZWLLsWfq7k";
    }
    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }
    public String getImgUrl() {
        return imgUrl;
    }


}
