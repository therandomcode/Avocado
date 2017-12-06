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

public class FarmerMessagesListView extends ArrayAdapter<String> {
    private String[] transportername;
    private String[] time;
    private Activity context;
    private String[] price;

    public FarmerMessagesListView(Activity context, String[] transportername, String[] time, String[] price) {
        super(context, R.layout.activity_farmer_history_listview, transportername);
        this.context = context;
        this.transportername = transportername;
        this.time = time;
        this.price = price;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        FarmerMessagesListView.ViewHolder viewHolder = null;
        if (r == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.activity_farmer_messages_listview, null, true);
            viewHolder = new FarmerMessagesListView.ViewHolder(r);
            r.setTag(viewHolder);
        } else {
            viewHolder = (FarmerMessagesListView.ViewHolder) r.getTag();
        }

        viewHolder.tvw1.setText(transportername[position]);
        viewHolder.tvw2.setText(time[position]);
        viewHolder.tvw3.setText(price[position]);
        return r;
    }

    class ViewHolder {
        TextView tvw1;
        TextView tvw2;
        TextView tvw3;

        ViewHolder(View v) {
            tvw1 = v.findViewById(R.id.transportername);
            tvw2 = v.findViewById(R.id.time);
            tvw3 = v.findViewById(R.id.price);
        }
    }
}
