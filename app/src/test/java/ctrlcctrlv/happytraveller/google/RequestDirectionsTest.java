package ctrlcctrlv.happytraveller.google;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RequestDirectionsTest
{

    RequestDirections requestDirections;

    @Before
    public void setUp()
    {
        requestDirections = new RequestDirections();
    }

    @Test
    public void getDirectionsAssertEquals1() throws IOException
    {
        String expected = "{   \"geocoded_waypoints\" : [      {         \"geocoder_status\" : \"OK\",         \"partial_match\" : true,         \"place_id\" : \"EipMZW9mLiBNZXJhcmNo" +
                "aWFzIDgwLCBTZXJyZXMgNjIxIDI1LCBHcmVlY2UiGhIYChQKEgmZkKw8inGpFBFDKC3NmVsW_RBQ\",         \"types\" : [ \"street_address\" ]      },      {         \"geocoder_status\" :" +
                " \"OK\",         \"partial_match\" : true,         \"place_id\" : \"ChIJQYVXPCdyqRQREaPIvDwFEgw\",         \"types\" : [ \"street_address\" ]      }   ],   \"routes\" :" +
                " [      {         \"bounds\" : {            \"northeast\" : {               \"lat\" : 41.083956,               \"lng\" : 23.5438956            },            \"southwest" +
                "\" : {               \"lat\" : 41.0812238,               \"lng\" : 23.5418144            }         },         \"copyrights\" : \"Map data ©2018 Google\",         \"legs" +
                "\" : [            {               \"distance\" : {                  \"text\" : \"0.4 km\",                  \"value\" : 352               },               \"duration\" :" +
                " {                  \"text\" : \"4 mins\",                  \"value\" : 261               },               \"end_address\" : \"Leof. Merarchias 113, Serres 621 25, Greec" +
                "e\",               \"end_location\" : {                  \"lat\" : 41.0812238,                  \"lng\" : 23.5418144               },               \"start_address\" : \"" +
                "Leof. Merarchias 80, Serres 621 25, Greece\",               \"start_location\" : {                  \"lat\" : 41.083956,                  \"lng\" : 23.5438956            " +
                "   },               \"steps\" : [                  {                     \"distance\" : {                        \"text\" : \"0.4 km\",                        \"value\" : " +
                "352                     },                     \"duration\" : {                        \"text\" : \"4 mins\",                        \"value\" : 261                     }, " +
                "                    \"end_location\" : {                        \"lat\" : 41.0812238,                        \"lng\" : 23.5418144                     },                     " +
                "\"html_instructions\" : \"Head \\u003cb\\u003esouthwest\\u003c/b\\u003e on \\u003cb\\u003eLeof. Merarchias\\u003c/b\\u003e toward \\u003cb\\u003eKidonion\\u003c/b\\u003e\\u0" +
                "03cdiv style=\\\"font-size:0.9em\\\"\\u003eDestination will be on the left\\u003c/div\\u003e\",                     \"polyline\" : {                        \"points\" : \"wewyFk" +
                "lunCZT\\\\TTFZZ^T\\\\V^V\\\\V^V^T\\\\VLH^V\\\\V^V^V\\\\THNFD^T@@\"                     },                     \"start_location\" : {                        \"lat\" : 41.083956,  "+
                "                      \"lng\" : 23.5438956                     },                     \"travel_mode\" : \"WALKING\"                  }               ],               \"traffic_speed_entry\" : [],          " +
                "     \"via_waypoint\" : []            }         ],         \"overview_polyline\" : {            \"points\" : \"wewyFklunCx@j@TFZZ|@l@|@n@jBnA|B~A\\\\THNf@Z@@\"         },         \"summary" +
                "\" : \"Leof. Merarchias\",         \"warnings\" : [            \"Walking directions are in beta.    Use caution – This route may be missing sidewalks or pedestrian paths.\"     " +
                "    ],         \"waypoint_order\" : []      }   ],   \"status\" : \"OK\"}";

        assertEquals(expected,requestDirections.getDirections("https://maps.googleapis.com/maps/api/directions/json?&mode=walking&origin=Leof.Merarchias80,Serres62125,Greece,Serres,&destination=Leof.Merarchias113,Serres62125,Greece,SerresOK&key=AIzaSyDI0zKd22JBJEGco2k9Thg2CZWLLsWfq7k"));
    }

    @Test
    public void getDirectionsAssertEquals2() throws IOException
    {
        String expected = "{   \"geocoded_waypoints\" : [      {         \"geocoder_status\" : \"OK\",         \"partial_match\" : true,         \"place_id\" : \"EipMZW9mLiBNZXJhcmNoaWFzIDgwLC" +
                "BTZXJyZXMgNjIxIDI1LCBHcmVlY2UiGhIYChQKEgmZkKw8inGpFBFDKC3NmVsW_RBQ\",         \"types\" : [ \"street_address\" ]      },      {         \"geocoder_status\" : \"OK\",         \"" +
                "partial_match\" : true,         \"place_id\" : \"ChIJU18UYI5xqRQRkNatdnhwXzQ\",         \"types\" : [ \"street_address\" ]      }   ],   \"routes\" : [      {         \"bounds\"" +
                " : {            \"northeast\" : {               \"lat\" : 41.0901176,               \"lng\" : 23.5449148            },            \"southwest\" : {               \"lat\" : 41.0" +
                "839559,               \"lng\" : 23.5417062            }         },         \"copyrights\" : \"Map data ©2018 Google\",         \"legs\" : [            {               \"distanc" +
                "e\" : {                  \"text\" : \"0.8 km\",                  \"value\" : 813               },               \"duration\" : {                  \"text\" : \"11 mins\",       " +
                "           \"value\" : 666               },               \"end_address\" : \"Karaiskaki 1, Serres 621 21, Greece\",               \"end_location\" : {                  \"lat\"" +
                " : 41.0901176,                  \"lng\" : 23.5417062               },               \"start_address\" : \"Leof. Merarchias 80, Serres 621 25, Greece\",               \"start_lo" +
                "cation\" : {                  \"lat\" : 41.0839559,                  \"lng\" : 23.5438956               },               \"steps\" : [                  {                     \"" +
                "distance\" : {                        \"text\" : \"0.2 km\",                        \"value\" : 182                     },                     \"duration\" : {                 " +
                "       \"text\" : \"2 mins\",                        \"value\" : 140                     },                     \"end_location\" : {                        \"lat\" : 41.0854013" +
                "9999999,                        \"lng\" : 23.5449148                     },                     \"html_instructions\" : \"Head \\u003cb\\u003enortheast\\u003c/b\\u003e on \\u003" +
                "cb\\u003eLeof. Merarchias\\u003c/b\\u003e toward \\u003cb\\u003eMpekiari\\u003c/b\\u003e\",                     \"polyline\" : {                        \"points\" : \"wewyFklunC" +
                "[UaAi@aEiC\"                     },                     \"start_location\" : {                        \"lat\" : 41.0839559,                        \"lng\" : 23.5438956          " +
                "           },                     \"travel_mode\" : \"WALKING\"                  },                  {                     \"distance\" : {                        \"text\" : \"0" +
                ".4 km\",                        \"value\" : 372                     },                     \"duration\" : {                        \"text\" : \"5 mins\",                        " +
                "\"value\" : 291                     },                     \"end_location\" : {                        \"lat\" : 41.0882155,                        \"lng\" : 23.5425506         " +
                "            },                     \"html_instructions\" : \"Turn \\u003cb\\u003eleft\\u003c/b\\u003e onto \\u003cb\\u003eSpetson\\u003c/b\\u003e\",                     \"maneuv" +
                "er\" : \"turn-left\",                     \"polyline\" : {                        \"points\" : \"wnwyFurunC_BrA{@z@{@z@kAhAeAfA_@TaBx@gAh@\"                     },              " +
                "       \"start_location\" : {                        \"lat\" : 41.08540139999999,                        \"lng\" : 23.5449148                     },                     \"trave" +
                "l_mode\" : \"WALKING\"                  },                  {                     \"distance\" : {                        \"text\" : \"80 m\",                        \"value\" " +
                ": 80                     },                     \"duration\" : {                        \"text\" : \"2 mins\",                        \"value\" : 96                     },      " +
                "               \"end_location\" : {                        \"lat\" : 41.0888652,                        \"lng\" : 23.5421332                     },                     \"html_ins" +
                "tructions\" : \"Continue onto \\u003cb\\u003ePalaion Patron Germanoy\\u003c/b\\u003e\",                     \"polyline\" : {                        \"points\" : \"k`xyF}cunCCD}Bl" +
                "A\"                     },                     \"start_location\" : {                        \"lat\" : 41.0882155,                        \"lng\" : 23.5425506                    " +
                " },                     \"travel_mode\" : \"WALKING\"                  },                  {                     \"distance\" : {                        \"text\" : \"35 m\",     " +
                "                   \"value\" : 35                     },                     \"duration\" : {                        \"text\" : \"1 min\",                        \"value\" : 27  " +
                "                   },                     \"end_location\" : {                        \"lat\" : 41.0891492,                        \"lng\" : 23.5423245                     },    " +
                "                 \"html_instructions\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e toward \\u003cb\\u003eFilikis Etaireias\\u003c/b\\u003e\",                     \"maneuver\" :" +
                " \"turn-right\",                     \"polyline\" : {                        \"points\" : \"mdxyFiaunCw@e@\"                     },                     \"start_location\" : {    " +
                "                    \"lat\" : 41.0888652,                        \"lng\" : 23.5421332                     },                     \"travel_mode\" : \"WALKING\"                  }," +
                "                  {                     \"distance\" : {                        \"text\" : \"0.1 km\",                        \"value\" : 111                     },              " +
                "       \"duration\" : {                        \"text\" : \"1 min\",                        \"value\" : 85                     },                     \"end_location\" : {        " +
                "                \"lat\" : 41.090072,                        \"lng\" : 23.5420421                     },                     \"html_instructions\" : \"Turn \\u003cb\\u003eleft\\u0" +
                "03c/b\\u003e onto \\u003cb\\u003eFilikis Etaireias\\u003c/b\\u003e\",                     \"maneuver\" : \"turn-left\",                     \"polyline\" : {                      " +
                "  \"points\" : \"efxyFobunCAJEJEDIBi@B{@Fy@H\"                     },                     \"start_location\" : {                        \"lat\" : 41.0891492,                     " +
                "   \"lng\" : 23.5423245                     },                     \"travel_mode\" : \"WALKING\"                  },                  {                     \"distance\" : {      " +
                "                  \"text\" : \"19 m\",                        \"value\" : 19                     },                     \"duration\" : {                        \"text\" : \"1 min" +
                "\",                        \"value\" : 14                     },                     \"end_location\" : {                        \"lat\" : 41.0900303,                        \"ln" +
                "g\" : 23.5418186                     },                     \"html_instructions\" : \"Turn \\u003cb\\u003eleft\\u003c/b\\u003e onto \\u003cb\\u003eGrigoriou Rakitzi\\u003c/b\\u00" +
                "3e\",                     \"maneuver\" : \"turn-left\",                     \"polyline\" : {                        \"points\" : \"}kxyFw`unCFj@\"                     },         " +
                "            \"start_location\" : {                        \"lat\" : 41.090072,                        \"lng\" : 23.5420421                     },                     \"travel_mod" +
                "e\" : \"WALKING\"                  },                  {                     \"distance\" : {                        \"text\" : \"14 m\",                        \"value\" : 14   " +
                "                  },                     \"duration\" : {                        \"text\" : \"1 min\",                        \"value\" : 13                     },               " +
                "      \"end_location\" : {                        \"lat\" : 41.0901176,                        \"lng\" : 23.5417062                     },                     \"html_instructions" +
                "\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e onto \\u003cb\\u003eKaraiskaki\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003eDestination will be on the left\\u00" +
                "3c/div\\u003e\",                     \"maneuver\" : \"turn-right\",                     \"polyline\" : {                        \"points\" : \"ukxyFk_unCQT\"                     " +
                "},                     \"start_location\" : {                        \"lat\" : 41.0900303,                        \"lng\" : 23.5418186                     },                     " +
                "\"travel_mode\" : \"WALKING\"                  }               ],               \"traffic_speed_entry\" : [],               \"via_waypoint\" : []            }         ],         " +
                "\"overview_polyline\" : {            \"points\" : \"wewyFklunC}A_AaEiC_BrAwBvBqCpCaCnAkAn@}BlAw@e@GVOHeBJy@HFj@QT\"         },         \"summary\" : \"Spetson\",         \"warnin" +
                "gs\" : [            \"Walking directions are in beta.    Use caution – This route may be missing sidewalks or pedestrian paths.\"         ],         \"waypoint_order\" : []      " +
                "}   ],   \"status\" : \"OK\"}";


        assertNotEquals(expected,requestDirections.getDirections("https://maps.googleapis.com/maps/api/directions/json?&mode=walking&origin=Leof.Merarchias80,Serres62125,Greece,Serres,&destination=Karaiskaki1,Serres62121,Greece,SerresOK&key=AIzaSyDI0zKd22JBJEGco2k9Thg2CZWLLsWfq7k"));
    }

    @Test
    public void getDirectionsAssertEquals3() throws IOException
    {
        String expected = "";

        assertEquals(expected,requestDirections.getDirections("https://maps.googleapis.com/maps/api/directions/json?&mode=walking&origin=Leof. Merarchias 80, Serres 621 25, Greece,Serre" +
                "s,&destination=Flemingk 6, Serres 621 25, Greece,SerresOK&key=AIzaSyDI0zKd22JBJEGco2k9Thg2CZWLLsWfq7k"));
    }


    @Test
    public void  getDirectionsAssertNotEquals1() throws IOException
    {
        String expected ="{   \"geocoded_waypoints\" : [      {         \"geocoder_status\" : \"OK\",         \"partial_match\" : true,         \"place_id\" : \"EipMZW9mLiBNZXJhcmNoaWFzIDgwLCBTZ" +
                "XJyZXMgNjIxIDI1LCBHcmVlY2UiGhIYChQKEgmZkKw8inGpFBFDKC3NmVsW_RBQ\",         \"types\" : [ \"street_address\" ]      },      {         \"geocoder_status\" : \"OK\",         \"part" +
                "ial_match\" : true,         \"place_id\" : \"ChIJ_7CFi-1xqRQRnPkNnpuM1Gc\",         \"types\" : [ \"establishment\", \"point_of_interest\" ]      }   ],   \"routes\" : [      { " +
                "        \"bounds\" : {            \"northeast\" : {               \"lat\" : 41.0902875,               \"lng\" : 23.5532652            },            \"southwest\" : {            " +
                "   \"lat\" : 41.0839559,               \"lng\" : 23.5438956            }         },         \"copyrights\" : \"Map data ©2018 Google\",         \"legs\" : [            {        " +
                "       \"distance\" : {                  \"text\" : \"1.2 km\",                  \"value\" : 1193               },               \"duration\" : {                  \"text\" : \"1" +
                "6 mins\",                  \"value\" : 938               },               \"end_address\" : \"Πλατεία Εμπορίου 15, Serres 621 22, Greece\",               \"end_location\" : {   " +
                "               \"lat\" : 41.0901924,                  \"lng\" : 23.5532652               },               \"start_address\" : \"Leof. Merarchias 80, Serres 621 25, Greece\",    " +
                "           \"start_location\" : {                  \"lat\" : 41.0839559,                  \"lng\" : 23.5438956               },               \"steps\" : [                  {   " +
                "                  \"distance\" : {                        \"text\" : \"0.9 km\",                        \"value\" : 872                     },                     \"duration\" :" +
                " {                        \"text\" : \"11 mins\",                        \"value\" : 671                     },                     \"end_location\" : {                        \"" +
                "lat\" : 41.0902875,                        \"lng\" : 23.5496208                     },                     \"html_instructions\" : \"Head \\u003cb\\u003enortheast\\u003c/b\\u003" +
                "e on \\u003cb\\u003eLeof. Merarchias\\u003c/b\\u003e toward \\u003cb\\u003eMpekiari\\u003c/b\\u003e\",                     \"polyline\" : {                        \"points\" : \"" +
                "wewyFklunC[UaAi@aEiCeAeBqA}BaB}CgAyB}@yAcBoAECYQo@_@QIg@Q}@]m@UwFaC\"                     },                     \"start_location\" : {                        \"lat\" : 41.08395" +
                "59,                        \"lng\" : 23.5438956                     },                     \"travel_mode\" : \"WALKING\"                  },                  {                  " +
                "   \"distance\" : {                        \"text\" : \"0.3 km\",                        \"value\" : 306                     },                     \"duration\" : {             " +
                "           \"text\" : \"4 mins\",                        \"value\" : 246                     },                     \"end_location\" : {                        \"lat\" : 41.0900" +
                "586,                        \"lng\" : 23.5532487                     },                     \"html_instructions\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e onto \\u003cb\\u0" +
                "03eDionisiou Solomou\\u003c/b\\u003e\",                     \"maneuver\" : \"turn-right\",                     \"polyline\" : {                        \"points\" : \"imxyFcpvnC@" +
                "}@LcCDu@H}BBoA@OBe@@]Bg@@QBu@Ag@Ec@\"                     },                     \"start_location\" : {                        \"lat\" : 41.0902875,                        \"lng" +
                "\" : 23.5496208                     },                     \"travel_mode\" : \"WALKING\"                  },                  {                     \"distance\" : {             " +
                "           \"text\" : \"15 m\",                        \"value\" : 15                     },                     \"duration\" : {                        \"text\" : \"1 min\",   " +
                "                     \"value\" : 21                     },                     \"end_location\" : {                        \"lat\" : 41.0901924,                        \"lng\" :" +
                " 23.5532652                     },                     \"html_instructions\" : \"Turn \\u003cb\\u003eleft\\u003c/b\\u003e onto \\u003cb\\u003eArchiepiskopou Makariou\\u003c/b\\u" +
                "003e\",                     \"maneuver\" : \"turn-le";

        assertNotEquals(expected,requestDirections.getDirections("https://maps.googleapis.com/maps/api/directions/json?&mode=walking&origin=Leof.Merarchias80,Serres62125,Greece,Serres,&destination=ΠλατείαΕμπορίου15,Serres62122,Greece,SerresOK&key=AIzaSyDI0zKd22JBJEGco2k9Thg2CZWLLsWfq7k"));
    }

    @Test
    public void  getDirectionsAssertNotEquals2() throws IOException
    {
        String expected ="{   \"geocoded_waypoints\" : [      {         \"geocoder_status\" : \"OK\",         \"partial_match\" : true,         \"place_id\" : \"EipMZW9mLiBNZXJhcmNoaWFzIDgwLCBTZ" +
                "XJyZXMgNjIxIDI1LCBHcmVlY2UiGhIYChQKEgmZkKw8inGpFBFDKC3NmVsW_RBQ\",         \"types\" : [ \"street_address\" ]      },      {         \"geocoder_status\" : \"OK\",         \"part" +
                "ial_match\" : true,         \"place_id\" : \"ChIJ_7CFi-1xqRQRnPkNnpuM1Gc\",         \"types\" : [ \"establishment\", \"point_of_interest\" ]      }   ],   \"routes\" : [      { " +
                "        \"bounds\" : {            \"northeast\" : {               \"lat\" : 41.0902875,               \"lng\" : 23.5532652            },            \"southwest\" : {         " +
                "      \"lat\" : 41.0839559,               \"lng\" : 23.5438956            }         },         \"copyrights\" : \"Map data ©2018 Google\",         \"legs\" : [            {  " +
                "             \"distance\" : {                  \"text\" : \"1.2 km\",                  \"value\" : 1193               },               \"duration\" : {                  \"tex" +
                "t\" : \"16 mins\",                  \"value\" : 938               },               \"end_address\" : \"Πλατεία Εμπορίου 15, Serres 621 22, Greece\",               \"end_locat" +
                "ion\" : {                  \"lat\" : 41.0901924,                  \"lng\" : 23.5532652               },               \"start_address\" : \"Leof. Merarchias 80, Serres 621 25" +
                ", Greece\",               \"start_location\" : {                  \"lat\" : 41.0839559,                  \"lng\" : 23.5438956               },               \"steps\" : [    " +
                "              {                     \"distance\" : {                        \"text\" : \"0.9 km\",                        \"value\" : 872                     },              " +
                "       \"duration\" : {                        \"text\" : \"11 mins\",                        \"value\" : 671                     },                     \"end_location\" : { " +
                "                       \"lat\" : 41.0902875,                        \"lng\" : 23.5496208                     },                     \"html_instructions\" : \"Head \\u003cb\\u" +
                "003enortheast\\u003c/b\\u003e on \\u003cb\\u003eLeof. Merarchias\\u003c/b\\u003e toward \\u003cb\\u003eMpekiari\\u003c/b\\u003e\",                     \"polyline\" : {       " +
                "                 \"points\" : \"wewyFklunC[UaAi@aEiCeAeBqA}BaB}CgAyB}@yAcBoAECYQo@_@QIg@Q}@]m@UwFaC\"                     },                     \"start_location\" : {       " +
                "                 \"lat\" : 41.0839559,                        \"lng\" : 23.5438956                     },                     \"travel_mode\" : \"WALKING\"                  }" +
                ",                  {                     \"distance\" : {                        \"text\" : \"0.3 km\",                        \"value\" : 306                     },         " +
                "            \"duration\" : {                        \"text\" : \"4 mins\",                        \"value\" : 246                     },                     \"end_location\" " +
                ": {                        \"lat\" : 41.0900586,                        \"lng\" : 23.5532487                     },                     \"html_instructions\" : \"Turn \\u003c" +
                "b\\u003eright\\u003c/b\\u003e onto \\u003cb\\u003eDionisiou Solomou\\u003c/b\\u003e\",                     \"maneuver\" : \"turn-right\",                     \"polyline\" : {" +
                "                        \"points\" : \"imxyFcpvnC@}@LcCDu@H}BBoA@OBe@@]Bg@@QBu@Ag@Ec@\"                     },                     \"start_location\" : {                     " +
                "   \"lat\" : 41.0902875,                        \"lng\" : 23.5496208                     },                     \"travel_mode\" : \"WALKING\"                  },             " +
                "     {                     \"distance\" : {                        \"text\" : \"15 m\",                        \"value\" : 15                     },                     \"dur" +
                "ation\" : {                        \"text\" : \"1 min\",                        \"value\" : 21                     },                     \"end_location\" : {                " +
                "        \"lat\" : 41.0901924,                        \"lng\" : 23.5532652                     },                     \"html_instructions\" : \"Turn \\u003cb\\u003eleft\\u003c" +
                "/b\\u003e onto \\u003cb\\u003eArchiepiskopou Makariou\\u003c/b\\u003e\",                     \"maneuver\" : \"turn-le";

        assertNotEquals(expected,requestDirections.getDirections("https://maps.googleapis.com/maps/api/directions/json?&mode=walking&origin=Leof. Merarchias 80, Serres 621 25, Greece,Serres,&destination=Flemingk 6, Serres 621 25, Greece,SerresOK&key=AIzaSyDI0zKd22JBJEGco2k9Thg2CZWLLsWfq7k"));
    }
}
