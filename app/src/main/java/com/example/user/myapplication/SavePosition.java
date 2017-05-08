package com.example.user.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Paula on 2017-05-08.
 */

public class SavePosition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_my_position);


        getSupportActionBar().hide(); //hide ActionBar

    }


}