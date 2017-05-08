package com.example.user.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by Paula on 2017-05-08.
 */

public class SaveCoordinates extends AppCompatActivity {
    protected MyApp mMyApp;
    Button save;
    PositionDAO dao;
    Activity activity;
    Position position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_coordinates);

        getSupportActionBar().hide(); //hide ActionBar

        //mMyApp = (MyApp) this.getApplicationContext();
        save = (Button) findViewById(R.id.save_coordinates_add);
        save.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                dao = new PositionDAO(activity);
                dao.insert(position);
                dao.close();
                dao.getlistAll();
                finish();
            }
        });

    }
}
