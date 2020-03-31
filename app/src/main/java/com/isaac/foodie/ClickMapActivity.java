package com.isaac.foodie;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class ClickMapActivity  extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    //GoogleApiClient mGoogleApiClient;
    Location mCurrentLocation;
    Location mRestaurantLocation;

    SupportMapFragment mMapFragment;

    Marker mSearchMarker;

    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 99;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();



    }
    private void fetchLastLocation() {

        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    mCurrentLocation = location;
                    Toast.makeText(getApplicationContext(),mCurrentLocation.getLatitude()
                            +""+mCurrentLocation.getLongitude(),Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment= (SupportMapFragment)
                            getSupportFragmentManager().findFragmentById(R.id.google_map);
                    supportMapFragment.getMapAsync(ClickMapActivity.this);
                }
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
//        LatLng latLng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
//        MarkerOptions markerOptions = new MarkerOptions().position(latLng)
//                .title("Current Position");
//        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
//        googleMap.addMarker(markerOptions);

        Intent mapIntent = this.getIntent();

        Double lat = Double.valueOf(mapIntent.getExtras().getString("latitude"));
        Double lng = Double.valueOf(mapIntent.getExtras().getString("longitude"));

        LatLng restaurantLatLng = new LatLng(lat, lng);
        MarkerOptions markerOptions = new MarkerOptions().position(restaurantLatLng)
                .title("Current Position");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(restaurantLatLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(restaurantLatLng, 15));
        googleMap.addMarker(markerOptions);

//        googleMap.addCircle(
//                new CircleOptions()
//                        .center(restaurantLatLng)
//                        .radius(100.0)
//                        .strokeWidth(3f)
//                        .strokeColor(Color.BLACK)
//                        .fillColor(Color.argb(70, 51, 171, 249))
//    );
    }
}
