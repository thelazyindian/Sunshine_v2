package me.satyabrat.sunshine_v2.Model;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("coord")
    public Coord coord;
    @SerializedName("country")
    public String country;
    @SerializedName("population")
    public int population;

}