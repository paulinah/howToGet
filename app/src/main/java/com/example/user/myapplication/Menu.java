package com.example.user.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Paula on 2017-04-09.
 */

public class Menu extends Activity
{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu1);

        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#25315e"));
        ImageButton compass = (ImageButton) findViewById(R.id.item1);
        ImageButton savecoordinates = (ImageButton) findViewById(R.id.item3);
        ImageButton saveposition = (ImageButton) findViewById(R.id.item2);
        ImageButton navigation = (ImageButton) findViewById(R.id.item4);


        compass.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view)
            {
                Intent intention = new Intent(getApplicationContext(), Compass.class);
                startActivity(intention);
            }
        });

        savecoordinates.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view)
            {
                Intent intention = new Intent(getApplicationContext(), SaveCoordinates.class);
                startActivity(intention);
            }
        });

        saveposition.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view)
            {
                Intent intention = new Intent(getApplicationContext(), SavePosition.class);
                startActivity(intention);
            }
        });

        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intention = new Intent(getApplicationContext(), PositionList.class);
                startActivity(intention);
            }
        });


    }
};
