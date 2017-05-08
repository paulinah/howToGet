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
import android.widget.Toast;

/**
 * Created by Paula on 2017-05-08.
 */

public class SaveCoordinates extends AppCompatActivity {
    protected MyApp mMyApp;
    Button save;
    PositionDAO dao;
    Activity activity;
    Position position;
    PositionViewHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_coordinates);

        getSupportActionBar().hide(); //hide ActionBar

        mMyApp = (MyApp) this.getApplicationContext();
        //helper = new PositionViewHelper(this);
        save = (Button) findViewById(R.id.save_coordinates_add);
        save.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v) {

                helper = new PositionViewHelper(activity);
                position = helper.createNewPosition();
                dao = new PositionDAO(activity);
                dao.insert(position);
                dao.close();
                String message = " " + position.getName() + " was creating ";
                Toast.makeText(SaveCoordinates.this, message, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        mMyApp.setCurrentActivity(this);
        activity = mMyApp.getCurrentActivity();

    }
    protected void onPause() {
        clearReferences();
        super.onPause();
    }
    protected void onDestroy() {
        clearReferences();
        super.onDestroy();
    }

    private void clearReferences(){
        Activity currActivity = mMyApp.getCurrentActivity();
        if (this.equals(currActivity))
            mMyApp.setCurrentActivity(null);
    }

    private boolean hasIntentionToUpdate() {
        return getIntent().hasExtra("position");

    }


}
