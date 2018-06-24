package com.infosys.androidbarchartassignment;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.infosys.androidbarchartassignment.base.AbstractTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;



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

