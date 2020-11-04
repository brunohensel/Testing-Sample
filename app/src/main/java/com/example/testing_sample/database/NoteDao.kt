package com.example.testing_sample.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.testing_sample.model.NoteDto
import io.reactivex.Single


@Dao
interface NoteDao {
    @Insert
    @Throws(Exception::class)
    fun insertNote(note: NoteDto): Single<Long>

    @Query("SELECT * FROM notes")
    fun getNotes(): LiveData<List<NoteDto>>

    @Delete
    @Throws(Exception::class)
    fun deleteNote(note: NoteDto): Single<Int>

    @Update
    @Throws(Exception::class)
    fun updateNote(note: NoteDto): Single<Int>
}