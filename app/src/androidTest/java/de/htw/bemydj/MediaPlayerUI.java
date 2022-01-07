package de.htw.bemydj;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MediaPlayerUI {

    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void displayPlayerButtons() {
        onView(withId(R.id.musicButton)).check(matches(isDisplayed()));
        onView(withId(R.id.btnPlay)).check(matches(isDisplayed()));
        onView(withId(R.id.btnRew)).check(matches(isDisplayed()));
        onView(withId(R.id.btnFor)).check(matches(isDisplayed()));
        onView(withId(R.id.btnPause)).check(matches(not(isDisplayed())));
    }
}
