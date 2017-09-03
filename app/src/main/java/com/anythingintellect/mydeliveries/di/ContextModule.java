package com.anythingintellect.mydeliveries.di;

import android.content.Context;

import dagger.Module;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }


}
