package com.anythingintellect.mydeliveries.di;

import dagger.Subcomponent;

/**
 * Created by ishan.dhingra on 03/09/17.
 */
@Subcomponent(modules = {ContextModule.class})
@PerFragment
public interface ContextComponent {

}
