package com.infosys.androidbarchartassignment.mvp.presenter;

import com.infosys.androidbarchartassignment.mvp.model.BarChartInteractor;
import com.infosys.androidbarchartassignment.mvp.model.BarChartInteractorImpl;
import com.infosys.androidbarchartassignment.mvp.view.BarChartContract;
import com.infosys.androidbarchartassignment.retrofit.data.GraphResponse;

public class BarChartPresenterImpl implements BarChartContract.BarChartPresenter, BarChartInteractorImpl.OnFetchFinishListener {

    // initialization
    public BarChartContract.BarChartView barChartView;
    private final BarChartInteractor barChartInteractor;

    public BarChartPresenterImpl(BarChartInteractor barChartInteractor) {

        this.barChartInteractor = barChartInteractor;
    }

    /**
     * Request network call and get data from REST url
     *
     * @param extendedURL String
     */
    @Override
    public void fetchBarChartDetails(String extendedURL) {
        // show progress bar
        barChartView.showWait();
        // call interactor to fetch barChart details
        barChartInteractor.fetchBarChartDetails(extendedURL, this);
    }

    /**
     * Trigger when barChart details fetching success
     *
     * @param barChartResponse barChartResponse
     */
    @Override
    public void onFetchingSuccess(GraphResponse barChartResponse) {
        if(barChartView != null){
            barChartView.hideWait();
            barChartView.onGetDataSuccess(barChartResponse);
        }
    }

    /**
     * Trigger when BarChart details fetching failure
     *
     * @param message String
     */
    @Override
    public void onFetchingFailure(String message) {
        if(barChartView != null){
            barChartView.hideWait();
            barChartView.onGetDataFailure(message);
        }

    }

    /**
     * Called when the view is created and wants to attach its presenter
     *
     * @param view view
     */
    @Override
    public void attach(BarChartContract.BarChartView view) {
        barChartView = view;
    }

    /**
     * Called when the view is destroyed to get rid of its presenter
     */
    @Override
    public void detach() {
        if (barChartView != null) {
            barChartView = null;
        }
        barChartInteractor.clearCompositeDisposable();
    }
}
