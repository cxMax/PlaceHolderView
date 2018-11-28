package com.cxmax.placeholderview.library.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.cxmax.placeholderview.library.PlaceHolderView;
import com.cxmax.placeholderview.library.util.Utils;


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

    @NonNull private HashMap<View, PlaceHolder> children = new HashMap<>();
    @NonNull private OnAttachStateChangeListener listener = new OnAttachStateChangeListener() {

        /**
         * when call {@link PlaceHolderView#bind(View)} , the below will be work
         * @param v
         */
        @Override
        public void onViewAttachedToWindow(View v) {

        }

        /**
         * when call {@link IPlaceHolderManager#release()} ()} , the below will be work
         * @param v
         */
        @Override
        public void onViewDetachedFromWindow(View v) {
            releaseAllRegisteredPlaceHolders();
        }
    };

    public PlaceHolderLayout(Context context) {
        this(context, null);
    }

    public PlaceHolderLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlaceHolderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addOnAttachStateChangeListener(listener);
    }

    public void addPlaceHolders(@NonNull Collection<Class<? extends PlaceHolder>> collection) {
        try {
            for (Class<? extends PlaceHolder> clz : collection) {
                PlaceHolder holder = clz.newInstance();
                holder.setContext(getContext());
                View child = holder.getPlaceHolder();
                children.put(child, holder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showPlaceHolder(@NonNull Class<? extends PlaceHolder> clz, @Nullable IPlaceHolderManager.IExpose expose) {
        if (getChildCount() > 0) {
            View child = getChildAt(0);
            notifyChildDetach(child);
            removeView(child);
        }
        for (PlaceHolder holder : children.values()) {
            if (holder.getClass().equals(clz)) {
                View child = Utils.getKeyByValue(children, holder);
                if (child != null) {
                    addView(child);
                    notifyChildAttach(holder, child);
                    if (expose != null) {
                        expose.expose(child);
                    }
                }
            }
        }
    }

    private void releaseAllRegisteredPlaceHolders() {
        if (!children.isEmpty()) {
            for (View child : children.keySet()) {
                notifyChildDetach(child);
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
