package com.example.user.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Paula on 2017-05-25.
 */

public class PositionList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    PositionDAO dao;
    private static List<Position> positions;
    private static ArrayAdapter<Position> adapter;
    ListView positionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        getSupportActionBar().hide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPosition();
    }

    private void loadPosition() {

        dao = new PositionDAO(this);
        positions = dao.getlistAll();

        positionList = (ListView) findViewById(R.id.list_position_list);
        adapter = new MyListAdapter(this, positions);
        positionList.setAdapter(adapter);

    }

    public void onItemClick(AdapterView<?> positionList, View item,int possition, long id) {

        Position position = (Position) positionList.getItemAtPosition(possition);
    }

}
