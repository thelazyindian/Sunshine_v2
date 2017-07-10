package me.satyabrat.sunshine_v2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.satyabrat.sunshine_v2.Adapter.WeatherAdapter;
import me.satyabrat.sunshine_v2.Interface.WeatherAPIEndPointInterface;
import me.satyabrat.sunshine_v2.Model.Weather;
import me.satyabrat.sunshine_v2.Model.WeatherJson;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String TAG = "MainActivity";
    String API_KEY="2daa104fa739b1de561007f5271089e5";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private WeatherAdapter mAdapter;
    private List<String> weatherList;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
pDialog = new ProgressDialog(this);
pDialog.setMessage("Fetching Data...");
pDialog.setCancelable(false);
        fetchWeather();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String getReadableDateString(long time) {
        // Because the API returns a unix timestamp (measured in seconds),
        // it must be converted to milliseconds in order to be converted to valid date.
        Date date = new Date(time * 1000);
        SimpleDateFormat format = new SimpleDateFormat("E, MMM d");
        return format.format(date).toString();
    }

    private void fetchWeather(){

        pDialog.show();

        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request request = chain.request().newBuilder()
                                        .addHeader("Accept", "Application/JSON").build();
                                return chain.proceed(request);
                            }
                        }
                ).build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        WeatherAPIEndPointInterface service  = retrofit.create(WeatherAPIEndPointInterface.class);
        Call<WeatherJson> call = service.getWeather("752001",
                "json",
                "metric",
                "7",
                API_KEY);

        call.enqueue(new Callback<WeatherJson>() {
            @Override
            public void onResponse(Call<WeatherJson> call, retrofit2.Response<WeatherJson> response) {
                Log.d(TAG, "on Response:" + response.code());
                weatherList=new ArrayList<>();
                WeatherJson result=response.body();

                for(me.satyabrat.sunshine_v2.Model.List list : result.list) {
                    for(Weather weather : list.weather){
                        Log.i(TAG, weather.main);
                        Log.i(TAG, getReadableDateString(list.dt));
                        Log.i(TAG, "MinTemp = " + Double.toString(list.temp.min) + "  MaxTemp = " + Double.toString(list.temp.max));
                        String weatherListItem =  getReadableDateString(list.dt) + " - " + weather.main + " - " + "MaxTemp: " + Double.toString(Math.round(list.temp.max)) + " MinTemp: " + Double.toString(Math.round(list.temp.min));
                        weatherList.add(weatherListItem);
                    }
                }

                mAdapter = new WeatherAdapter(weatherList);
                mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(mAdapter);

                pDialog.hide();
            }

            @Override
            public void onFailure(Call<WeatherJson> call, Throwable t) {
                pDialog.hide();
                Toast.makeText(MainActivity.this, "Fetch Error :(", Toast.LENGTH_LONG).show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchWeather();
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }
}
