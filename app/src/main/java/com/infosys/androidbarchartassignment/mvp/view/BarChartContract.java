package com.infosys.androidbarchartassignment.mvp.view;


import com.infosys.androidbarchartassignment.mvp.base.BasePresenter;
import com.infosys.androidbarchartassignment.mvp.base.BaseView;
import com.infosys.androidbarchartassignment.retrofit.data.GraphResponse;

public class BarChartContract {

    public interface BarChartView extends BaseView {

        /**
         * Update the list items in list view through adapter
         */
        void onGetDataSuccess(GraphResponse graphResponse);

        /**
         * Show toast on data fetching failure
         */
        void onGetDataFailure(String message);
    }

    public interface BarChartPresenter extends BasePresenter {

        /**
         * Request network call and get data from REST url
         *
         * @param extendedURL String
         */
        void fetchBarChartDetails(String extendedURL);
    }
}
