package com.anythingintellect.mydeliveries.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public class ToasterImpl implements Toaster {

    private final Context context;

    public ToasterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void showLong(String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void showShort(String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0 , 0);
        toast.show();
    }

    @Override
    public void showLong(int string) {
        showLong(context.getString(string));
    }

    @Override
    public void showShort(int string) {
        showShort(context.getString(string));
    }
}
