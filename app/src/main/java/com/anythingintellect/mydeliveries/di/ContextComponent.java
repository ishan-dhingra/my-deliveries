package com.anythingintellect.mydeliveries.di;

import com.anythingintellect.mydeliveries.view.DeliveryDetailActivity;
import com.anythingintellect.mydeliveries.view.DeliveryListFragment;
import com.anythingintellect.mydeliveries.view.MainActivity;

import dagger.Subcomponent;

/**
 * Created by ishan.dhingra on 03/09/17.
 */
@Subcomponent(modules = {ContextModule.class})
@PerContext
public interface ContextComponent {

    void inject(MainActivity mainActivity);
    void inject(DeliveryDetailActivity mainActivity);
    void inject(DeliveryListFragment deliveryListFragment);

}
