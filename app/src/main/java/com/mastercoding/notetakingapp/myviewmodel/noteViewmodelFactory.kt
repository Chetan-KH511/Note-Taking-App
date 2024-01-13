package com.mastercoding.notetakingapp.myviewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mastercoding.notetakingapp.Repository.NoteRepo

class noteViewmodelFactory(val app : Application, private val noteRepo: NoteRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewmodel(app, noteRepo) as T
    }




}