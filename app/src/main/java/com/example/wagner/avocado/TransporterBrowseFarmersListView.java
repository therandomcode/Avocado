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

public class TransporterBrowseFarmersListView extends ArrayAdapter<String> {
    private String[] transportername;
    private Integer[] imgid;
    private Activity context;
    private String[] locationfrom;
    private String[] detail;

    public TransporterBrowseFarmersListView(Activity context, String[] transportername, Integer[] imgid, String[] locationfrom, String[] detail) {
        super(context, R.layout.activity_transporter_history_listview, transportername);
        this.context = context;
        this.transportername = transportername;
        this.imgid = imgid;
        this.locationfrom = locationfrom;
        this.detail = detail;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;
        if (r == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.activity_transporter_history_listview, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) r.getTag();
        }

        viewHolder.ivw.setImageResource(imgid[position]);
        viewHolder.tvw1.setText(transportername[position]);
        viewHolder.tvw5.setText(locationfrom[position]);
        viewHolder.tvw7.setText(detail[position]);


        return r;
    }

    class ViewHolder {
        TextView tvw1;
        TextView tvw5;
        TextView tvw7;
        ImageView ivw;

        ViewHolder(View v) {
            tvw1 = v.findViewById(R.id.transportername);
            tvw5 = v.findViewById(R.id.locationfrom);
            tvw7 = v.findViewById(R.id.detail);
            ivw = v.findViewById(R.id.imageView);
        }
    }
}
