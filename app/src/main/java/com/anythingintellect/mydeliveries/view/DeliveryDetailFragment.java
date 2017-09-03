package com.anythingintellect.mydeliveries.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anythingintellect.mydeliveries.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveryDetailFragment extends Fragment {


    public DeliveryDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivery_details, container, false);
    }

}
