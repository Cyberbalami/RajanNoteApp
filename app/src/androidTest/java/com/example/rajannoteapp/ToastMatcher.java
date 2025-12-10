package com.example.rajannoteapp;

import android.os.IBinder;
import android.view.WindowManager;
import android.view.View;

import androidx.test.espresso.Root;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ToastMatcher extends TypeSafeMatcher<Root> {

    @Override
    public void describeTo(Description description) {
        description.appendText("is toast");
    }

    @Override
    public boolean matchesSafely(Root root) {
        // Check if this window is a toast OR a system overlay toast
        int type = root.getWindowLayoutParams().get().type;

        // Accept both toast types:
        if (type == WindowManager.LayoutParams.TYPE_TOAST ||
                type == WindowManager.LayoutParams.TYPE_APPLICATION_PANEL) {

            IBinder windowToken = root.getDecorView().getWindowToken();
            IBinder appToken = root.getDecorView().getApplicationWindowToken();

            // Toasts have windowToken == appToken
            return windowToken == appToken;
        }

        return false;
    }
}
