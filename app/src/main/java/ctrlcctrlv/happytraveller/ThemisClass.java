package ctrlcctrlv.happytraveller;

import com.google.android.gms.maps.model.LatLng;

import java.util.Random;


// TODO: 12/12/2018 #themis delete the whole class 
public class ThemisClass
{
    public int[][] getDistanceAndTime(LatLng onePoint , LatLng otherPoint) //no specific reason for those 2 variables just for themi to see and paste it
    {
        Random rand = new Random();
        int [] [] returnValues = new int [1][2];


        returnValues [0][0] =  rand.nextInt(500) + 20; //min 20 , max 500
        returnValues [0][1] = rand.nextInt(20) + 5;


        return returnValues;
    }
}
