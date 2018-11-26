package com.cxmax.placeholderview.library;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import com.cxmax.placeholderview.library.core.PlaceHolder;
import com.cxmax.placeholderview.library.core.PlaceHolderPool;
import com.cxmax.placeholderview.library.core.WrapperContext;
import com.cxmax.placeholderview.library.core.WrapperContextTransformer;


import java.util.Arrays;
import java.util.HashSet;

/**
 * @describe : ErrorView,EmptyView,LoadingView以及其他一些公共占位符的统一封装
 * @usage :
 *          1.注册 ：
 *            new PlaceHolderView.Config()
 *                 .addPlaceHolder(ErrorPlaceHolder.class, EmptyPlaceHolder.class, LoadingPlaceHolder.class)
 *                 .build().register(this);
 *          2.展示 ：
 *            PlaceHolderPool.showPlaceHolder(LoadingPlaceHolder.class);
 *            PlaceHolderPool.showPlaceHolder(EmptyPlaceHolder.class);
 *            et...
 *
 *          todo list :
 *          1. 占位符，点击事件callback的回调还没有加入。 比如一些retry操作
 * <p>
 * </p>
 * Created by caixi on 2018/11/23.
 */
public class PlaceHolderView implements IPlaceHolderView{

    @NonNull private Config config;

    public PlaceHolderView(@NonNull Config config) {
        this.config = config;
    }

    @NonNull
    @Override
    public PlaceHolderPool register(@NonNull Activity activity) {
        WrapperContext context = new WrapperContextTransformer().build(activity);
        return new PlaceHolderPool(config, context);
    }

    @NonNull
    @Override
    public PlaceHolderPool register(@NonNull Fragment fragment) {
        WrapperContext context = new WrapperContextTransformer().build(fragment.getActivity());
        return new PlaceHolderPool(config, context);
    }

    @NonNull
    @Override
    public PlaceHolderPool register(@NonNull View root) {
        WrapperContext context = new WrapperContextTransformer().build(root);
        return new PlaceHolderPool(config, context);
    }


    public static class Config {
        @NonNull private HashSet<Class<? extends PlaceHolder>> placeHolders = new HashSet<>();

        public Config addPlaceHolder(@NonNull Class<? extends PlaceHolder>... holders) {
            placeHolders.addAll(Arrays.asList(holders));
            return this;
        }

        @NonNull
        public HashSet<Class<? extends PlaceHolder>> getPlaceHolders() {
            return placeHolders;
        }

        @MainThread
        public PlaceHolderView build() {
            return new PlaceHolderView(this);
        }
    }

}
