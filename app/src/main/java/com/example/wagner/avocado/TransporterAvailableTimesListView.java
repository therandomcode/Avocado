package com.example.wagner.avocado;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TransporterAvailableTimesListView extends ArrayAdapter<String> {
    private String[] dates;
    private String[] time;
    private Activity context;

    public TransporterAvailableTimesListView(Activity context, String[] dates, String[] time) {
        super(context, R.layout.activity_transporter_available_times_listview, dates);
        this.context = context;
        this.dates = dates;
        this.time = time;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        TransporterAvailableTimesListView.ViewHolder viewHolder = null;
        if (r == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.activity_transporter_available_times_listview, null, true);
            viewHolder = new TransporterAvailableTimesListView.ViewHolder(r);
            r.setTag(viewHolder);
        } else {
            viewHolder = (TransporterAvailableTimesListView.ViewHolder) r.getTag();
        }

        viewHolder.tvw1.setText(dates[position]);
        viewHolder.tvw2.setText(time[position]);

        return r;
    }

    class ViewHolder {
        TextView tvw1;
        TextView tvw2;

        ViewHolder(View v) {
            tvw1 = v.findViewById(R.id.datePicked);
            tvw2 = v.findViewById(R.id.timePicked);
        }
    }
}
