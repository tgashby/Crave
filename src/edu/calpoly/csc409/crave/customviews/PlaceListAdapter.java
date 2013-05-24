package edu.calpoly.csc409.crave.customviews;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import edu.calpoly.csc409.crave.pojos.Place;

/**
 * This class binds the visual PlaceViews and the data behind them (Places).
 */
public class PlaceListAdapter extends BaseAdapter {
    private Context m_context;
    private List<Place> mPlaceList;

    public PlaceListAdapter(Context context, List<Place> jokeList) {
        this.m_context = context;
        this.mPlaceList = jokeList;
    }

    @Override
    public int getCount() {
        return mPlaceList.size();
    }

    @Override
    public Object getItem(int ndx) {
        return (Place) mPlaceList.get(ndx);
    }

    @Override
    public long getItemId(int ndx) {
        return ndx;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return new PlaceView(this.m_context, this.mPlaceList.get(position));
    }
}
