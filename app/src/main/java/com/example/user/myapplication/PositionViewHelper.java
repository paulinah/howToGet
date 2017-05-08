package com.example.user.myapplication;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Paula on 2017-05-08.
 */

public class PositionViewHelper {

    private final Activity activity;

    public PositionViewHelper(Activity activity) {
        this.activity = activity;
    }

    public String getName() {
        return getTextFieldValue(R.id.save_coordinates_name);
    }

    private String getTextFieldValue(int fieldId) {
        //System.out.println(activity.getComponentName().getClassName() + "MOJA ACTIVITY");
        EditText field = (EditText) activity.findViewById(fieldId);
        String value = field.getText().toString();
        return value;
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
