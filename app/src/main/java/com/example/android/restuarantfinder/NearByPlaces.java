package com.example.android.restuarantfinder;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.ListView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NearByPlaces extends AsyncTask<Object,String,String>{

    GoogleMap mMap;
    String url;
    InputStream is;
    BufferedReader bufferedReader;
    StringBuilder stringBuilder;
    String data;
    String restaurant;
    ArrayList<String> items;
    static JSONArray jsonArray;
    @Override
    protected String doInBackground(Object... objects) {

        mMap=(GoogleMap)objects[0];
        url=(String)objects[1];
        try
        {
            URL myurl=new URL(url);
            HttpURLConnection httpURLConnection=(HttpURLConnection)myurl.openConnection();
            httpURLConnection.connect();
            is=httpURLConnection.getInputStream();
            bufferedReader =new BufferedReader(new InputStreamReader(is));
            String line="";
            Log.d("NearByPlaces", "Reached safely AFTER LINK");
            stringBuilder=new StringBuilder();
            while((line=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line);

            }
            data=stringBuilder.toString();

        }catch(Exception e)
        {

        }
        Log.d("NearByPlaces", "Reached safely AFTER LINK LSTLY");
        return data;
    }


    public JSONArray find() {
        return jsonArray;
    }

    @Override
    protected void onPostExecute(String s) {
            try
            {
                int i;
                JSONObject parentObject=new JSONObject(s);
                 jsonArray=parentObject.getJSONArray("results");
                Log.d("NearByPlaces", "ging in");
                if(jsonArray.length()==0)
                    Log.d("NearByPlaces", "ging in1111");
                for(i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    JSONObject locationObj=jsonObject.getJSONObject("geometry").getJSONObject("location");
                    Log.d("NearByPlaces", "ging in11");
                    String longitude=null;
                    String lat=locationObj.getString("lat");
                    if(locationObj.has("lng")) {
                        Log.d("NearByPlaces", "ging in11 nside longitude");
                        longitude = locationObj.getString("lng");
                        LatLng latLng=new LatLng(Double.parseDouble(lat),Double.parseDouble(longitude));
                        JSONObject nameObject=jsonArray.getJSONObject(i);
                        restaurant=nameObject.getString("name");
                        String vic=nameObject.getString("vicinity");
                        Log.d("NearByPlaces",restaurant);
                        MarkerOptions options=new MarkerOptions()
                                .position(latLng)
                                .title(restaurant);
                        mMap.addMarker(options);
                        Log.d("NearByPlaces",restaurant);
                    }
                }
                Log.d("NearByPlaces", "Reached safely AFTER LINK LSTLY the last step");


            }catch(JSONException e)
            {
                e.printStackTrace();
            }
    }
}
