package com.mastercoding.notetakingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mastercoding.notetakingapp.Repository.NoteRepo
import com.mastercoding.notetakingapp.database.Notedatabase
import com.mastercoding.notetakingapp.databinding.ActivityMainBinding
import com.mastercoding.notetakingapp.myviewmodel.NoteViewmodel
import com.mastercoding.notetakingapp.myviewmodel.noteViewmodelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var notesviewModel : NoteViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewmodel()
    }

    private fun setUpViewmodel() {
        //Start linking things in Main Activity
        val noteRepository = NoteRepo(Notedatabase(this))

        //Since we have viewmodel Factory no need to create instance of viewmodel, Directly communicate through viewModelFactory
        val viewModelProviderFactory = noteViewmodelFactory(application, noteRepository)

        notesviewModel = ViewModelProvider(
            this,
            viewModelProviderFactory).get(NoteViewmodel::class.java)
        //since you cant connect with viewmodel directly u use viewmodel provider here
    }

    //Here a viewmodel factory is created which is connected to Repository which is connected to database for accesing all the functions
}