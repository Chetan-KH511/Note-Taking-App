package com.mastercoding.notetakingapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.mastercoding.notetakingapp.MainActivity
import com.mastercoding.notetakingapp.R
import com.mastercoding.notetakingapp.adapter.NoteAdapter
import com.mastercoding.notetakingapp.databinding.FragmentHomeBinding
import com.mastercoding.notetakingapp.databinding.FragmentUpdateNoteBinding
import com.mastercoding.notetakingapp.model.Note
import com.mastercoding.notetakingapp.myviewmodel.NoteViewmodel

class UpdateNoteFragment : Fragment(R.layout.fragment_update_note) {
    private var _binding : FragmentUpdateNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var notesviewModel : NoteViewmodel
    private lateinit var currenNote : Note

    //Since the update note Fragment contains arguments in nav_graph
    private val args: UpdateNoteFragmentArgs by navArgs()
    //this is how we pass data between fragment and arg

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesviewModel = (activity as MainActivity).notesviewModel
        currenNote = args.note!!

        binding.etNoteTitleupdate.setText(currenNote.noteTitle)
        binding.etNoteBodyUpdate.setText(currenNote.noteBody)

        //If user update note

        binding.fabDone.setOnClickListener{
            val title = binding.etNoteTitleupdate.text.toString().trim()
            val body = binding.etNoteBodyUpdate.text.toString().trim()

            if(title.isNotEmpty()){
                val note = Note(currenNote.id, title, body)
                view.findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }else{
                Toast.makeText(context, "Please enter the note title", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun deleteNote(){

        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("Are you sure?")
            setPositiveButton("Delete"){_,_,->
                notesviewModel.deleteNote(currenNote)
                view?.findNavController()?.navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }
            setNegativeButton("Cancel", null)
        }.create().show()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_update_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_delete -> {
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }




}