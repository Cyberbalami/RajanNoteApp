package com.example.rajannoteapp;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class StorageManagerTest {

    @Mock
    Context context;

    @Mock
    SharedPreferences prefs;

    @Mock
    SharedPreferences.Editor editor;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(prefs);
        when(prefs.edit()).thenReturn(editor);
        when(editor.putString(anyString(), anyString())).thenReturn(editor);
    }

    @Test
    public void testSetStorageModeShared() {
        StorageManager.setStorageMode(context, true);
        verify(editor).putString("storage_mode", "shared");
        verify(editor).apply();
    }

    @Test
    public void testSetStorageModeFile() {
        StorageManager.setStorageMode(context, false);
        verify(editor).putString("storage_mode", "file");
        verify(editor).apply();
    }

    @Test
    public void testIsUsingSharedDefault() {
        when(prefs.getString("storage_mode", "shared")).thenReturn("shared");
        assertTrue(StorageManager.isUsingShared(context));
    }

    @Test
    public void testIsUsingSharedFileMode() {
        when(prefs.getString("storage_mode", "shared")).thenReturn("file");
        assertFalse(StorageManager.isUsingShared(context));
    }
}
