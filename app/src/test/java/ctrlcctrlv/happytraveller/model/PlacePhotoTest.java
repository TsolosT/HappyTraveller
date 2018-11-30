package ctrlcctrlv.happytraveller.model;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlacePhotoTest {

    //variables
    private PlacePhoto photoWithRef,photoNoRef;

    @Before
    public void setUp()
    {
        photoWithRef=new PlacePhoto("abcdefg");
        photoNoRef=new PlacePhoto();

    }

    @Test
    public void getPhotoRefTest()
    {
        String photo_ref=photoWithRef.getPhotoReference();
        String expected="abcdefg";
        assertEquals(expected,photo_ref);
    }

    @Test
    public void setPhotoRefTest()
    {
        photoNoRef.setPhotoReference("abcabcabc");
        String expected="abcabcabc";
        assertEquals(expected,photoNoRef.getPhotoReference());

    }

    @Test
    public void getPhotoUrlTest()
    {
        String photoUrl="https://maps.googleapis.com/maps/api/place/photo?maxwidth=146&photoreference" +
                "=abcdefg&key=AIzaSyDI0zKd22JBJEGco2k9Thg2CZWLLsWfq7k";
        assertEquals(photoUrl,photoWithRef.getImgUrl());
    }

    @Test
    public void getNullUrlImg()
    {   String imgUrl="https://maps.googleapis.com/maps/api/place/photo?maxwidth=146&photoreference" +
            "=&key=AIzaSyDI0zKd22JBJEGco2k9Thg2CZWLLsWfq7k";
        photoNoRef.setPhotoReference(null);
        assertEquals(imgUrl,photoNoRef.getImgUrl());
    }

    @After
    public void tearDown()
    {
            photoNoRef=null;
            photoWithRef=null;
    }


}