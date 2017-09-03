package com.anythingintellect.mydeliveries.di;

import com.anythingintellect.mydeliveries.network.MyDeliveriesAPIService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public Retrofit providesRetrofit(CallAdapter.Factory callAdapterFactory,
                                     Converter.Factory converterFactory) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(converterFactory)
                .build();
    }

    @Provides
    @Singleton
    public Converter.Factory providesConverterFactory() {
        return JacksonConverterFactory.create();
    }

    @Provides
    @Singleton
    public CallAdapter.Factory providesCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    public MyDeliveriesAPIService providesMyDeliveriesAPIService(Retrofit retrofit) {
        return retrofit.create(MyDeliveriesAPIService.class);
    }
}
