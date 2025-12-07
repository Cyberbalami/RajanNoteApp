package com.example.rajannoteapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ViewNoteActivityTest {

    @Rule
    public ActivityScenarioRule<ViewNoteActivity> rule =
            new ActivityScenarioRule<>(new Intent(
                    ApplicationProvider.getApplicationContext(),
                    ViewNoteActivity.class
            ).putExtra("title", "Sample Title")
                    .putExtra("content", "Sample Content"));

    @Test
    public void testDisplaysCorrectNote() {
        onView(withId(R.id.tvNoteTitle)).check(matches(withText("Sample Title")));
        onView(withId(R.id.tvNoteContent)).check(matches(withText("Sample Content")));
    }

    @Test
    public void testToolbarBackButtonClosesActivity() {
        onView(withContentDescription("Navigate up")).perform(click());
    }
}
