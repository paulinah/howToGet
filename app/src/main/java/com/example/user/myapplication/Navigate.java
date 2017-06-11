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
                System.err.println(destinationLatitude);
                destinationLongitude= Double.parseDouble(extras.getString("longitude"));
                System.err.println(destinationLongitude);

            // our compass image
            image = (ImageView) findViewById(R.id.imageViewNavigate);

            // TextView that will tell the user what degree is he heading
            tvHeading = (TextView) findViewById(R.id.tvHeading);
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

        @Override
        public void onSensorChanged(SensorEvent event) {
            gps = new GPSTracker(Navigate.this);
            // get the angle around the z-axis rotated
            float degree = Math.round(event.values[0]);

                currentLatitude = gps.getLatitude();
                currentLongitude = gps.getLongitude();
            //OBLICZANIE AZYMUTU

            //OBLICZANIE ODLEGLOSCI
            double distance = Math.sqrt(Math.pow(destinationLatitude-currentLatitude,2)+(Math.pow(destinationLongitude-currentLongitude,2)));

            tvHeading.setText("Heading: " + (String.format("%.1f",degree)) + " degrees");
            tvDistance.setText("Distance: " + (String.format("%.4f",distance)) + " degrees");

            // create a rotation animation (reverse turn degree degrees)
            RotateAnimation ra = new RotateAnimation(
                    currentDegree,
                    -degree,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f);

            // how long the animation will take place
            ra.setDuration(210);

            // set the animation after the end of the reservation status
            ra.setFillAfter(true);

            // Start the animation
            image.startAnimation(ra);
            currentDegree = -degree;

        }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not in use
    }

}
