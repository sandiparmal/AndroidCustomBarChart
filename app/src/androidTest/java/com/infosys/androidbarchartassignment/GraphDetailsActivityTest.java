package com.infosys.androidbarchartassignment;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.RelativeLayout;

import com.infosys.androidbarchartassignment.base.AbstractTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;



@RunWith(AndroidJUnit4.class)
@LargeTest
public class GraphDetailsActivityTest extends AbstractTest {


    @Rule
    public ActivityTestRule<BarChartActivity> mActivityRule = new ActivityTestRule<>(BarChartActivity.class);

    @Test
    public void testRelativeWithGraph() throws InterruptedException {

        // Check that Relative Layout
        RelativeLayout relativeLayout = (RelativeLayout) mActivityRule.getActivity().findViewById(R.id.graphLayout);
        //One improvement would be not to rely on the real network query, but mock the response (Mockito etc...) to avoid depending on network related stuff.
        waitForCondition(() -> relativeLayout != null , 3000);
        assertNotNull(relativeLayout);


    }

}

