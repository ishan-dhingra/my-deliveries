package com.anythingintellect.mydeliveries.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.anythingintellect.mydeliveries.R;
import com.anythingintellect.mydeliveries.model.Delivery;
import com.anythingintellect.mydeliveries.model.Location;
import com.anythingintellect.mydeliveries.util.Toaster;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveryDetailFragment extends Fragment implements OnMapReadyCallback {


    private static final float DEFAULT_ZOOM = 19f;
    private Delivery delivery;

    public DeliveryDetailFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delivery = DeliveryDetailActivity.getDelivery(getArguments());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivery_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(delivery.getDescription());
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setupInfoView(view);
    }

    private void setupInfoView(View view) {
        TextView txtDescription = view.findViewById(R.id.txtDescription);
        SimpleDraweeView draweeView = view.findViewById(R.id.imgDelivery);
        txtDescription.setText(delivery.getDescription());
        draweeView.setImageURI(delivery.getImageUrl());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        addCurrentMarker(googleMap);
    }

    private void addCurrentMarker(GoogleMap googleMap) {
        Location location = delivery.getLocation();
        LatLng vehicle = new LatLng(location.getLat(), location.getLng());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(vehicle);
        markerOptions.title(delivery.getLocation().getAddress());
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker));
        googleMap.addMarker(markerOptions).showInfoWindow();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vehicle, DEFAULT_ZOOM));
    }
}
