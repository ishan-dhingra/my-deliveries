package com.anythingintellect.mydeliveries.di;

import android.content.Context;

import com.anythingintellect.mydeliveries.util.DefaultNavigator;
import com.anythingintellect.mydeliveries.util.Navigator;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @PerContext
    public Navigator providesNavigator() {
        return new DefaultNavigator(context);
    }


}
