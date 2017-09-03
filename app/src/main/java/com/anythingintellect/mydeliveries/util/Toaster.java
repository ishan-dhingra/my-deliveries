package com.anythingintellect.mydeliveries.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

class Toaster {

    private final Context context;

    public Toaster(Context context) {
        this.context = context;
    }

    public void showLong(String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void showShort(String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0 , 0);
        toast.show();
    }
}
