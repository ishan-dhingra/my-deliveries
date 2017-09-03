package com.anythingintellect.mydeliveries.di;

import android.content.Context;

import com.anythingintellect.mydeliveries.db.LocalStore;
import com.anythingintellect.mydeliveries.network.MyDeliveriesAPIService;
import com.anythingintellect.mydeliveries.repo.DeliveryRepository;
import com.anythingintellect.mydeliveries.repo.DeliveryRepositoryImpl;
import com.anythingintellect.mydeliveries.util.Toaster;
import com.anythingintellect.mydeliveries.util.ToasterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ishan.dhingra on 03/09/17.
 */
@Module(includes = {NetworkModule.class, DBModule.class})
public class BaseModule {

    private final Context context;

    public BaseModule(Context context) {
        this.context = context;
    }

    @Provides
    public DeliveryRepository providesDeliveryRepository(LocalStore localStore,
                                                         MyDeliveriesAPIService apiService) {
        return new DeliveryRepositoryImpl(localStore, apiService);
    }

    @Provides
    @Singleton
    public Toaster providesToaster() {
        return new ToasterImpl(context);
    }


}
