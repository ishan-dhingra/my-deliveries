package com.anythingintellect.mydeliveries.repo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.anythingintellect.mydeliveries.db.LocalStore;
import com.anythingintellect.mydeliveries.model.Delivery;
import com.anythingintellect.mydeliveries.network.MyDeliveriesAPIService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmResults;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public class DeliveryRepositoryImpl implements DeliveryRepository {

    LocalStore localStore;
    MyDeliveriesAPIService apiService;

    public DeliveryRepositoryImpl(@NonNull LocalStore localStore,
                                  @NonNull MyDeliveriesAPIService apiService) {
        this.localStore = localStore;
        this.apiService = apiService;
    }


    public RealmResults<Delivery> getDeliveries() {
        return localStore.getDeliveries();
    }

    public Observable<List<Delivery>> fetchAndStoreDeliveries() {
        return apiService.getDeliveries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                // Hack to assign ids before inserting into db
                // technically the id should be assigned from server side
                .map(new Function<List<Delivery>, List<Delivery>>() {
                    @Override
                    public List<Delivery> apply(@io.reactivex.annotations.NonNull List<Delivery> deliveries) throws Exception {
                        int id = 1;
                        for (Delivery delivery : deliveries) {
                            delivery.setId(id++);
                        }
                        return deliveries;
                    }
                })
                .doOnNext(new Consumer<List<Delivery>>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull List<Delivery> deliveries) throws Exception {
                        localStore.saveDeliveries(deliveries);
                    }
                });
    }

    @Override
    public void dispose() {
        localStore.dispose();
    }
}
