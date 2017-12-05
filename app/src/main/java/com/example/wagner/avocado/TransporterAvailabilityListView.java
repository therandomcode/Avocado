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

public class TransporterAvailabilityListView extends ArrayAdapter<String> {
    private String[] time;
    private Activity context;


    public TransporterAvailabilityListView(Activity context, String[] time) {
        super(context, R.layout.activity_transporter_availability_listview, time);
        this.time = time;
        this.context=context;
   }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;
        if (r == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.activity_transporter_availability_listview, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) r.getTag();
        }


        viewHolder.tvw2.setText(time[position]);


        return r;
    }

    class ViewHolder {
        TextView tvw2;

        ViewHolder(View v) {
            tvw2 = v.findViewById(R.id.time);
        }
    }
}
