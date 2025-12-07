package com.example.rajannoteapp;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.annotation.Config;
import org.robolectric.RobolectricTestRunner;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 33)
public class FileStorageTest {

    private FileStorage storage;
    private Context context;

    @Before
    public void setup() {
        context = ApplicationProvider.getApplicationContext();
        storage = new FileStorage(context);

        // Clean up old test files before running new ones
        deleteIfExists("TestNote.txt");
        deleteIfExists("Title1.txt");
        deleteIfExists("Title2.txt");
        deleteIfExists("DeleteMe.txt");
    }

    private void deleteIfExists(String filename) {
        File dir = context.getFilesDir();
        File file = new File(dir, filename);
        if (file.exists()) file.delete();
    }

    @Test
    public void testSaveAndReadNote() {
        storage.saveNote("TestNote", "Hello World");

        String result = storage.getNoteContent("TestNote");

        assertEquals("Hello World", result);
    }

    @Test
    public void testGetAllTitles() {
        storage.saveNote("Title1", "A");
        storage.saveNote("Title2", "B");

        List<String> titles = storage.getAllNotesTitles();

        assertTrue(titles.contains("Title1"));
        assertTrue(titles.contains("Title2"));
        assertEquals(2, titles.size());
    }

    @Test
    public void testDeleteNote() {
        storage.saveNote("DeleteMe", "X");

        storage.deleteNote("DeleteMe");

        String result = storage.getNoteContent("DeleteMe");

        // Should return empty string when not found
        assertEquals("", result);

        // Confirm the file is actually deleted
        File deletedFile = new File(context.getFilesDir(), "DeleteMe.txt");
        assertFalse(deletedFile.exists());
    }
}
