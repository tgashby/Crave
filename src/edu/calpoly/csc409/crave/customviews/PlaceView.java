package edu.calpoly.csc409.crave.customviews;

import android.app.FragmentManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import edu.calpoly.csc409.crave.R;
import edu.calpoly.csc409.crave.pojos.Place;

public class PlaceView extends LinearLayout {
    protected Place mPlace;

    public PlaceView(Context context, Place place) {
        super(context);

        mPlace = place;

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.place_view, this, true);

        ((TextView)findViewById(R.id.place_name)).setText(mPlace.getName());
        ((TextView)findViewById(R.id.place_distance)).setText(mPlace.getDistance() + " mi");
    }

    public Place getPlace() {
        return mPlace;
    }
}
