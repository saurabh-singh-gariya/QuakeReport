package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = "of";

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquake) {
        super(context,0, earthquake);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemview = convertView;
        if(listItemview == null){
            listItemview = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }
        Earthquake currentEarthquake = getItem(position);

        //for displaying magnitude of the earhquake
        Double magnitude = currentEarthquake.getEarthquakeMagnitude();
        String finalMagnitude = formatMagnitude(magnitude);

        TextView magTextView = (TextView) listItemview.findViewById(R.id.magnitude);
        magTextView.setText(finalMagnitude);

        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getEarthquakeMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        // for Locations of the EarthQuake
        String originalLocation = currentEarthquake.getEarthquakeLocation();
        String offsetLocation;
        String primaryLocation;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            offsetLocation = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        }
        else{
            offsetLocation = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView offsetView = (TextView) listItemview.findViewById(R.id.location_offset);
        offsetView.setText(offsetLocation);

        TextView primaryLocView = (TextView) listItemview.findViewById(R.id.primary_location);
        primaryLocView.setText(primaryLocation);

        //Creating a date object and passing the time in milliseconds for into the default constructor
        Date dateObject = new Date(currentEarthquake.getEarthquakeTime());

        //setting up the TextView to show Date
        TextView dateTextView = (TextView) listItemview.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        dateTextView.setText(formattedDate);

        //setting up the TextView to show Time
        TextView timeTextView = (TextView) listItemview.findViewById(R.id.time);
        String formattedTime = formateTime(dateObject);
        timeTextView.setText(formattedTime);

        return listItemview;
    }

    /**
     * Helper method that will return formatted date from Unix to Human Readable
     * @param dateObj
     * @return formatted date in String type
     */

    private String formatDate(Date dateObj){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd,YYYY");
        return dateFormat.format(dateObj);
    }

    /**
     * Helper method that will return formatted time from Unix to Human Readable
     * @param dateObj
     * @return formatted time in String type
     */

    private String formateTime(Date dateObj){
        SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");
        return timeformat.format(dateObj);
    }

    /**
     * Helper method that will return fromatted magnitude to one decimal place
     * @param magnitude
     * @return formated magnitude in String type
     */
    private String formatMagnitude(double magnitude){
        DecimalFormat format = new DecimalFormat("0.0");
        return format.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
