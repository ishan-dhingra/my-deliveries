package com.anythingintellect.mydeliveries.util;

import android.databinding.BindingAdapter;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by ishan.dhingra on 04/09/17.
 */

public class BindingUtil {

    @BindingAdapter("bind:visible")
    public static void bindVisible(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("bind:imgUrl")
    public static void bindImageUrl(SimpleDraweeView view, String imgUrl) {
        view.setImageURI(imgUrl);
    }

}
