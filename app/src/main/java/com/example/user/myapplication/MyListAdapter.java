package com.example.user.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

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
            holder.delete = (ImageView) converView.findViewById(R.id.row_delete);
            converView.setTag(holder);
        }
        else {
            holder = (ImageHolder) converView.getTag();
        }

        position = positions.get(possition);
        holder.name.setText(position.getName());
        holder.latitude.setText(position.getLatitude());
        holder.longitude.setText(position.getLongitude());
        holder.delete.setOnClickListener(onDeleteListener(possition,holder));//clicked Delete ImageVie

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        holder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {
            }

            @Override
            public void onStartClose(SwipeLayout layout) {
            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //when user's hand released.
            }
        });

        return converView;
    }


    //Click on Delete Icon
    private View.OnClickListener onDeleteListener(final int possition, final ImageHolder holder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(activity)
                        .setTitle("Usuń pozycję")
                        .setMessage("Czy na pewno chcesz usunąć pozycję?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dao = new PositionDAO(activity);
                                dao.remove(position); //delete meal into the DB
                                dao.close();//close DB

                                positions.remove(possition);
                                holder.swipeLayout.close();
                                activity.updateAdapter();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        };
    }

    public class ImageHolder
    {
        TextView name;
        TextView latitude;
        TextView longitude;
        View delete;
        SwipeLayout swipeLayout;

        public ImageHolder (View v) {

            name = (TextView) v.findViewById(R.id.row_name);
            latitude = (TextView) v.findViewById(R.id.row_latitude);
            longitude = (TextView) v.findViewById(R.id.row_longitude);
            swipeLayout = (SwipeLayout) v.findViewById(R.id.swipe);
            delete = v.findViewById(R.id.row_delete);

            swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        }
    }
}
