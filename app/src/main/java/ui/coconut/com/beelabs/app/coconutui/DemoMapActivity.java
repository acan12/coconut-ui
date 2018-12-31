package ui.coconut.com.beelabs.app.coconutui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import app.beelabs.com.codebase.base.BaseActivity;
import app.coconut.ui.com.beelabs.UIBase;
import app.coconut.ui.com.beelabs.ui.map.CocoBaseMap;
import pl.tajchert.nammu.Nammu;

public class DemoMapActivity extends BaseActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    private CocoBaseMap baseMap;
    private GoogleMap gMap;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_map);

        Nammu.init(this);

        if (Nammu.checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {

            mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    IConfig.REQUEST_CODE_MAP_PERMISSION);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        baseMap = CocoBaseMap.getInstance(googleMap, CocoBaseMap.setupGoogleApiClient(this, false));

//        // Add a marker in Sydney, Australia, and move the camera.
//        LatLng sydney = new LatLng(-34, 151);
//        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        renderMap(googleMap);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        UIBase.restartActivityWithoutAnimation(this);
    }

    @SuppressLint("MissingPermission")
    private void renderMap(final GoogleMap googleMap) {
        googleMap.clear();
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        // Turn on the My Location layer and the related control on the map.
        baseMap.setGoogleMap(googleMap);
        baseMap.showMapLocationByCoordinate(new double[]{-6.1753924,106.8249641}, false, true, R.drawable.marker);

        googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

                if (Nammu.checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    double lat = googleMap.getCameraPosition().target.latitude;
                    double lng = googleMap.getCameraPosition().target.longitude;

                    if (lat == 0 && lng == 0) return; // break if not has location coordinate
                    Geocoder geocoder = new Geocoder(DemoMapActivity.this, Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
                        if (addresses.size() == 0) return;

                        String address = addresses.get(0).getAddressLine(0);
                        String region = addresses.get(0).getAdminArea();
                        String city = addresses.get(0).getLocality();
                        String state = addresses.get(0).getAdminArea();
                        String fullAddress = address + ", " + city + " " + state;


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    ActivityCompat.requestPermissions(DemoMapActivity.this,
                            new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                            IConfig.REQUEST_CODE_MAP_PERMISSION);
                }
            }
        });
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(baseMap.getGoogleApiClient());

        try {
            if (location == null) {
                // Create the LocationRequest object
                LocationRequest mLocationRequest = LocationRequest.create()
                        .setPriority(LocationRequest.PRIORITY_LOW_POWER)
                        .setInterval(60 * 1000)        // 10 seconds, in milliseconds
                        .setFastestInterval(1 * 1000); // 1 second, in milliseconds

                LocationServices.FusedLocationApi.requestLocationUpdates(baseMap.getGoogleApiClient(), mLocationRequest, this);

            } else {

                baseMap.createMarker(baseMap.getGoogleMap(), new LatLng(location.getLatitude(), location.getLongitude()), true, R.drawable.marker);
            }
        } catch (Exception e) {
            Log.e("MapKokuoFragment:", e.getMessage());
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
