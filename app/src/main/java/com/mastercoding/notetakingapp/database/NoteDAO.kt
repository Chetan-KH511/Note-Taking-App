package com.mastercoding.notetakingapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mastercoding.notetakingapp.model.Note

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note : Note)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes() : LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE noteTitle LIKE :query OR noteBody LIKE :query")
    fun seachtNote(query : String?) : LiveData<List<Note>>

}