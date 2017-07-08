package me.satyabrat.sunshine_v2.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("dt")
    @Expose
    public int dt;
    @SerializedName("temp")
    @Expose
    public Temp temp;
    @SerializedName("pressure")
    @Expose
    public double pressure;
    @SerializedName("humidity")
    @Expose
    public int humidity;
    @SerializedName("weather")
    @Expose
    public java.util.List<Weather> weather = null;
    @SerializedName("speed")
    @Expose
    public double speed;
    @SerializedName("deg")
    @Expose
    public int deg;
    @SerializedName("clouds")
    @Expose
    public int clouds;
    @SerializedName("rain")
    @Expose
    public double rain;

}