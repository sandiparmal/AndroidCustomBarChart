package com.infosys.androidbarchartassignment;

import android.support.test.runner.AndroidJUnit4;

import com.infosys.androidbarchartassignment.base.AbstractTest;
import com.infosys.androidbarchartassignment.mvp.model.BarChartInteractorImpl;
import com.infosys.androidbarchartassignment.mvp.presenter.BarChartPresenterImpl;
import com.infosys.androidbarchartassignment.mvp.view.BarChartContract;
import com.infosys.androidbarchartassignment.retrofit.data.Graph;
import com.infosys.androidbarchartassignment.retrofit.data.GraphResponse;
import com.infosys.androidbarchartassignment.retrofit.service.NetworkService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;

import io.reactivex.Observable;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(AndroidJUnit4.class)
public class MainActivityPresenterTest extends AbstractTest {

    private BarChartPresenterImpl objGraphPresenter;
    private BarChartContract.BarChartView barChartView;

    @Before
    public void setUp() {
        this.objGraphPresenter = spy(new BarChartPresenterImpl(new BarChartInteractorImpl()));
        barChartView = mock(BarChartContract.BarChartView.class);
    }

    @Test
    public void testAttach() {
        assertNull(objGraphPresenter.barChartView);

        objGraphPresenter.attach(barChartView);
        assertNotNull(objGraphPresenter.barChartView);
    }

    @Test
    public void testDetach() {
        objGraphPresenter.attach(barChartView);
        assertNotNull(objGraphPresenter.barChartView);

        objGraphPresenter.detach();
        assertNull(objGraphPresenter.barChartView);
    }

    @Test
    public void testFetchGraph() {

        // attach view to presenter
        objGraphPresenter.attach(barChartView);

        // Mock Network Services
        NetworkService networkService = Mockito.mock(NetworkService.class);

        // get mock results
        GraphResponse graphResponse = getMockGraphResults();

        //Test ok response
        when(networkService.getGraphValues()).thenReturn(Observable.just(graphResponse));
        objGraphPresenter.fetchBarChartDetails("https://api.myjson.com/bins/");
        waitFor(50);
        verify(barChartView, atLeastOnce()).showWait();
        waitFor(2500);
        ArgumentCaptor<GraphResponse> argument = ArgumentCaptor.forClass(GraphResponse.class);
        verify(objGraphPresenter).onFetchingSuccess(argument.capture());
        waitFor(50);
        verify(barChartView, atLeastOnce()).hideWait();
        waitFor(50);
        verify(barChartView).onGetDataSuccess(argument.capture());

    }

    private GraphResponse getMockGraphResults() {

        GraphResponse objGraphResponse = new GraphResponse();

        ArrayList<Graph> list = new ArrayList<>();
        Graph objGraph = new Graph();
        objGraph.setIndex(1);
        objGraph.setValue(1);
        list.add(objGraph);

        objGraph = new Graph();
        objGraph.setIndex(2);
        objGraph.setValue(2);
        list.add(objGraph);

        objGraph = new Graph();
        objGraph.setIndex(3);
        objGraph.setValue(3);
        list.add(objGraph);

        objGraph = new Graph();
        objGraph.setIndex(4);
        objGraph.setValue(4);
        list.add(objGraph);

        objGraph = new Graph();
        objGraph.setIndex(5);
        objGraph.setValue(5);
        list.add(objGraph);

        objGraph = new Graph();
        objGraph.setIndex(6);
        objGraph.setValue(6);
        list.add(objGraph);

        objGraph = new Graph();
        objGraph.setIndex(7);
        objGraph.setValue(7);
        list.add(objGraph);

        objGraph = new Graph();
        objGraph.setIndex(8);
        objGraph.setValue(8);
        list.add(objGraph);

        objGraph = new Graph();
        objGraph.setIndex(9);
        objGraph.setValue(9);
        list.add(objGraph);

        objGraph = new Graph();
        objGraph.setIndex(10);
        objGraph.setValue(10);
        list.add(objGraph);

        objGraphResponse.graph = list;

        return objGraphResponse;

    }

}
