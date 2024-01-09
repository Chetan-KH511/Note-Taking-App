package com.mastercoding.notetakingapp.Repository

import androidx.room.Query
import com.mastercoding.notetakingapp.database.Notedatabase
import com.mastercoding.notetakingapp.model.Note

class NoteRepo(private val db: Notedatabase) {

    suspend fun insertNote(note: Note) = db.getNoteDao().insertNote(note)
    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)
    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)

    fun getAllNotes() = db.getNoteDao().getAllNotes()
    fun searchNote(query: String?) = db.getNoteDao().seachtNote(query)

}