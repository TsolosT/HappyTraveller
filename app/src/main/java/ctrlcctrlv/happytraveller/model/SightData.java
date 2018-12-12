package ctrlcctrlv.happytraveller.model;

import java.util.ArrayList;

public class SightData {

    String sightName;
    String dist_for_place = null;
    String time_for_place = null;


    public SightData(String sightName, String dist_for_place, String time_for_place)
    {
        this.sightName=sightName;
        this.dist_for_place=dist_for_place;
        this.time_for_place=time_for_place;
    }

    public void  setSightName(String sightName)
    {
        this.sightName=sightName;
    }

    public void setDist_for_place(String dist_for_place)
    {
        this.dist_for_place=dist_for_place;
    }

    public void setTime_for_place(String time_for_place)
    {
        this.time_for_place=time_for_place;
    }

    public String getSightName() {return sightName;}

    public String getDist_for_place() { return dist_for_place;}

    public String getTime_for_place() { return time_for_place;}

}
