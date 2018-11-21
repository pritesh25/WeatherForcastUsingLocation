package com.example.user.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ImageView imgView;

    //https://openweathermap.org/weather-conditions

    String imageUrlHead      = "http://openweathermap.org/img/w/";
    String imageUrlBody      = "";
    String imageUrlExtension = ".png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgView     = findViewById(R.id.condIcon);
        callIonWebService();

    }

    private void callIonWebService() {
        Ion.with(getApplicationContext())
                .load("http://api.openweathermap.org/data/2.5/weather?q="+"Mumbai,In"+"&appid=82af4295b345889c281640e43c8d0862")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        try
                        {
                            Log.d(TAG,"result = "+result);

                            /****************************************************/
                            Log.d(TAG,"*************************************");
                            JsonObject coord    = result.getAsJsonObject("coord");
                            Log.d(TAG,"coord = "+coord);

                            String lon = String.valueOf(coord.getAsJsonPrimitive("lon"));
                            String lat = String.valueOf(coord.getAsJsonPrimitive("lat"));

                            Log.d(TAG,"lon = "+lon);
                            Log.d(TAG,"lat = "+lat);
                            /****************************************************/
                            Log.d(TAG,"*************************************");
                            JsonArray weather    = result.getAsJsonArray("weather");

                            for(int i=0;i<weather.size();i++)
                            {
                                JsonElement object =  weather.get(i);
                                Log.d(TAG,"weather at "+i+" = "+weather.get(i));

                                Log.d(TAG,"id           = "+object.getAsJsonObject().getAsJsonPrimitive("id"));
                                Log.d(TAG,"main         = "+object.getAsJsonObject().getAsJsonPrimitive("main"));
                                Log.d(TAG,"description  = "+object.getAsJsonObject().getAsJsonPrimitive("description"));
                                Log.d(TAG,"icon         = "+object.getAsJsonObject().getAsJsonPrimitive("icon"));
                                imageUrlBody                 =  object.getAsJsonObject().getAsJsonPrimitive("icon").getAsString();

                            }
                            /****************************************************/
                            Log.d(TAG,"*************************************");
                            Log.d(TAG,"base = "+result.getAsJsonPrimitive("base"));
                            /****************************************************/
                            Log.d(TAG,"*************************************");
                            JsonObject main    = result.getAsJsonObject("main");
                            Log.d(TAG,"main = "+main);

                            String temp = String.valueOf(main.getAsJsonPrimitive("temp"));
                            String pressure = String.valueOf(main.getAsJsonPrimitive("pressure"));
                            String humidity = String.valueOf(main.getAsJsonPrimitive("humidity"));
                            String temp_min = String.valueOf(main.getAsJsonPrimitive("temp_min"));
                            String temp_max = String.valueOf(main.getAsJsonPrimitive("temp_max"));

                            Log.d(TAG,"temp     = "+temp);
                            Log.d(TAG,"pressure = "+pressure);
                            Log.d(TAG,"humidity = "+humidity);
                            Log.d(TAG,"temp_min = "+temp_min);
                            Log.d(TAG,"temp_max = "+temp_max);

                            /****************************************************/
                            Log.d(TAG,"*************************************");
                            Log.d(TAG,"visibility = "+result.getAsJsonPrimitive("visibility"));
                            /****************************************************/
                            Log.d(TAG,"*************************************");
                            JsonObject wind    = result.getAsJsonObject("wind");
                            Log.d(TAG,"wind = "+wind);

                            String speed = String.valueOf(wind.getAsJsonPrimitive("speed"));
                            String deg  = String.valueOf(wind.getAsJsonPrimitive("deg"));

                            Log.d(TAG,"speed    = "+speed);
                            Log.d(TAG,"deg      = "+deg);

                            /****************************************************/
                            Log.d(TAG,"*************************************");
                            JsonObject clouds    = result.getAsJsonObject("clouds");
                            Log.d(TAG,"clouds = "+clouds);
                            String all = String.valueOf(clouds.getAsJsonPrimitive("all"));
                            Log.d(TAG,"all    = "+all);
                            Log.d(TAG,"*************************************");
                            Log.d(TAG,"dt = "+result.getAsJsonPrimitive("dt"));

                            /****************************************************/
                            Log.d(TAG,"*************************************");
                            JsonObject sys    = result.getAsJsonObject("sys");
                            Log.d(TAG,"sys = "+sys);

                            String type     = String.valueOf(sys.getAsJsonPrimitive("type"));
                            String id       = String.valueOf(sys.getAsJsonPrimitive("id"));
                            String message  = String.valueOf(sys.getAsJsonPrimitive("message"));
                            String country  = String.valueOf(sys.getAsJsonPrimitive("country"));
                            String sunrise  = String.valueOf(sys.getAsJsonPrimitive("sunrise"));
                            String sunset   = String.valueOf(sys.getAsJsonPrimitive("sunset"));


                            Log.d(TAG,"type     = "+type);
                            Log.d(TAG,"id       = "+id);
                            Log.d(TAG,"message  = "+message);
                            Log.d(TAG,"country  = "+country);
                            Log.d(TAG,"sunrise  = "+sunrise);
                            Log.d(TAG,"sunset   = "+sunset);
                            /****************************************************/
                            Log.d(TAG,"*************************************");
                            Log.d(TAG,"id   = "+result.getAsJsonPrimitive("id"));
                            Log.d(TAG,"name = "+result.getAsJsonPrimitive("name"));
                            Log.d(TAG,"cod  = "+result.getAsJsonPrimitive("cod"));

                            /****************************************************/
                            Log.d(TAG,"full icon  = "+imageUrlHead+imageUrlBody+imageUrlExtension);
                            Ion.with(imgView)
                                    .placeholder(R.drawable.ic_launcher_background)
                                    .error(R.drawable.ic_launcher_foreground)
                                    .load(imageUrlHead+imageUrlBody+imageUrlExtension);

                        }
                        catch (Exception e1)
                        {
                            Log.d(TAG,"catch error = "+e1.getMessage());
                        }

                    }
                });

    }
}