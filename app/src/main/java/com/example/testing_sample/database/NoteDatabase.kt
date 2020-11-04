package com.example.testing_sample.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteDao::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        const val DATABASE_NAME: String = "note_db"
    }
}