package com.anythingintellect.mydeliveries.viewmodel;

import android.databinding.ObservableField;

import com.anythingintellect.mydeliveries.R;
import com.anythingintellect.mydeliveries.model.Delivery;
import com.anythingintellect.mydeliveries.repo.DeliveryRepository;
import com.anythingintellect.mydeliveries.util.Toaster;

import java.util.List;

import javax.inject.Inject;

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

    DeliveryRepository repository;
    Toaster toaster;

    @Inject
    public DeliveryListViewModel(DeliveryRepository repository, Toaster toaster) {
        this.repository = repository;
        this.showError = new ObservableField<>(false);
        this.isLoading = new ObservableField<>(false);
        this.deliveries = repository.getDeliveries();
        this.toaster = toaster;
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
        if (!isLoading.get()) {
            isLoading.set(true);
            showError.set(false);
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
                            if (deliveries == null || deliveries.size() == 0) {
                                showError.set(true);
                            } else {
                                toaster.showLong(R.string.txt_sync_error_toast);
                            }
                            isLoading.set(false);
                        }

                        @Override
                        public void onComplete() {
                            isLoading.set(false);
                        }
                    });
        }
    }

    public void dispose() {
        repository.dispose();
    }
}
