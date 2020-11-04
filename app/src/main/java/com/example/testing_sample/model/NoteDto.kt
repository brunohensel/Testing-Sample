package com.example.testing_sample.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "notes_table")
data class NoteDto(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var content: String,
    var timestamp: String
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as NoteDto
        return id == other.id && content == other.content && title == other.title
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + timestamp.hashCode()
        return result
    }
}