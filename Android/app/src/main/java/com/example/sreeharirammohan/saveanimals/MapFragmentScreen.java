package com.example.sreeharirammohan.saveanimals;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;


public class MapFragmentScreen extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;


    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                googleMap.setMyLocationEnabled(true);


                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterator i = dataSnapshot.getChildren().iterator();

                        while(i.hasNext()) {

                            final DataSnapshot animal = (DataSnapshot) (i.next());

                            final String animalName = animal.getKey();




                            animal.child("Location").getRef().addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    Iterator locationIterator = dataSnapshot.getChildren().iterator();

                                    while(locationIterator.hasNext()) {
                                        DataSnapshot location = (DataSnapshot) (locationIterator.next());
                                        String str = location.getValue().toString();
                                        String[] arr = str.split(",");

                                        double latitude = Double.parseDouble(arr[0]);
                                        double longitude = Double.parseDouble(arr[1]);

                                        Log.v("Monkey", animalName + " -> " + latitude + "," + longitude);

                                        googleMap.addMarker(new MarkerOptions()
                                                .position(new LatLng(latitude, longitude))
                                                .title(animalName + " was found here"));


                                        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.baldeagle);;



                                        if(animalName.equals("Bald Eagle")) {
                                            icon = BitmapDescriptorFactory.fromResource(R.drawable.baldeagle);
                                        } else if(animalName.equals("Beaver")) {
                                            icon = BitmapDescriptorFactory.fromResource(R.drawable.beaver);
                                        } else if(animalName.equals("Blue Butterfly")) {
                                            icon = BitmapDescriptorFactory.fromResource(R.drawable.butterfly);
                                        } else if(animalName.equals("California Condor")) {
                                            icon = BitmapDescriptorFactory.fromResource(R.drawable.cali_condor);
                                        } else if(animalName.equals("California Vole")) {
                                            icon = BitmapDescriptorFactory.fromResource(R.drawable.vole);
                                        } else if(animalName.equals("Kit Fox")) {
                                            icon = BitmapDescriptorFactory.fromResource(R.drawable.kitfox);
                                        } else if(animalName.equals("Peregrine Falcon")) {
                                            icon = BitmapDescriptorFactory.fromResource(R.drawable.peregrine);
                                        } else if(animalName.equals("Red-Legged Frog")) {
                                            icon = BitmapDescriptorFactory.fromResource(R.drawable.red_leg_frog);
                                        } else if(animalName.equals("Sea Otter")) {
                                            icon = BitmapDescriptorFactory.fromResource(R.drawable.sea_otter);
                                        } else if(animalName.equals("Wolverine Animal")) {
                                            icon = BitmapDescriptorFactory.fromResource(R.drawable.wolverine);
                                        }

                                        // For dropping a marker at a point on the Map
                                        LatLng marker = new LatLng(latitude, longitude);

                                        googleMap.addMarker(new MarkerOptions().position(marker).title(animalName + " was found here").icon(icon));
                                        // For zooming automatically to the location of the marker
                                        CameraPosition cameraPosition = new CameraPosition.Builder().target(marker).zoom(12).build();
                                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                                    }


                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });








                        }



                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });





            }
        });

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


}
