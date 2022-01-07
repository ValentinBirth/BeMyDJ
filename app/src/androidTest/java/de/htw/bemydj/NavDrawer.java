package de.htw.bemydj;


import static androidx.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.content.ComponentName;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.htw.bemydj.ui.networkControlView.NetworkControlActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NavDrawer {

    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(MainActivity.class);

    @Rule
    public IntentsTestRule<MainActivity> activityTestRule =
            new IntentsTestRule<>(MainActivity.class);

    @Test
    public void  navDrawerNetworkActivity(){
        ViewInteraction navDrawer = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.toolbar),
                                childAtPosition(
                                        withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                        0)),
                        1),isDisplayed()));
        navDrawer.perform(click());

        ViewInteraction itemNetwork = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Network Control"),
                        withParent(allOf(withId(R.id.nav_network_control),
                                withParent(withId(R.id.design_navigation_view))))));
        itemNetwork.check(matches(isDisplayed()));
        itemNetwork.perform(click());
        intended(hasComponent(new ComponentName(ApplicationProvider.getApplicationContext(), NetworkControlActivity.class)));
    }

    @Test
    public void  navDrawerAppInstruction(){
        ViewInteraction navDrawer = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.toolbar),
                                childAtPosition(
                                        withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                        0)),
                        1),isDisplayed()));
        navDrawer.perform(click());

        ViewInteraction itemApp = onView(
                allOf(withId(R.id.design_menu_item_text), withText("App Instruction"),
                        withParent(allOf(withId(R.id.nav_app_info),
                                withParent(withId(R.id.design_navigation_view))))));
        itemApp.check(matches(isDisplayed()));
    }

    @Test
    public void  navDrawerHome(){
        ViewInteraction navDrawer = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.toolbar),
                                childAtPosition(
                                        withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                        0)),
                        1),isDisplayed()));
        navDrawer.perform(click());

        ViewInteraction itemHome = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Home"),
                        withParent(allOf(withId(R.id.nav_home),
                                withParent(withId(R.id.design_navigation_view))))));
        itemHome.check(matches(isDisplayed()));
        onView(withId(R.id.fragment_home)).check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
