package com.ngdat.chemistrylab.observer;

import android.graphics.Point;

import com.ngdat.chemistrylab.chemical.Substance;

/**
 * Created by Admin on 9/8/2016.
 */
public interface ContainerCallBack {
    void onHeightChange(Substance substance, double valueChange);
    void onSubstanceRemoved(Substance substance);

    Point getSurfaceLine(Substance substance);
    double getYTop(Substance substance);
    double getAvailableHeight();
    boolean isClosedVessel();
}
