package com.anythingintellect.mydeliveries.di;

import com.anythingintellect.mydeliveries.db.LocalStore;
import com.anythingintellect.mydeliveries.db.RealmLocalStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

@Module
public class DBModule {

    private final boolean inMemory;


    public DBModule(boolean inMemory) {
        this.inMemory = inMemory;
    }

    @Provides
    @Singleton
    public RealmConfiguration providesConfiguration() {
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
        builder.inMemory();
        return builder.build();
    }

    @Provides
    public Realm providesRealm(RealmConfiguration realmConfiguration) {
        return Realm.getInstance(realmConfiguration);
    }

    @Provides
    public LocalStore providesLocalStore(Realm realm) {
        return new RealmLocalStore(realm);
    }

}
