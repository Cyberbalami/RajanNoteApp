package com.example.rajannoteapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;


import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> rule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testEmptyStateVisibleWhenNoNotes() {
        onView(withId(R.id.txtEmpty)).check(matches(isDisplayed()));
        onView(withId(R.id.listViewNotes)).check(matches(withEffectiveVisibility(Visibility.GONE)));
    }

    @Test
    public void testAddNoteMenuOpensAddNoteActivity() {
        Intents.init();

        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());
        onView(withText("Add Note")).perform(click());

        Intents.intended(hasComponent(AddNoteActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void testDeleteNoteMenuOpensDeleteNoteActivity() {
        Intents.init();

        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());
        onView(withText("Delete Note")).perform(click());

        Intents.intended(hasComponent(DeleteNoteActivity.class.getName()));
        Intents.release();
    }
}
