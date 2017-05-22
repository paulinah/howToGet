package com.example.user.myapplication;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Paula on 2017-05-08.
 */

public class PositionViewHelper {

    private Activity activity;

    public PositionViewHelper(Activity activity) {
        this.activity = activity;
    }

    public String getName() {
        return getTextFieldValue(R.id.save_my_position);
    }

    private String getTextFieldValue(int fieldId) {
        System.err.println(activity.toString());

        EditText field = (EditText) activity.findViewById(R.id.nazwa_pozycji);
        String value = field.getText().toString();
        return value;
    }
    public String getMyLatitude() {
        return getTextFieldValue(R.id.save_coordinates_latitude);

    }
    public String getMyLongitude() {
        return getTextFieldValue(R.id.save_coordinates_longitude);

    }
    public String getLatitude() {
        return getTextFieldValue(R.id.save_coordinates_latitude);

    }
    public String getLongitude() {
        return getTextFieldValue(R.id.save_coordinates_longitude);

    }
    Position createNewPosition() {
        return new Position(getName(), getLatitude(), getLongitude());
    }
}
