package com.cxmax.placeholderview.library.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Collection;
import java.util.HashMap;

/**
 * @describe : 这个ViewGroup的作用是
 *                  1. 增加到目标布局的最顶层
 *                  2. 包含占位符的容器
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 2018/11/26.
 */
public class PlaceHolderLayout extends FrameLayout {

    // todo 只用一个pool
    @NonNull private HashMap<Class<? extends PlaceHolder>, PlaceHolder> pool = new HashMap<>();
    @NonNull private HashMap<View, PlaceHolder> children = new HashMap<>();

    public PlaceHolderLayout(Context context) {
        this(context, null);
    }

    public PlaceHolderLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlaceHolderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addOnAttachStateChangeListener(new OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {

            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                // todo 释放资源操作
            }
        });
    }

    public void addPlaceHolders(@NonNull Collection<Class<? extends PlaceHolder>> collection) {
        try {
            for (Class<? extends PlaceHolder> clz : collection) {
                PlaceHolder holder = clz.newInstance();
                holder.setContext(getContext());
                pool.put(clz, holder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showPlaceHolder(Class<? extends PlaceHolder> clz) {
        if (getChildCount() > 0) {
            View child = getChildAt(0);
            notifyChildDetach(child);
            removeView(child);
        }
        if (pool.containsKey(clz)) {
            PlaceHolder holder = pool.get(clz);
            if (holder != null) {
                View child = holder.getPlaceHolder();
                addView(child);
                notifyChildAttach(holder, child);
            }
        }
    }

    private void notifyChildAttach(PlaceHolder holder, View child) {
        assureChildDetach(child);
        children.put(child, holder);
        holder.onAttach();
    }

    private void notifyChildDetach(View child) {
        if (child != null && children.containsKey(child)) {
            PlaceHolder holder = children.get(child);
            if (holder != null) {
                holder.onDetach();
            }
        }
    }

    private void assureChildDetach(View child) {
        if (children.containsKey(child)) {
            notifyChildDetach(child);
        }
    }



}
