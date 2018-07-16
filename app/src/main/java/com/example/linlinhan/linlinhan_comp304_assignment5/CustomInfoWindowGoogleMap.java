package com.example.linlinhan.linlinhan_comp304_assignment5;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public CustomInfoWindowGoogleMap(Context ctx){
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.map_custom_infowindow, null);

        TextView txvBrand = view.findViewById(R.id.txvBrand);
       /* TextView details_tv = view.findViewById(R.id.details);
        ImageView img = view.findViewById(R.id.pic);*/


        txvBrand.setText(marker.getTitle());


        return view;
    }
}
