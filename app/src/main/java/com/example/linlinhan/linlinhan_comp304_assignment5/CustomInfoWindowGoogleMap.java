package com.example.linlinhan.linlinhan_comp304_assignment5;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import static android.content.Context.MODE_PRIVATE;

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

        //TextView txvBrand = view.findViewById(R.id.txvBrand);
        TextView txvShowroom=view.findViewById(R.id.txvShowroom);
        TextView txvAddress=view.findViewById(R.id.txvAddress);
        ImageView imgvLogo = view.findViewById(R.id.imgvLogo);

        txvShowroom.setText(marker.getTitle());
        //txvShowroom.setText(marker.getSnippet());
        txvAddress.setText(marker.getSnippet());

        SharedPreferences myPref =context.getSharedPreferences("Asg5SharedPreferences", MODE_PRIVATE);
        String selectedBrand = myPref.getString("selectedBrand","");
        int imageId= context.getResources().getIdentifier(selectedBrand.toLowerCase(),
                "drawable", context.getPackageName());



        imgvLogo.setImageResource(imageId);


        return view;
    }
}
