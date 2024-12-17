package com.ngdat.chemistrylab.effect_and_animation;

import com.ngdat.chemistrylab.customview.laboratory_instrument.holder_instrument.LaboratoryHolderInstrument;

/**
 * Created by Admin on 10/9/2016.
 */
public interface BaseAnimation {
    boolean run();
    void updateUI();
    LaboratoryHolderInstrument getHolder();
    void onStop();
    boolean isPaused();
}
