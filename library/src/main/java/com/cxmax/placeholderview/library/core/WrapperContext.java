package com.cxmax.placeholderview.library.core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 2018/11/26.
 */
public class WrapperContext {

    private Context context;
    /**
     * 填充占位符的view的父布局
     */
    private ViewGroup parent;
    /**
     * 需要填充占位符的view
     */
    private View oldContent;
    /**
     * 在父布局中的index
     */
    private int childIndex;

    public WrapperContext(Context context, ViewGroup parent, View oldContent, int childIndex) {
        this.context = context;
        this.parent = parent;
        this.oldContent = oldContent;
        this.childIndex = childIndex;
    }

    public Context getContext() {
        return context;
    }

    public ViewGroup getParent() {
        return parent;
    }

    public View getOldContent() {
        return oldContent;
    }

    public int getChildIndex() {
        return childIndex;
    }
}
