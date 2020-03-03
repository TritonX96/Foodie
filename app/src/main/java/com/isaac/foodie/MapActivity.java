package com.isaac.foodie;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;


import android.view.View;
import android.widget.Toast;

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
import com.google.maps.android.SphericalUtil;

import java.io.IOException;
import java.util.List;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback{

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
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();


    }

    private void fetchLastLocation() {

        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)) {
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
                        supportMapFragment.getMapAsync(MapActivity.this);
                    }
                }
            });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
       LatLng latLng= new LatLng(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude());
       MarkerOptions markerOptions = new MarkerOptions().position(latLng)
               .title("Current Position");
       googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
       googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
       googleMap.addMarker(markerOptions);

       googleMap.addCircle(
               new CircleOptions()
                       .center(latLng)
                       .radius(500.0)
                       .strokeWidth(3f)
                       .strokeColor(Color.BLACK)
                       .fillColor(Color.argb(70,51,171,249))

       );

//        LatLng restLatlng = new LatLng(mRestaurantLocation.getLang(),mRestaurantLocation.getLong());
//        MarkerOptions restaMarker = new MarkerOptions().position(restLatlng)
//                .title(getName);



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    fetchLastLocation();
                }
                break;
        }
    }




    //Place search location
//    public void searchLocation(View view) {
//
//        EditText locationSearch = findViewById(R.id.searchBar);
//        String location = locationSearch.getText().toString();
//        List<Address> addressList = null;
//
//        if (location != null || !location.equals("")) {
//            Geocoder geocoder = new Geocoder(MapActivity.this);
//            try {
//                addressList = geocoder.getFromLocationName(location, 1);
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Address address = addressList.get(0);
//            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
//            mMap.addMarker(new MarkerOptions().position(latLng).title(location));
//            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//            Toast.makeText(getApplicationContext(),address.getLatitude()+" "+address.getLongitude(),Toast.LENGTH_LONG).show();
//
//            if (mSearchMarker != null) {
//                mSearchMarker.remove();
//            }
//
//        }
//    }


}


