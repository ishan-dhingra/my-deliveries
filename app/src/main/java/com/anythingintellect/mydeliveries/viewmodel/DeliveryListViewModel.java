package com.anythingintellect.mydeliveries.viewmodel;

import android.databinding.ObservableField;

import com.anythingintellect.mydeliveries.model.Delivery;
import com.anythingintellect.mydeliveries.repo.DeliveryRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.realm.RealmResults;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public class DeliveryListViewModel {

    private final ObservableField<Boolean> showError;
    private final ObservableField<Boolean> isLoading;
    private RealmResults<Delivery> deliveries;

    private DeliveryRepository repository;

    public DeliveryListViewModel(DeliveryRepository repository) {
        this.repository = repository;
        this.showError = new ObservableField<>(false);
        this.isLoading = new ObservableField<>(false);
        this.deliveries = repository.getDeliveries();
    }


    public ObservableField<Boolean> getShowError() {
        return showError;
    }

    public ObservableField<Boolean> getIsLoading() {
        return isLoading;
    }

    public RealmResults<Delivery> getDeliveries() {
        return deliveries;
    }

    public void syncDeliveries() {
        isLoading.set(true);
        repository.fetchAndStoreDeliveries()
                .subscribe(new Observer<List<Delivery>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Delivery> deliveries) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        isLoading.set(false);
                    }
                });
    }
}
