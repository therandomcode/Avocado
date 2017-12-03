package com.example.wagner.avocado;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TransporterViewScheduleCustomListView extends ArrayAdapter<String> {
    private String[] transportername;
    private String[] time;
    private Integer[] imgid;
    private Activity context;
    private String[] price;
    private String[] locationfrom;
    private String[] locationto;
    private String[] detail;

    public TransporterViewScheduleCustomListView(Activity context, String[] transportername, String[] time, Integer[] imgid, String[] price, String[] locationfrom, String[] locationto, String[] detail) {
        super(context, R.layout.activity_transporter_view_schedule_listview, transportername);
        this.context = context;
        this.transportername = transportername;
        this.time = time;
        this.imgid = imgid;
        this.price = price;
        this.locationfrom = locationfrom;
        this.locationto = locationto;
        this.detail = detail;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;
        if (r == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.activity_transporter_view_schedule_listview, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) r.getTag();
        }

        viewHolder.ivw.setImageResource(imgid[position]);
        viewHolder.tvw1.setText(transportername[position]);
        viewHolder.tvw2.setText(time[position]);
        viewHolder.tvw3.setText(price[position]);
        viewHolder.tvw5.setText(locationfrom[position]);
        viewHolder.tvw6.setText(locationto[position]);
        viewHolder.tvw7.setText(detail[position]);


        return r;
    }

    class ViewHolder {
        TextView tvw1;
        TextView tvw2;
        TextView tvw3;
        TextView tvw5;
        TextView tvw6;
        TextView tvw7;
        ImageView ivw;

        ViewHolder(View v) {
            tvw1 = v.findViewById(R.id.transportername);
            tvw2 = v.findViewById(R.id.time);
            tvw3 = v.findViewById(R.id.price);
            tvw5 = v.findViewById(R.id.locationfrom);
            tvw6 = v.findViewById(R.id.locationto);
            tvw7 = v.findViewById(R.id.detail);
            ivw = v.findViewById(R.id.imageView);
        }
    }
}