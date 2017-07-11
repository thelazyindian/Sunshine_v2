package me.satyabrat.sunshine_v2.Model;

import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("dt")
    public int dt;
    @SerializedName("temp")
    public Temp temp;
    @SerializedName("pressure")
    public double pressure;
    @SerializedName("humidity")
    public int humidity;
    @SerializedName("weather")
    public java.util.List<Weather> weather = null;
    @SerializedName("speed")
    public double speed;
    @SerializedName("deg")
    public int deg;
    @SerializedName("clouds")
    public int clouds;
    @SerializedName("rain")
    public double rain;

}