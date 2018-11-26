package com.cxmax.placeholderview.library.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import com.cxmax.placeholderview.library.PlaceHolderView;


/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 2018/11/26.
 */
public class PlaceHolderPool implements IPlaceHolderPool {

    @NonNull private PlaceHolderLayout layout;
    @NonNull private PlaceHolderView.Config config;
    @NonNull private WrapperContext wrapperContext;

    public PlaceHolderPool(@NonNull PlaceHolderView.Config config, @NonNull WrapperContext wrapperContext) {
        this.config = config;
        this.wrapperContext = wrapperContext;
        initPlaceHolderLayout();
    }

    private void initPlaceHolderLayout() {
        Context context = wrapperContext.getContext();
        View oldContent = wrapperContext.getOldContent();
        ViewGroup parent = wrapperContext.getParent();
        layout = new PlaceHolderLayout(context);
        layout.addPlaceHolders(config.getPlaceHolders());
        ViewGroup.LayoutParams oldLayoutParams = oldContent.getLayoutParams();
        if (parent != null) {
            parent.addView(layout, wrapperContext.getChildIndex(), oldLayoutParams);
        }
    }

    @Override
    public void showPlaceHolder(Class<? extends PlaceHolder> clz) {
        layout.showPlaceHolder(clz);
    }
}
