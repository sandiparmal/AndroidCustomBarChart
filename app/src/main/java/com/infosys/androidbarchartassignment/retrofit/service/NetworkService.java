package com.infosys.androidbarchartassignment.retrofit.service;

import com.infosys.androidbarchartassignment.retrofit.data.GraphResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NetworkService {

    // retrofit get call
    @GET("ipz6h")
    Observable<GraphResponse> getGraphValues();


}

