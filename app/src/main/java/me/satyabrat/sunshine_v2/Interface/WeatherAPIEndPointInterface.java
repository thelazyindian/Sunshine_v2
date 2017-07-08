package me.satyabrat.sunshine_v2.Interface;

import me.satyabrat.sunshine_v2.Model.Weather;
import me.satyabrat.sunshine_v2.Model.WeatherJson;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPIEndPointInterface {
    @GET ("forecast/daily")
    Call<WeatherJson> getWeather(@Query("q") String query,
                                 @Query("metric") String metric,
                                 @Query("units") String units,
                                 @Query("cnt") String cnt,
                                 @Query("APPID") String appid);
}
