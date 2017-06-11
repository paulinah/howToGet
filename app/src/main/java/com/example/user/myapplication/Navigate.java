package com.example.user.myapplication;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class Navigate extends Activity implements SensorEventListener {



        private ImageView image;
        private ImageView arrow;
        GPSTracker gps;
        // record the compass picture angle turned
        private float currentDegree = 0f;
        private double destinationLatitude;
        private double destinationLongitude;
        private double currentLatitude;
        private double currentLongitude;


        // device sensor manager
        private SensorManager mSensorManager;

        TextView tvHeading;
        TextView tvDistance;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.navigate);

            Bundle extras = getIntent().getExtras();
                destinationLatitude= Double.parseDouble(extras.getString("latitude"));
                destinationLongitude= Double.parseDouble(extras.getString("longitude"));

            // our compass image
            arrow = (ImageView) findViewById(R.id.imageViewNavigate);

            // TextView that will tell the user what degree is he heading
            tvDistance = (TextView) findViewById(R.id.tvDistance);

            // initialize your android device sensor capabilities
            mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        }

        @Override
        protected void onResume() {
            super.onResume();

            // for the system's orientation sensor registered listeners
            mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                    SensorManager.SENSOR_DELAY_GAME);
        }

        @Override
        protected void onPause() {
            super.onPause();

            // to stop the listener and save battery
            mSensorManager.unregisterListener(this);
        }
        double azimuth = 0;
        @Override
        public void onSensorChanged(SensorEvent event) {
            gps = new GPSTracker(Navigate.this);
            // get the angle around the z-axis rotated
            float degree = Math.round(event.values[0]);

                currentLatitude = gps.getLatitude();
                currentLongitude = gps.getLongitude();
            //OBLICZANIE AZYMUTU

            azimuth = (360/Math.PI)*Math.abs((destinationLongitude-currentLongitude)/(destinationLatitude-currentLatitude));
            int quarter;
            if(destinationLongitude>=currentLongitude){
                if(destinationLatitude>=currentLatitude){
                    quarter=1;
                }else {
                    quarter = 2;
                }
            }else{
                if(destinationLatitude>=currentLatitude){
                    quarter=4;
                }else{
                    quarter=3;
                }
            }switch(quarter) {
                case 1:
                    azimuth=azimuth;
                    break;
                case 2:
                    azimuth=180-azimuth;
                    break;
                case 3:
                    azimuth=180+azimuth;
                    break;
                case 4:
                    azimuth=360-azimuth;
                    break;
            }

            //OBLICZANIE ODLEGLOSCI
            double distance = Math.sqrt(Math.pow(destinationLatitude-currentLatitude,2)+(Math.pow(destinationLongitude-currentLongitude,2)));

            tvDistance.setText("Distance: " + (String.format("%.4f",distance)) + " degrees");


            RotateAnimation naviRotation = new RotateAnimation(
                    currentDegree,
                    -degree-(float)azimuth,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f);

            // how long the animation will take place
            naviRotation.setDuration(210);

            // set the animation after the end of the reservation status
            naviRotation.setFillAfter(true);

            // Start the animation
            // image.startAnimation(ra);
            arrow.startAnimation(naviRotation);
            currentDegree = -degree-(float)azimuth;

        }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not in use
    }

}
