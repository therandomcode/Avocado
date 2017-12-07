package com.example.wagner.avocado;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FarmerHistoryListView extends ArrayAdapter<String> {
    private String[] transportername;
    private String[] time;
    private Integer[] imgid;
    private Activity context;
    private String[] price;
    private String[] delivered;

    public FarmerHistoryListView(Activity context, String[] transportername, String[] time, Integer[] imgid, String[] price, String[] delivered) {
        super(context, R.layout.activity_farmer_history_listview, transportername);
        this.context = context;
        this.transportername = transportername;
        this.time = time;
        this.imgid = imgid;
        this.price = price;
        this.delivered = delivered;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        FarmerHistoryListView.ViewHolder viewHolder = null;
        if (r == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.activity_farmer_history_listview, null, true);
            viewHolder = new FarmerHistoryListView.ViewHolder(r);
            r.setTag(viewHolder);
        } else {
            viewHolder = (FarmerHistoryListView.ViewHolder) r.getTag();
        }

        viewHolder.ivw.setImageResource(imgid[position]);
        viewHolder.tvw1.setText(transportername[position]);
        viewHolder.tvw2.setText(time[position]);
        viewHolder.tvw3.setText(price[position]);
        viewHolder.tvw4.setText(delivered[position]);


        return r;
    }

    class ViewHolder {
        TextView tvw1;
        TextView tvw2;
        TextView tvw3;
        TextView tvw4;
        ImageView ivw;

        ViewHolder(View v) {
            tvw1 = v.findViewById(R.id.datePicked);
            tvw2 = v.findViewById(R.id.timePicked);
            tvw3 = v.findViewById(R.id.price);
            tvw4 = v.findViewById(R.id.delivered);
            ivw = v.findViewById(R.id.farmerPhoto);
        }
    }
}
