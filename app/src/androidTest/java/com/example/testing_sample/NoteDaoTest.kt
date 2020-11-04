package com.example.testing_sample

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testing_sample.database.NoteDao
import com.example.testing_sample.database.NoteDatabase
import com.example.testing_sample.util.TestUtil.Companion.TEST_NOTE_1
import com.example.testing_sample.util.waitForValue
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NoteDaoTest {

    private lateinit var noteDatabase: NoteDatabase
    private lateinit var noteDao: NoteDao

    private val TEST_TITLE = "Title #1"
    private val TEST_CONTENT = "Content #1"
    private val TEST_TIMESTAMP = "May-2020"

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        /**Using an in-memory database because the information stored here disappears when the
         *process is killed.*/

        noteDatabase = Room.inMemoryDatabaseBuilder(context, NoteDatabase::class.java)
            /**Allowing main thread queries, just for testing.*/
            .allowMainThreadQueries()
            .build()

        noteDao = noteDatabase.noteDao()
    }

    @After
    fun closeDb() {
        noteDatabase.close()
    }

    @Test
    fun insertReadDelete() {
        //Given
        val note = TEST_NOTE_1

        //insert
        noteDao.insertNote(note).blockingGet() //wait until inserted

        //read
        val insertedNotes = noteDao.getNotes().waitForValue()

        assertNotNull(insertedNotes)
        assertEquals(note.content, insertedNotes[0].content)
        assertEquals(note.timestamp, insertedNotes[0].timestamp)
        assertEquals(note.title, insertedNotes[0].title)

        note.id = insertedNotes[0].id
        assertEquals(note, insertedNotes[0])

        //delete
        noteDao.deleteNote(note.id).blockingGet()
        //confirm the database is empty
        val insertedNote = noteDao.getNotes().waitForValue()
        assertEquals(0, insertedNote.size)
    }

    @Test
    fun insertReadUpdateReadDelete() {
        //Given
        val note = TEST_NOTE_1

        //insert
        noteDao.insertNote(note).blockingGet() //wait until inserted

        //read
        val insertedNotes = noteDao.getNotes().waitForValue()

        assertNotNull(insertedNotes)
        assertEquals(note.content, insertedNotes[0].content)
        assertEquals(note.timestamp, insertedNotes[0].timestamp)
        assertEquals(note.title, insertedNotes[0].title)

        note.id = insertedNotes[0].id
        assertEquals(note, insertedNotes[0])

        //update
        note.title = TEST_TITLE
        note.content = TEST_CONTENT
        note.timestamp = TEST_TIMESTAMP
        noteDao.updateNote(note).blockingGet()

        //read
        val updatedNotes = noteDao.getNotes().waitForValue()
        assertEquals(TEST_TITLE, updatedNotes[0].title)
        assertEquals(TEST_CONTENT, updatedNotes[0].content)
        assertEquals(TEST_TIMESTAMP, updatedNotes[0].timestamp)

        note.id = updatedNotes[0].id
        assertEquals(note, updatedNotes[0])

        //delete
        noteDao.deleteNote(note.id).blockingGet()
        //confirm the database is empty
        val insertedNote = noteDao.getNotes().waitForValue()
        assertEquals(0, insertedNote.size)
    }

    @Test(expected = NullPointerException::class)
    @Throws(Exception::class)
    fun insert_null_title_throw_exception() {
        val note = TEST_NOTE_1
        note.title = null!!
        noteDao.insertNote(note).blockingGet()
    }

    @Test(expected = NullPointerException::class)
    @Throws(Exception::class)
    fun update_null_title_throw_exception() {
        val note = TEST_NOTE_1
        noteDao.insertNote(note).blockingGet()
        val insertedNote = noteDao.getNotes().waitForValue()
        assertNotNull(insertedNote)

        //update
        val updatedNote = noteDao.getNotes().waitForValue()[0]
        updatedNote.title = null!!
        noteDao.updateNote(updatedNote).blockingGet()
    }
}