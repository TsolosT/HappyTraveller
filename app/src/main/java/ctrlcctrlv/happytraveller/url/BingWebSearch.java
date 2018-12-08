package ctrlcctrlv.happytraveller.url;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ctrlcctrlv.happytraveller.activities.DetailsActivity;

public class BingWebSearch {
    static String host = "https://api.cognitive.microsoft.com";
    static String path = "/bingcustomsearch/v7.0/search";
    static String subscriptionKey = "71740daa-d800-43b6-9c44-d0ab4f9f68d6";
    static String searchTerm = null;  // Replace with search term specific to your search scenario.

    public BingWebSearch(){

    }

    public String  subKeyBing() {
        return  subscriptionKey;
    }

    public String getSearchTerm(){
        return searchTerm;
    }
    public void setSearchTerm(String newSearchTerm) {
        searchTerm = newSearchTerm;
    }
    public static SearchResults SearchWeb (String searchQuery) throws Exception {
        // construct URL of search request (endpoint + query string)

        DetailsActivity.setSearchTerm(searchTerm);
        DetailsActivity.getSearchTerm();

        URL url = new URL(host + path + "?q=" +  URLEncoder.encode(searchTerm, "UTF-8"));
        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);

        // receive JSON body
        InputStream in = connection.getInputStream();
        InputStream stream =new BufferedInputStream(in);
        String response = new Scanner(stream).useDelimiter("\\A").next();

        // construct result object for return
        SearchResults results = new SearchResults(new HashMap<String, String>(), response);

        // extract Bing-related HTTP headers
        Map<String, List<String>> headers = connection.getHeaderFields();
        for (String header : headers.keySet()) {
            if (header == null) continue;      // may have null key
            if (header.startsWith("BingAPIs-") || header.startsWith("X-MSEdge-")) {
                results.relevantHeaders.put(header, headers.get(header).get(0));
            }
        }

        stream.close();
        return results;
    }

    // pretty-printer for JSON; uses GSON parser to parse and re-serialize
    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(json_text).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }

    public static void main (String[] args) {
        if (subscriptionKey.length() != 32) {
            System.out.println("Invalid Custom Search subscription key!");
            System.out.println("Please paste yours into the source code.");
            System.exit(1);
        }

        try {
            System.out.println("Searching your slice of the Web for: " + searchTerm);

            SearchResults result = SearchWeb(searchTerm);

            System.out.println("\nRelevant HTTP Headers:\n");
            for (String header : result.relevantHeaders.keySet())
                System.out.println(header + ": " + result.relevantHeaders.get(header));

            System.out.println("\nJSON Response:\n");
            System.out.println(prettify(result.jsonResponse));
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            System.exit(1);
        }
    }
}

