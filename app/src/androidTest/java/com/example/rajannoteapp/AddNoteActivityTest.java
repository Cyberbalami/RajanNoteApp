package com.example.rajannoteapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.not;

import android.app.Activity;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AddNoteActivityTest {

    @Rule
    public ActivityScenarioRule<AddNoteActivity> rule =
            new ActivityScenarioRule<>(AddNoteActivity.class);

    @Test
    public void testViewsAreVisible() {
        onView(withId(R.id.txtTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.txtContent)).check(matches(isDisplayed()));
        onView(withId(R.id.btnSave)).check(matches(isDisplayed()));
    }

    @Test
    public void testEmptyWarningToastShown() {
        onView(withId(R.id.btnSave)).perform(click());

        onView(withText(R.string.empty_warning))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void testSuccessfulSave() {
        onView(withId(R.id.txtTitle)).perform(typeText("MyTitle"), closeSoftKeyboard());
        onView(withId(R.id.txtContent)).perform(typeText("MyContent"), closeSoftKeyboard());

        onView(withId(R.id.btnSave)).perform(click());

        onView(withText(R.string.note_saved))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }
}
