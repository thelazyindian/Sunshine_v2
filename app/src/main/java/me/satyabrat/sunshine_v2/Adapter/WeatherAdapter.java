package me.satyabrat.sunshine_v2.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import me.satyabrat.sunshine_v2.Model.Weather;
import me.satyabrat.sunshine_v2.Model.WeatherJson;
import me.satyabrat.sunshine_v2.R;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {

    public List<String> weatherList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView weather;

        public MyViewHolder(View itemView) {
            super(itemView);
            weather = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    public WeatherAdapter(List<String> weatherList){
        this.weatherList=weatherList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.weather.setText(weatherList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
}
