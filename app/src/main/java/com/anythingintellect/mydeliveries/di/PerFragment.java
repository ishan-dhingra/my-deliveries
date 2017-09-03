package com.anythingintellect.mydeliveries.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

@Retention(RetentionPolicy.RUNTIME)
@Scope
public @interface PerFragment {
}
