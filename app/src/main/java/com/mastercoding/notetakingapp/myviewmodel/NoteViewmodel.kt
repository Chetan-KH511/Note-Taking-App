package com.mastercoding.notetakingapp.myviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mastercoding.notetakingapp.Repository.NoteRepo
import com.mastercoding.notetakingapp.model.Note
import kotlinx.coroutines.launch

class NoteViewmodel(app: Application, private val noteRepository :NoteRepo) : AndroidViewModel(app) {


    //functionns with suspend fun i.e coroutines launched in background thread
    fun addNote(note: Note) =
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }

    fun deleteNote(note: Note) =
        viewModelScope.launch {
        noteRepository.deleteNote(note)
    }

    fun updateNote(note: Note) =
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }

    //Normal funcs

    fun getAllNotes() =
        noteRepository.getAllNotes()

    fun searchNotes(query:String?) =
        noteRepository.searchNote(query)




}