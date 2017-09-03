package com.anythingintellect.mydeliveries.repo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.anythingintellect.mydeliveries.db.LocalStore;
import com.anythingintellect.mydeliveries.model.Delivery;
import com.anythingintellect.mydeliveries.network.MyDeliveriesAPIService;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmResults;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public class DeliveryRepository {

    LocalStore localStore;
    MyDeliveriesAPIService apiService;

    public DeliveryRepository(@NonNull LocalStore localStore,
                              @NonNull MyDeliveriesAPIService apiService) {
        this.localStore = localStore;
        this.apiService = apiService;
    }


    public RealmResults<Delivery> getDeliveries() {
        return localStore.getDeliveries();
    }

    public void fetchAndStoreDeliveries() {
        apiService.getDeliveries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<List<Delivery>>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull List<Delivery> deliveries) throws Exception {
                        localStore.saveDeliveries(deliveries);
                    }
                }).subscribe(new Observer<List<Delivery>>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull List<Delivery> deliveries) {

            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
