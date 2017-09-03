package com.anythingintellect.mydeliveries.util;

import android.widget.Toast;

import com.anythingintellect.mydeliveries.BaseTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.assertEquals;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ToasterTest extends BaseTest {

    private Toaster toaster;

    @Before
    public void setup() {
        toaster = new ToasterImpl(RuntimeEnvironment.application.getApplicationContext());
    }

    // showLong
    // Should show long toast
    @Test
    public void testShowLong_ShouldShowLongToast() {
        String msg = "test";
        toaster.showLong(msg);
        assertEquals(msg, ShadowToast.getTextOfLatestToast());
        assertEquals(Toast.LENGTH_LONG, ShadowToast.getLatestToast().getDuration());
    }

    // showShort
    // Should show short toast
    @Test
    public void testShowShort_ShouldShowShortToast() {
        String msg = "test";
        toaster.showShort(msg);
        assertEquals(msg, ShadowToast.getTextOfLatestToast());
        assertEquals(Toast.LENGTH_SHORT, ShadowToast.getLatestToast().getDuration());
    }
}
