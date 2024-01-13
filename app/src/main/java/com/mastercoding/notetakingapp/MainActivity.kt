package com.mastercoding.notetakingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mastercoding.notetakingapp.myviewmodel.NoteViewmodel

class MainActivity : AppCompatActivity() {

    lateinit var notesviewModel : NoteViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}