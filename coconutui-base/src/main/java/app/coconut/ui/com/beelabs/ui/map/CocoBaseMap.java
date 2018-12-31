package app.coconut.ui.com.beelabs.ui.map;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import app.beelabs.com.codebase.base.BaseActivity;


public class CocoBaseMap {

    private static CocoBaseMap baseMap;
    private GoogleMap googleMap;
    private static GoogleApiClient googleApiClient;
    private Marker posMarker;

    public CocoBaseMap(GoogleMap googleMap, GoogleApiClient googleApiClient) {
        this.googleMap = googleMap;
        this.googleApiClient = googleApiClient;
    }


    public void setGoogleMap(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    public GoogleMap getGoogleMap() {
        return googleMap;
    }

    public static GoogleApiClient getGoogleApiClient() {
        return googleApiClient;
    }

    // -- basemap
    public static CocoBaseMap getInstance(GoogleMap googleMap, GoogleApiClient googleApiClient) {
        if (baseMap == null) {
            baseMap = new CocoBaseMap(googleMap, googleApiClient);
        }
        return baseMap;
    }

    public static GoogleApiClient setupGoogleApiClient(BaseActivity base, boolean resetApiClient) {

        if (resetApiClient) googleApiClient = null;
        if (googleApiClient == null) {
            // Create an instance of GoogleAPIClient.
            googleApiClient = new GoogleApiClient.Builder(base)
                    .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) base)
                    .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) base)
                    .addApi(LocationServices.API)
                    .addApi(Places.GEO_DATA_API)
                    .build();
        }

        return googleApiClient;
    }

    public CocoBaseMap showMapLocationByCoordinate(double[] latlngDefault, boolean showCurrentLocation, boolean clearMarker, int markerInt) {

        if (clearMarker) googleMap.clear();

        if (latlngDefault.length > 0) {

            LatLng latLng = new LatLng(latlngDefault[0], latlngDefault[1]);

            if (showCurrentLocation) {
                googleApiClient.reconnect();
                return this;

            } else {
                createMarker(googleMap, latLng, clearMarker, markerInt);
            }
        }

        return null;
    }


    public void createMarker(GoogleMap googleMap, LatLng currentCoordinate, boolean clearMap, int markerInt) {
        if (clearMap) googleMap.clear();

        posMarker = googleMap.addMarker(new MarkerOptions()
                .flat(true)
                .icon(BitmapDescriptorFactory.fromResource(markerInt))
                .anchor(.5f, .5f)
                .position(currentCoordinate)
        );

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentCoordinate, 15f));
    }
}
