package com.example.rajannoteapp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharedPrefStorageTest {

    @Mock
    private Context context;

    @Mock
    private SharedPreferences prefs;

    @Mock
    private SharedPreferences.Editor editor;

    private SharedPrefStorage storage;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Mock SharedPreferences behavior
        when(context.getSharedPreferences(anyString(), anyInt()))
                .thenReturn(prefs);

        when(prefs.edit()).thenReturn(editor);

        // Fluent editor chaining
        when(editor.putString(anyString(), anyString())).thenReturn(editor);
        when(editor.remove(anyString())).thenReturn(editor);

        storage = new SharedPrefStorage(context);
    }

    @Test
    public void testSaveNote() {
        storage.saveNote("Note1", "Hello");

        verify(editor).putString("Note1", "Hello");
        verify(editor).apply();
    }

    @Test
    public void testDeleteNote() {
        storage.deleteNote("Note1");

        verify(editor).remove("Note1");
        verify(editor).apply();
    }

    @Test
    public void testGetAllTitles() {
        // Use Object so SharedPreferences can hold any types
        Map<String, Object> mockMap = new HashMap<>();
        mockMap.put("Note1", "A");
        mockMap.put("Note2", "B");

        // FINAL FIX — use doReturn() to avoid wildcard capture errors
        doReturn(mockMap).when(prefs).getAll();

        List<String> titles = storage.getAllNotesTitles();

        assertEquals(2, titles.size());
        assertTrue(titles.contains("Note1"));
        assertTrue(titles.contains("Note2"));
    }

    @Test
    public void testGetNoteContent() {
        when(prefs.getString("Note1", "")).thenReturn("Saved content");

        String content = storage.getNoteContent("Note1");

        assertEquals("Saved content", content);
    }
}
