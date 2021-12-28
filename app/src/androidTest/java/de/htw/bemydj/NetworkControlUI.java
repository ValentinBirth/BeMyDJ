package de.htw.bemydj;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
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
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
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
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION");

    @Test
    public void networkCotrolUI() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigationsleiste öffnen"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));

        ViewInteraction navigationMenuItemView = onView(
                allOf(withId(R.id.nav_network_control),
                        childAtPosition(
                                allOf(withId(R.id.design_navigation_view),
                                        childAtPosition(
                                                withId(R.id.nav_view),
                                                0)),
                                2),
                        isDisplayed()));

        ViewInteraction networkControlTabTitle = onView(
                allOf(withText("NETWORK CONTROL"),
                        withParent(allOf(withContentDescription("Network Control"),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));

        ViewInteraction deviceName = onView(
                allOf(withId(R.id.userInfoStatus), withText("Device Name: Galaxy S20 5G"),
                        withParent(withParent(withId(R.id.view_pager))),
                        isDisplayed()));

        ViewInteraction startDiscoverySwitch = onView(
                allOf(withId(R.id.discoverPeersSwitch), withText("Search for listeners"),
                        withParent(withParent(withId(R.id.view_pager))),
                        isDisplayed()));

        ViewInteraction connectionStatus = onView(withId(R.id.connectionStatus));

        ViewInteraction availableListenersTab = onView(
                allOf(withContentDescription("Available Listeners"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                1),
                        isDisplayed()));

        ViewInteraction availableListenerTabTitle = onView(
                allOf(withText("AVAILABLE LISTENERS"),
                        withParent(allOf(withContentDescription("Available Listeners"),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));

        ViewInteraction availablePeersRecylerView = onView(
                allOf(withId(R.id.availablePeersRecyclerView),
                        withParent(allOf(withId(R.id.swipe_container_available),
                                withParent(withId(R.id.view_pager)))),
                        isDisplayed()));

        ViewInteraction groupTab = onView(
                allOf(withContentDescription("Group"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                2),
                        isDisplayed()));

        ViewInteraction groupPeersRecyclerView = onView(
                allOf(withId(R.id.groupPeersRecyclerView),
                        withParent(allOf(withId(R.id.swipe_container_group),
                                withParent(withId(R.id.view_pager)))),
                        isDisplayed()));

        ViewInteraction groupTabTitle = onView(
                allOf(withText("GROUP"),
                        withParent(allOf(withContentDescription("Group"),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));

        appCompatImageButton.perform(click());
        navigationMenuItemView.perform(click());
        networkControlTabTitle.check(matches(withText("NETWORK CONTROL")));
        deviceName.check(matches(isDisplayed()));
        startDiscoverySwitch.check(matches(isDisplayed()));
        connectionStatus.check(matches(isDisplayed()));
        connectionStatus.check(matches(withText("")));
        startDiscoverySwitch.perform(click());
        connectionStatus.check(matches(isDisplayed()));
        availableListenersTab.perform(click());
        availableListenerTabTitle.check(matches(withText("AVAILABLE LISTENERS")));
        availablePeersRecylerView.check(matches(isDisplayed()));
        groupTab.perform(click());
        groupTabTitle.check(matches(withText("GROUP")));
        groupPeersRecyclerView.check(matches(isDisplayed()));
        pressBack();
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
