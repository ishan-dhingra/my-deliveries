package com.anythingintellect.mydeliveries;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.util.concurrent.Callable;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public class BaseTest {

    @BeforeClass
    public static void setupRxJava() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.computation();
            }
        });

        RxAndroidPlugins.setMainThreadSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
                return Schedulers.computation();
            }
        });
    }

    @AfterClass
    public static void rxTearDown() {
        RxAndroidPlugins.reset();
    }


}
