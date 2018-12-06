package ctrlcctrlv.happytraveller.jsonParser;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import ctrlcctrlv.happytraveller.model.PlaceData;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



@RunWith(MockitoJUnitRunner.class)
public class PlaceParserTest {

    private String jsonReply;
    private ArrayList<PlaceData> mockArray;
    private  PlaceParser parser;
    private PlaceData mockPlace;
    private String plusCode;

    @Before
    public void setUp() {

           jsonReply="{\n" +
                   "   \"html_attributions\" : [],\n" +
                   "   \"results\" : [\n" +
                   "      {\n" +
                   "         \"geometry\" : {\n" +
                   "            \"location\" : {\n" +
                   "               \"lat\" : 41.0909417,\n" +
                   "               \"lng\" : 23.549367\n" +
                   "            },\n" +
                   "            \"viewport\" : {\n" +
                   "               \"northeast\" : {\n" +
                   "                  \"lat\" : 41.09217073029149,\n" +
                   "                  \"lng\" : 23.5509312802915\n" +
                   "               },\n" +
                   "               \"southwest\" : {\n" +
                   "                  \"lat\" : 41.08947276970849,\n" +
                   "                  \"lng\" : 23.54823331970849\n" +
                   "               }\n" +
                   "            }\n" +
                   "         },\n" +
                   "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/museum-71.png\",\n" +
                   "         \"id\" : \"bc6ea3cd688cfa05ac606c4a5c9fedf121bfc725\",\n" +
                   "         \"name\" : \"Αρχαιολογικό Μουσείο Μπεζεστένι\",\n" +
                   "         \"opening_hours\" : {\n" +
                   "            \"open_now\" : false\n" +
                   "         },\n" +
                   "         \"photos\" : [\n" +
                   "            {\n" +
                   "               \"height\" : 4140,\n" +
                   "               \"html_attributions\" : [\n" +
                   "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/100595518368873677708/photos\\\"\\u003eAlexandra Andreou\\u003c/a\\u003e\"\n" +
                   "               ],\n" +
                   "               \"photo_reference\" : \"CmRaAAAANBhxVcFSFT5tesgpR_uqHqtos3o5IykDJKk0i5Qdu6l0D664kJSy7i3PBBOdaIWeKEZlteL2kEhWuuoS-Xcb-_ztpsymySmUjj7OcRlYcoeLRHWEKNbW6FswsHIjl-G2EhBUPMGsisTuPZkiKovRrTUCGhRIh6VVRrd0bsMnAyOhBYsdd0xykA\",\n" +
                   "               \"width\" : 5520\n" +
                   "            }\n" +
                   "         ],\n" +
                   "         \"place_id\" : \"ChIJheFRVO1xqRQReski8ouDQOo\",\n" +
                   "         \"plus_code\" : {\n" +
                   "            \"compound_code\" : \"3GRX+9P Σέρρες, Ελλάδα\",\n" +
                   "            \"global_code\" : \"8GH53GRX+9P\"\n" +
                   "         },\n" +
                   "         \"rating\" : 4.2,\n" +
                   "         \"reference\" : \"ChIJheFRVO1xqRQReski8ouDQOo\",\n" +
                   "         \"scope\" : \"GOOGLE\",\n" +
                   "         \"types\" : [ \"museum\", \"point_of_interest\", \"establishment\" ],\n" +
                   "         \"vicinity\" : \"Λεωφόρος Μεραρχίας 858, Σέρρες\"\n" +
                   "      },],\n" +
                   "   \"status\" : \"OK\"\n" +
                   "}";
           mockArray=mock(ArrayList.class);
           mockPlace=mock(PlaceData.class);
           when(mockArray.get(0)).thenReturn(mockPlace);
           when(mockArray.get(0).getName()).thenReturn("Αρχαιολογικό Μουσείο Μπεζεστένι");
           when(mockArray.get(0).getAddress()).thenReturn("Λεωφόρος Μεραρχίας 858, Σέρρες");
           when(mockArray.size()).thenReturn(1);
           parser=new PlaceParser();
            plusCode="{\"compound_code\":\"3HV3+JX Serres, Greece\",\"global_code\":\"8GH53HV3+JX\"}";

    }
    @Test
    public void doPlaceParseGetNameTest()
    {
        ArrayList<PlaceData> testPlaceDataArray=new ArrayList<>();
        testPlaceDataArray= parser.parseGoogleParse(jsonReply);
        assertEquals(mockArray.get(0).getName(),testPlaceDataArray.get(0).getName());
    }
    @Test
    public void doPlaceParseGetAddressTest()
    {
        ArrayList<PlaceData> testPlaceDataArray=new ArrayList<>();
        testPlaceDataArray= parser.parseGoogleParse(jsonReply);
        assertEquals(mockArray.get(0).getAddress(),testPlaceDataArray.get(0).getAddress());
    }
    @Test
    public void doPlaceParseSameSizeTest()
    {
        ArrayList<PlaceData> testPlaceDataArray=new ArrayList<>();
        testPlaceDataArray= parser.parseGoogleParse(jsonReply);
        assertEquals(mockArray.size(),testPlaceDataArray.size());
    }

    //add failed test px assertNotEqual etc
    //add test on get latlng parse klp
    @Test
    public void checkSpliterTestCorrect()
    {
        String cityAndCountry=parser.getCompountCode(plusCode);
        String expected="Serres,Greece";
        assertEquals(expected,cityAndCountry);
    }
    @Test
    public void checkSpliterTestWrong()
    {
        String cityAndCountry=parser.getCompountCode(plusCode);
        String expected="Serres, Greece";
        assertNotEquals(expected,cityAndCountry);
    }
    @After
    public void tearDown()
    {
        jsonReply=null;
       mockArray=null;
    }


}