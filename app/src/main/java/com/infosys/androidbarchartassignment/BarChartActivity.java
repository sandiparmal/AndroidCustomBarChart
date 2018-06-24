package com.infosys.androidbarchartassignment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.infosys.androidbarchartassignment.mvp.base.BaseActivity;
import com.infosys.androidbarchartassignment.mvp.model.BarChartInteractorImpl;
import com.infosys.androidbarchartassignment.mvp.presenter.BarChartPresenterImpl;
import com.infosys.androidbarchartassignment.mvp.view.BarChartContract;
import com.infosys.androidbarchartassignment.retrofit.data.Graph;
import com.infosys.androidbarchartassignment.retrofit.data.GraphResponse;
import com.infosys.androidbarchartassignment.ui.BarChartView;
import com.infosys.androidbarchartassignment.utils.ConnectivityUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;


public class BarChartActivity extends BaseActivity implements BarChartContract.BarChartView {

    private static final String EXTENDED_URL = "https://api.myjson.com/bins/";
    private Unbinder mUnbinder;
    private BarChartPresenterImpl mBarChartPresenter;

    @BindView(R.id.graphLayout)
    RelativeLayout graphView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    /**
     * Layout resource to be inflated
     *
     * @return layout resource
     */
    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    /**
     * Initialisations
     *
     * @param savedState Bundle
     */
    @Override
    protected void init(Bundle savedState) {
        // binding ButterKnife
        mUnbinder = butterknife.ButterKnife.bind(this);

        // Initialize Presenter
        mBarChartPresenter = new BarChartPresenterImpl(new BarChartInteractorImpl());
        mBarChartPresenter.attach(this);

        initiateFetchRequest();
    }

    /**
     * initiate data fetch request if network is present else
     * show toast message
     */
    private void initiateFetchRequest() {
        ConnectivityUtils objConnectivityUtils = new ConnectivityUtils();

        // check if network is available
        boolean isNetwork = objConnectivityUtils.isNetworkAvailable(this);
        if (isNetwork) {
            mBarChartPresenter.fetchBarChartDetails(EXTENDED_URL);
        } else {
            // network is not available
            Toast.makeText(this, R.string.network_error, Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void onGetDataSuccess(GraphResponse graphResponse) {
        Log.d("BarChartActivity", graphResponse.toString());
        ArrayList<Graph> arrayList = graphResponse.graph;
        BarChartView barChartView = new BarChartView(this, arrayList);
        barChartView.setRxRadiusCorner(25f);
        barChartView.setRyRadiusCorner(25f);
        barChartView.setTextLabelColor(Color.DKGRAY);
        barChartView.setLineColor(ContextCompat.getColor(this,R.color.lineBarColor));
        graphView.addView(barChartView);
    }

    @Override
    public void onGetDataFailure(String message) {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mUnbinder.unbind();
        mBarChartPresenter.detach();
    }
}
