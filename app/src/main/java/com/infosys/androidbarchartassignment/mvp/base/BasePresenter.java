package com.infosys.androidbarchartassignment.mvp.base;

import com.infosys.androidbarchartassignment.mvp.view.BarChartContract;

public interface BasePresenter<BarChartView> {

    /**
     * Called when the view is created and wants to attach its presenter
     */
    void attach(BarChartContract.BarChartView view);

    /**
     * Called when the view is destroyed to get rid of its presenter
     */
    void detach();
}
