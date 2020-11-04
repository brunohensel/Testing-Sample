package com.example.testing_sample.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testing_sample.model.NoteDto

@Database(entities = [NoteDto::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        const val DATABASE_NAME: String = "note_db"
    }
}