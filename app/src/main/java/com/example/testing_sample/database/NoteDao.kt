package com.example.testing_sample.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.testing_sample.model.NoteDto
import io.reactivex.Single

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteDto): Single<Long>

    @Query("SELECT * FROM notes_table")
    fun getNotes(): LiveData<List<NoteDto>>

    @Query("DELETE FROM notes_table WHERE id = :id")
    fun deleteNote(id: Int): Single<Int>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNote(note: NoteDto): Single<Int>
}