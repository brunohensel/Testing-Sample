package com.example.testing_sample.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

const val TIMESTAMP1 = "05-2019"
const val TIMESTAMP2 = "04-2019"

class NoteDtoTest {
    /**Compare two equal Notes*/
    @Test
    fun isNotesEqual_identicalProperties_return_true() {
        //Given
        val note1 = NoteDto(title = "Note #1", content = "This is Note #1", timestamp = TIMESTAMP1)
        val note2 = NoteDto(title = "Note #1", content = "This is Note #1", timestamp = TIMESTAMP1)
        //Then
        assertEquals(note1, note2)
    }

    /**Compare notes with 2 different ids*/
    @Test
    fun isNoteEqual_differentIds_returnFalse() {
        //Given
        val note1 = NoteDto(title = "Note #1", content = "This is Note #1", timestamp = TIMESTAMP1)
        note1.id = 1
        val note2 = NoteDto(title = "Note #1", content = "This is Note #1", timestamp = TIMESTAMP1)
        note2.id = 2

        //Then
        assertNotEquals(note1, note2)
    }

    /**Compare two notes with different timestamps*/
    @Test
    fun isNoteEqual_differentTimestamps_returnTrue() {
        //Given
        val note1 = NoteDto(title = "Note #1", content = "This is Note #1", timestamp = TIMESTAMP1)
        val note2 = NoteDto(title = "Note #1", content = "This is Note #1", timestamp = TIMESTAMP2)

        //Then
        assertEquals(note1, note2)
    }

    /**Compare two notes with different titles*/
    @Test
    internal fun isNoteEqual_differentTitle() {
        //Given
        val note1 = NoteDto(title = "Note #1", content = "This is Note #1", timestamp = TIMESTAMP1)
        val note2 = NoteDto(title = "Note #2", content = "This is Note #1", timestamp = TIMESTAMP2)

        //Then
        assertNotEquals(note1, note2)
    }
    /**Compare two notes with different content*/
    @Test
    internal fun isNoteEqual_differentContent() {
        //Given
        val note1 = NoteDto(title = "Note #1", content = "This is Note #1", timestamp = TIMESTAMP1)
        val note2 = NoteDto(title = "Note #1", content = "This is Note #2", timestamp = TIMESTAMP2)

        //Then
        assertNotEquals(note1, note2)
    }
}