package org.example;
import org.json.JSONObject;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        URL url = null;
        HttpURLConnection connection = null;
        int responseCode  = 0;
        String urlString = "http://api.zippopotam.us/us/33162";
        try{
            url = new URL(urlString);
        }catch(MalformedURLException e){
            System.out.println("Problem in url");
        }

        try {
            connection = (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();
        }catch(IOException e){
            System.out.println("connection problem");
        }

        if(responseCode == 200){
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder apiData = new StringBuilder();
            String readline = null;
            while((readline = in.readLine()) != null){
                apiData.append(readline);
            }
            in.close();

            JSONObject jsonAPIResponse = new JSONObject(apiData.toString());
            System.out.println(jsonAPIResponse.toString());

        }else{
            System.out.println("API call could not be made");
        }
    }
}