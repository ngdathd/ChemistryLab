package com.ngdat.chemistrylab.chemical.liquid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.ngdat.chemistrylab.chemical.BaseSubstanceManager;
import com.ngdat.chemistrylab.chemical.Substance;
import com.ngdat.chemistrylab.customview.laboratory_instrument.holder_instrument.LaboratoryHolderInstrument;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 8/22/2016.
 */
public class LiquidManager extends BaseSubstanceManager {
    public static final String TAG = "LiquidManager";
    private Point holderArrPoint[];

    public LiquidManager(Context context, LaboratoryHolderInstrument holder) {
        super(context, holder);
        this.holderArrPoint = holder.getArrayPoint();
        this.currentHeight = 0;
    }

    @Override
    public Liquid addSubstance(Substance liquid) {
        Liquid result = (Liquid) super.addSubstance(liquid);
        if (result == liquid) {
            result.setWidth(width);
            result.setManager(this);
            result.calculateMaxMoleInHolder(maxHeight, width);
            holder.checkReaction(result);
        }
        return result;
    }

    @Override
    public void drawAllSubstances(Canvas canvas, Paint paint) {
        int startIndex = (int) maxHeight;
        int endIndex = startIndex - ((int) currentHeight);
        paint.setStyle(Paint.Style.STROKE);
        for (int i = listSubstances.size() - 1; i >= 0; i--) {
            Liquid aLiquid = getSubstance(i);
            paint.setColor(aLiquid.getColor());
            paint.setAlpha(aLiquid.getAlpha());
            for (int j = startIndex; j > endIndex; j--) {
                Point line = holderArrPoint[j];
                canvas.drawLine(line.x, j, line.y, j, paint);
            }
        }
    }

    @Override
    public Liquid getSubstance(int index) {
        return (Liquid) super.getSubstance(index);
    }

    @Override
    public BaseSubstanceManager getSuitableSubstanceManager(LaboratoryHolderInstrument holder) {
        return holder.getLiquidManager();
    }

    @Override
    protected List<Substance> takeSubstanceByHeight(double height) {
        if(height >= this.currentHeight){
            return clearAllSubstances();
        }
        List<Substance> result = new ArrayList<>(listSubstances.size());
        double ratio = height / currentHeight;
        int size = listSubstances.size();
        for (int i = size - 1; i >= 0; i--) {
            Liquid aLiquid = getSubstance(i);
            result.add(aLiquid.split(aLiquid.getMole() * ratio));
            aLiquid.getTip().update();
        }
        return result;
    }

    @Override
    public void onHeightChange(Substance liquid, double valueChange) {
        this.currentHeight += valueChange;
        super.onHeightChange(liquid, valueChange);
    }

    @Override
    public Point getSurfaceLine(Substance substance) {
        return holderArrPoint[(int) (maxHeight - currentHeight)];
    }

    @Override
    public double getYTop(Substance substance) {
        return 0;
    }

    //    public List<Liquid> checkReaction() {
//        List<Liquid> listLiquidResult = new ArrayList<>();
//        for (int i = 0; i < liquidCount - 1; i++) {
//            for (int j = 1; j < liquidCount; j++) {
//                Liquid liquid1 = listLiquid.get(i);
//                Liquid liquid2 = listLiquid.get(j);
////                Log.i(TAG,"Reaction: "+ liquid1.toString() + " " + liquid2.toString());
//                ReactionEquation equation = DatabaseManager.getInstance(context).findReaction(liquid1, liquid2);
//                if (equation != null) {
////                    Log.i(TAG, "equation: " + equation.getResultSubstances().toString());
//                    onReactionListener.reactionHappened(equation.getEquation());
//                    listLiquid.remove(liquid1);
//                    listLiquid.remove(liquid2);
//                    removeLiquidPath(liquid1.getHeight()+liquid2.getHeight());
//                    liquidCount -= 2;
//                    currentHeight -= (liquid1.getHeight() + liquid2.getHeight());
//                    List<Substance> listResultSubstance = equation.getResultSubstances();
//                    int length = listResultSubstance.size();
//                    for (int k = 0; k < length; k++) {
//                        Substance temp = listResultSubstance.get(k);
//                        if (temp instanceof Liquid) {
//                            listLiquidResult.add((Liquid) temp);
//                        }
//                    }
//                }
//            }
//        }
//        return listLiquidResult;
//    }
}
