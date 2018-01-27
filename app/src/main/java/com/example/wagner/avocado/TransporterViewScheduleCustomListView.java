package com.example.wagner.avocado;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TransporterViewScheduleCustomListView extends ArrayAdapter<String> {
    private String[] transportername;
    private String[] time;
    private String[] status;
    private Integer[] imgid;
    private Activity context;
    private Button startButton;

    public TransporterViewScheduleCustomListView(Activity context, String[] transportername,
                                                 String[] time, String[] status, Integer[] imgid) {
        super(context, R.layout.activity_transporter_view_schedule_listview, transportername);
        this.context = context;
        this.transportername = transportername;
        this.time = time;
        this.status = status;
        this.imgid = imgid;
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
        viewHolder.tvw3.setText(status[position]);

        return r;
    }

    class ViewHolder {
        TextView tvw1;
        TextView tvw2;
        TextView tvw3;
        ImageView ivw;

        ViewHolder(View v) {
            tvw1 = v.findViewById(R.id.farmerName);
            tvw2 = v.findViewById(R.id.timePicked);
            tvw3 = v.findViewById(R.id.status);
            ivw = v.findViewById(R.id.farmerPhoto);
        }
    }
}