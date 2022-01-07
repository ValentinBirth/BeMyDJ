package de.htw.bemydj;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.GrantPermissionRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NetworkControlUI {

    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(MainActivity.class);

    @Rule
    public IntentsTestRule<MainActivity> activityTestRule =
            new IntentsTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION");


    @Test
    public void networkControlFragment(){
        ViewInteraction navBar = onView(
                allOf(withContentDescription("Navigationsleiste öffnen"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));

        ViewInteraction networkControlItem = onView(
                allOf(withId(R.id.nav_network_control),
                        childAtPosition(
                                allOf(withId(R.id.design_navigation_view),
                                        childAtPosition(
                                                withId(R.id.nav_view),
                                                0)),
                                2),
                        isDisplayed()));

        ViewInteraction networkControlTab = onView(
                allOf(withParent(allOf(withContentDescription("Network Control"),
                        withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));

        navBar.perform(click());
        networkControlItem.perform(click());
        networkControlTab.perform(click());
        onView(withId(R.id.fragment_networkControl)).check(matches(isDisplayed()));
    }

    @Test
    public void networkAvailablePeersFragment(){
        ViewInteraction navBar = onView(
                allOf(withContentDescription("Navigationsleiste öffnen"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));

        ViewInteraction networkControlItem = onView(
                allOf(withId(R.id.nav_network_control),
                        childAtPosition(
                                allOf(withId(R.id.design_navigation_view),
                                        childAtPosition(
                                                withId(R.id.nav_view),
                                                0)),
                                2),
                        isDisplayed()));

        ViewInteraction availableListenerTab = onView(
                allOf(withParent(allOf(withContentDescription("Available Listeners"),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));

        navBar.perform(click());
        networkControlItem.perform(click());
        availableListenerTab.perform(click());
        onView(withId(R.id.fragment_available_peers)).check(matches(isDisplayed()));
    }

    @Test
    public void networkGroupFragment(){
        ViewInteraction navBar = onView(
                allOf(withContentDescription("Navigationsleiste öffnen"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));

        ViewInteraction networkControlItem = onView(
                allOf(withId(R.id.nav_network_control),
                        childAtPosition(
                                allOf(withId(R.id.design_navigation_view),
                                        childAtPosition(
                                                withId(R.id.nav_view),
                                                0)),
                                2),
                        isDisplayed()));

        ViewInteraction groupTab = onView(
                allOf(withParent(allOf(withContentDescription("Group"),
                        withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));

        navBar.perform(click());
        networkControlItem.perform(click());
        groupTab.perform(click());
        onView(withId(R.id.fragment_group)).check(matches(isDisplayed()));
    }

    @Test
    public void networkControlSwitch(){
        ViewInteraction navBar = onView(
                allOf(withContentDescription("Navigationsleiste öffnen"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));

        ViewInteraction networkControlItem = onView(
                allOf(withId(R.id.nav_network_control),
                        childAtPosition(
                                allOf(withId(R.id.design_navigation_view),
                                        childAtPosition(
                                                withId(R.id.nav_view),
                                                0)),
                                2),
                        isDisplayed()));

        ViewInteraction networkControlTab = onView(
                allOf(withParent(allOf(withContentDescription("Network Control"),
                        withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));

        ViewInteraction startDiscoverySwitch = onView(
                allOf(withId(R.id.discoverPeersSwitch), withText("Search for listeners"),
                        withParent(withParent(withId(R.id.view_pager))),
                        isDisplayed()));

        ViewInteraction connectionStatus = onView(withId(R.id.connectionStatus));

        navBar.perform(click());
        networkControlItem.perform(click());
        networkControlTab.perform(click());
        startDiscoverySwitch.check(matches(isNotChecked()));
        startDiscoverySwitch.perform(click());
        startDiscoverySwitch.check(matches(isChecked()));
        connectionStatus.check(matches(isDisplayed()));
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
