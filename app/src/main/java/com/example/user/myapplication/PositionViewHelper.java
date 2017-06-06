package com.example.user.myapplication;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by Paula on 2017-05-08.
 */

public class PositionViewHelper {

    private Activity activity;

    public PositionViewHelper(Activity activity) {
        this.activity = activity;
    }

    public String getName(int fieldId) {
        return getTextFieldValue(fieldId);
    }

    Position createNewPosition(int fieldId) {
        return new Position(getName(fieldId));
    }
    Position createNewPosition(int fieldId,int latId, int longId) {
        return new Position(getName(fieldId),getLatitude(latId),getLongitude(longId));
    }

    private String getTextFieldValue(int fieldId) {
        System.err.println(activity.toString());

        EditText field = (EditText) activity.findViewById(fieldId);
        System.err.println("poprzed parametr: "+fieldId);
        System.err.println("bezposrednio: "+R.id.nazwa_pozycji);
        String value = field.getText().toString();
        return value;
    }
    public String getMyLatitude() {
        return getTextFieldValue(R.id.save_coordinates_latitude);

    }
    public String getMyLongitude() {
        return getTextFieldValue(R.id.save_coordinates_longitude);

    }
    public String getLatitude(int latId) {
        return getTextFieldValue(latId);

    }
    public String getLongitude(int longId) {
        return getTextFieldValue(longId);

    }

    //edit Meal in Swipe - fill data toFoodPornAddMeal screen
    public void EditMeal(Position position){
        EditText name = (EditText) activity.findViewById(R.id.save_coordinates_name);
        name.setText((position.getName()));
        EditText latitude = (EditText) activity.findViewById(R.id.save_coordinates_latitude);
        latitude.setText(position.getLatitude());
        EditText longitude = (EditText) activity.findViewById(R.id.save_coordinates_longitude);
        longitude.setText(position.getLongitude());
    }

}
