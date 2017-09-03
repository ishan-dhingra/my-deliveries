package com.anythingintellect.mydeliveries.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

@Component(modules = BaseModule.class)
@Singleton
public interface AppComponent {

    ContextComponent plusFragmentModule(ContextModule contextModule);

}
