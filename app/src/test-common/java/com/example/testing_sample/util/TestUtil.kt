package com.example.testing_sample.util

import com.example.testing_sample.model.NoteDto
import java.util.*

class TestUtil {
    companion object {

        const val TIMESTAMP_1 = "05-2019"
        val TEST_NOTE_1: NoteDto =
            NoteDto(
                title = "Take out the trash",
                content = "It's garbage day tomorrow.",
                timestamp = TIMESTAMP_1
            )

        const val TIMESTAMP_2 = "06-2019"
        val TEST_NOTE_2: NoteDto =
            NoteDto(
                title = "Anniversary gift",
                content = "Buy an anniversary gift.",
                timestamp = TIMESTAMP_2
            )

        val TEST_NOTES_LIST: List<NoteDto> = Collections.unmodifiableList(
            object : ArrayList<NoteDto>() {
                init {
                    add(
                        NoteDto(
                            id = 1,
                            title = "Take out the trash",
                            content = "It's garbage day tomorrow.",
                            timestamp = TIMESTAMP_1
                        )
                    )
                    add(
                        NoteDto(
                            id = 2,
                            title = "Anniversary gift",
                            content = "Buy an anniversary gift.",
                            timestamp = TIMESTAMP_2
                        )
                    )
                }
            }
        )
    }
}