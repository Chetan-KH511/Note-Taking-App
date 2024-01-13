package com.mastercoding.notetakingapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mastercoding.notetakingapp.databinding.NoteLayoutBinding
import com.mastercoding.notetakingapp.fragments.HomeFragment
import com.mastercoding.notetakingapp.fragments.HomeFragmentDirections
import com.mastercoding.notetakingapp.model.Note
import java.util.*

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteviewHolder>() {


    class NoteviewHolder(val itemBindig : NoteLayoutBinding): RecyclerView.ViewHolder(itemBindig.root)

        private val differCallback = object : DiffUtil.ItemCallback<Note>(){
            //Helps to update/dlt/ recycler view items more and more fast
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return ( (oldItem.id == newItem.id) &&
                        (oldItem.noteBody == newItem.noteBody) &&
                        (oldItem.noteTitle == newItem.noteTitle))
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return (oldItem == newItem)
            }
        }

        val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteviewHolder {
        return NoteviewHolder(NoteLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NoteviewHolder, position: Int) {
        val currentNote =  differ.currentList[position]

        holder.itemBindig.tvNoteTitle.text = currentNote.noteTitle
        holder.itemBindig.tvNoteBody.text = currentNote.noteBody

        val random = Random()
        val color = Color.argb(
            255,
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
        )

        holder.itemBindig.ibColor.setBackgroundColor(color)

        holder.itemView.setOnClickListener{
            val direction = HomeFragmentDirections.actionHomeFragmentToUpdateNoteFragment(currentNote)
            it.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}