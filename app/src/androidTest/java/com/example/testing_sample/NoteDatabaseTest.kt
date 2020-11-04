package com.example.testing_sample

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testing_sample.database.NoteDao
import com.example.testing_sample.database.NoteDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class NoteDatabaseTest {

    //system under test
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var noteDao: NoteDao

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

}