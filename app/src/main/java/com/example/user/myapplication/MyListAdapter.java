package com.example.user.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Paula on 2017-05-25.
 */
public class MyListAdapter extends ArrayAdapter<Position> {

    private List<Position> positions;
    private PositionList activity;
    PositionDAO dao;
    Position position;


    public MyListAdapter(PositionList context, List<Position> positions) {
        super(context, R.layout.list_row, positions);
        this.activity = context;
        this.positions = positions;
    }

    @Override
    public View getView(int possition, View converView, ViewGroup parent) {

        final ImageHolder holder;
        LayoutInflater customInflater = LayoutInflater.from(getContext());

        if (converView == null) {

            converView = customInflater.inflate(R.layout.list_row,parent,false);
            holder = new ImageHolder(converView);
            holder.name = (TextView) converView.findViewById(R.id.row_name);
            holder.latitude = (TextView) converView.findViewById(R.id.row_latitude);
            holder.longitude = (TextView) converView.findViewById(R.id.row_longitude);
            converView.setTag(holder);
        }
        else {
            holder = (ImageHolder) converView.getTag();
        }

        position = positions.get(possition);
        holder.name.setText(position.getName());
        holder.latitude.setText(position.getLatitude());
        holder.longitude.setText(position.getLongitude());

        return converView;
    }

    public class ImageHolder
    {
        TextView name;
        TextView latitude;
        TextView longitude;

        public ImageHolder (View v) {

            name = (TextView) v.findViewById(R.id.row_name);
            latitude = (TextView) v.findViewById(R.id.row_latitude);
            longitude = (TextView) v.findViewById(R.id.row_longitude);
        }
    }
}
