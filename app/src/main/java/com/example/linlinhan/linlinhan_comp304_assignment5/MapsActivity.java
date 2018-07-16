package com.example.linlinhan.linlinhan_comp304_assignment5;

import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String _selecctedBrand;
    private String _selecctedShowroom;
    private String _selecctedAddress;
    private double _latitude ;
    private double _longitude ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        SharedPreferences myPref = getSharedPreferences("Asg5SharedPreferences", MODE_PRIVATE);
        _selecctedBrand=myPref.getString("selectedBrand","");
        _selecctedShowroom = myPref.getString("selectedShowroom","");
        _selecctedAddress=myPref.getString("selectedAddress","");

        final Geocoder coder = new Geocoder(getApplicationContext(), Locale.getDefault());

        try{
            List<Address> geocodeResults =
                    coder.getFromLocationName(_selecctedAddress, 1);
            Iterator<Address> locations = geocodeResults.iterator();
                Address loc = locations.next();
            _latitude = loc.getLatitude();
            _longitude = loc.getLongitude();
        }
        catch(IOException e){
            Log.e("GeoAddress", "Failed to get location info", e);

        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng selectedPlace = new LatLng(_latitude, _longitude);

        // custom marker
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(selectedPlace).title(_selecctedShowroom);
        markerOptions.snippet(_selecctedAddress);
        //markerOptions.snippet(_selecctedAddress);

        //InfoWindowData info = new InfoWindowData();
        //info.setImage("honda");

        CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this);
        mMap.setInfoWindowAdapter(customInfoWindow);

        Marker m = mMap.addMarker(markerOptions);
        //m.setTag(info);
        //m.showInfoWindow();

        mMap.moveCamera(CameraUpdateFactory.newLatLng(selectedPlace));

    }// end of onMapReady

    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch (keyCode)
        {
            case KeyEvent.KEYCODE_3:
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
                break;
            case KeyEvent.KEYCODE_1:
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
                break;
        }
        return super.onKeyDown(keyCode,event);

    }// end of onKeyDown

    public void ChangeType(View view){
        if (mMap.getMapType()==GoogleMap.MAP_TYPE_NORMAL){
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        else if (mMap.getMapType()==GoogleMap.MAP_TYPE_SATELLITE){
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }
}
