package com.anythingintellect.mydeliveries.network;

import com.anythingintellect.mydeliveries.model.Delivery;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public interface MyDeliveriesAPIService {

    @GET("/deliveries")
    Observable<List<Delivery>> getDeliveries();

}
