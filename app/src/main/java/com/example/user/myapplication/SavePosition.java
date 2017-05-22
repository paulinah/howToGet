package com.example.user.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Paula on 2017-05-08.
 */

public class SavePosition extends Activity {

    GPSTracker gps;
    Button btnShowLocation;
    PositionDAO dao;
    Activity activity;
    Position position;
    PositionViewHelper helper;
    protected MyApp mMyApp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_my_position);
        activity = this;
        btnShowLocation = (Button) findViewById(R.id.save_my_position);

        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(SavePosition.this);
                helper = new PositionViewHelper(activity);
                position = helper.createNewPosition();
                dao = new PositionDAO(activity);
                dao.insert(position);
                System.err.println(dao.getlistAll());
                dao.close();

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

                    finish();

                }else{
                    gps.showSettingsAlert();
                }

            }
        });
    }

    protected void onPause() {
  //      clearReferences();
        super.onPause();
    }
    protected void onDestroy() {
    //    clearReferences();
        super.onDestroy();
    }
//
//    private void clearReferences(){
//        Activity currActivity = mMyApp.getCurrentActivity();
//        if (this.equals(currActivity))
//            mMyApp.setCurrentActivity(this);
//    }

    private boolean hasIntentionToUpdate() {
        return getIntent().hasExtra("position");

    }
}