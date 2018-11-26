package com.cxmax.placeholderview.library.core;

import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.io.Serializable;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 2018/11/26.
 */
public abstract class PlaceHolder implements Serializable {

    private View placeHolder;
    @Nullable private Context context;

    @NonNull public View getPlaceHolder() {
        @LayoutRes int resId = onCreateView();
        if (placeHolder == null) {
            placeHolder = View.inflate(context, resId, null);
        }
        this.context = placeHolder.getContext();
        onViewCreate(context, placeHolder);
        return placeHolder;
    }

    public void setContext(@Nullable Context context) {
        this.context = context;
    }

    /**
     * @return PlaceHolder Layout Id
     */
    @CheckResult
    @LayoutRes
    public abstract int onCreateView();

    public abstract void onViewCreate(Context context, View view);

    public abstract void onAttach();

    /**
     * todo onDetach释放时机
     */
    public abstract void onDetach();
}
