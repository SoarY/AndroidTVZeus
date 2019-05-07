package com.soar.zeus.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.soar.zeus.view.LoadingView;


/**
 * NAME：YONG_
 * Created at: 2018/12/14 17
 * Describe:
 */
public class BindAdapter {

    @BindingAdapter("viewSwitcher")
    public static void setViewSwitcher(ViewSwitcher view, int whichChild) {
        view.setDisplayedChild(whichChild);
    }

    @BindingAdapter("loadState")
    public static void setLoadState(LoadingView view, LoadingView.State state) {
        view.notifyDataChanged(state);
    }

    @BindingAdapter("OnFocusChangeListener")
    public static void setOnFocusChangeListener(View view, View.OnFocusChangeListener listener) {
        view.setOnFocusChangeListener(listener);
    }

    @BindingAdapter(value = {"imgSource", "placeholder", "errorholder"}, requireAll = false)
    public static void setImgSource(ImageView view, String imgSource, Drawable placeholder, Drawable errorholder) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholder)
                .error(errorholder);
        Glide.with(view.getContext())
                .load(imgSource)
                .apply(options)
                .into(view);
    }
}
