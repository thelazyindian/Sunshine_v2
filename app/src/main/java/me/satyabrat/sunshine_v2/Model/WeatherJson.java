package me.satyabrat.sunshine_v2.Model;

import com.google.gson.annotations.SerializedName;

public class WeatherJson {

    @SerializedName("city")
    public City city;
    @SerializedName("cod")
    public String cod;
    @SerializedName("message")
    public double message;
    @SerializedName("cnt")
    public int cnt;
    @SerializedName("list")
    public java.util.List<me.satyabrat.sunshine_v2.Model.List> list = null;

}
