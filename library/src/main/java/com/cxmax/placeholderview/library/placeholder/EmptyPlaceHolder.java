package com.cxmax.placeholderview.library.placeholder;

import android.content.Context;
import android.view.View;
import com.cxmax.placeholderview.library.R;
import com.cxmax.placeholderview.library.core.PlaceHolder;


/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 2018/11/26.
 */
public class EmptyPlaceHolder extends PlaceHolder {
    @Override
    public int onCreateView() {
        return R.layout.placeholder_empty;
    }

    @Override
    public void onViewCreate(Context context, View view) {

    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {

    }
}
