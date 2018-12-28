package app.coconut.ui.com.beelabs.ui.map;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import app.beelabs.com.codebase.base.BaseActivity;

public class CocoBaseMap {

    private static CocoBaseMap baseMap;
    private GoogleMap googleMap;
    private static GoogleApiClient googleApiClient;

    public CocoBaseMap(GoogleMap googleMap, GoogleApiClient googleApiClient) {
        this.googleMap = googleMap;
        this.googleApiClient = googleApiClient;
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

    public CocoBaseMap showMapLocationByCoordinate(double[] latlng, boolean showCurrentLocation, boolean clear) {

        if (clear) googleMap.clear();

        if (latlng.length > 0) {

            LatLng latLng = new LatLng(latlng[0], latlng[1]);

            if (showCurrentLocation) {

                googleApiClient.reconnect();
                return this;

            } else {
                createMarker(googleMap, latLng, true, true);
            }


        }

        return null;
    }


    public void createMarker(GoogleMap googleMap, LatLng place, boolean centerilize, boolean clearMap) {
        if (clearMap) googleMap.clear();

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 15f));

    }


}
