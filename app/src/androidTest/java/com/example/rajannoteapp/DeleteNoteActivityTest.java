package com.example.rajannoteapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DeleteNoteActivityTest {

    @Rule
    public ActivityScenarioRule<DeleteNoteActivity> rule =
            new ActivityScenarioRule<>(DeleteNoteActivity.class);

    @Test
    public void testEmptyStateVisible() {
        onView(withId(R.id.txtEmptyDelete)).check(matches(isDisplayed()));
        onView(withId(R.id.listViewDeleteNotes))
                .check(matches(withEffectiveVisibility(Visibility.GONE)));
    }
}
