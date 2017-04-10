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

        compass.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view)
            {
                Intent intention = new Intent(getApplicationContext(), Compass.class);
                startActivity(intention);
            }


        });

    }
};
