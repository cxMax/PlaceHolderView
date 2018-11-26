package com.cxmax.placeholderview.library.core;

import android.support.annotation.MainThread;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 2018/11/26.
 */
public interface IPlaceHolderPool {

    @MainThread
    public void showPlaceHolder(Class<? extends PlaceHolder> clz);

}
