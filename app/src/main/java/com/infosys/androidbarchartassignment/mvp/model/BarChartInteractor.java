package com.infosys.androidbarchartassignment.mvp.model;

import com.infosys.androidbarchartassignment.retrofit.data.GraphResponse;

public interface BarChartInteractor {

    /**
     * OnFetchListener will trigger onFetchingSuccess and onFetchingFailure depends on result
     */
    interface OnFetchFinishListener {
        /**
         * Trigger when barChart details fetching success
         *
         * @param graphResponse Graph
         */
        void onFetchingSuccess(GraphResponse graphResponse);

        /**
         * Trigger when barChart details fetching failure
         *
         * @param message String
         */
        void onFetchingFailure(String message);
    }

    /**
     * Request network call and get data from REST url
     *
     * @param URL String
     */
    void fetchBarChartDetails(String URL, BarChartInteractor.OnFetchFinishListener onFetchListener);

    /**
     * Dispose CompositeDisposable
     */
    void clearCompositeDisposable();
}
