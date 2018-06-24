package com.infosys.androidbarchartassignment.mvp.model;

import android.support.annotation.NonNull;
import android.util.Log;


import com.infosys.androidbarchartassignment.App;
import com.infosys.androidbarchartassignment.R;
import com.infosys.androidbarchartassignment.retrofit.data.GraphResponse;
import com.infosys.androidbarchartassignment.retrofit.service.NetworkService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class BarChartInteractorImpl implements BarChartInteractor {

    /**
     * Collects all subscriptions to un-subscribe later
     */
    @NonNull
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    /**
     * Request network call and get data from REST url
     *
     * @param URL                   String
     * @param onFetchFinishListener OnFetchFinishListener
     */
    @Override
    public void fetchBarChartDetails(String URL, OnFetchFinishListener onFetchFinishListener) {

        try{
            //configure Retrofit using Retrofit Builder
            NetworkService networkService = App.getClient(URL).create(NetworkService.class);

            mCompositeDisposable.add(networkService.getGraphValues()
                    .subscribeOn(Schedulers.io()) // “work” on io thread
                    .observeOn(AndroidSchedulers.mainThread()) // “listen” on UIThread
                    .map(new Function<GraphResponse, GraphResponse>() {
                        @Override
                        public GraphResponse apply(
                                @io.reactivex.annotations.NonNull final GraphResponse graphResponse) throws Exception {
                            // we want to have the country and not the wrapper object
                            return graphResponse;
                        }
                    })
                    .subscribe(new Consumer<GraphResponse>() {
                        @Override
                        public void accept(
                                @io.reactivex.annotations.NonNull final GraphResponse graphResponse)
                                throws Exception {
                            onFetchFinishListener.onFetchingSuccess(graphResponse);
                        }
                    })
            );
        } catch (Exception e){
            Log.d("Error : ", e.toString());
            onFetchFinishListener.onFetchingFailure(App.getContext().getString(R.string.fetch_failed_message));
        }


    }

    /**
     * Dispose CompositeDisposable
     */
    @Override
    public void clearCompositeDisposable() {
        mCompositeDisposable.clear();
    }

}
