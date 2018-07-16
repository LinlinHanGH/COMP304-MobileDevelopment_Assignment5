package com.example.linlinhan.linlinhan_comp304_assignment5;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
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
        ImageView img = view.findViewById(R.id.imgvLogo);

        InfoWindowData infoWindowData = (InfoWindowData) marker.getTag();

        txvBrand.setText(marker.getTitle());
        int imageId = context.getResources().getIdentifier(infoWindowData.getImage().toLowerCase(),
                "drawable", context.getPackageName());
        img.setImageResource(imageId);


        return view;
    }
}
