package com.mastercoding.notetakingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mastercoding.notetakingapp.model.Note
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Note::class], version = 1)
abstract class Notedatabase :RoomDatabase(){
    abstract fun getNoteDao() : NoteDAO

    companion object{
        @Volatile
        private var instance : Notedatabase? = null
        private val LOCK = Any()

        @OptIn(InternalCoroutinesApi::class)
        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance ?: createDatabase(context).also{
                instance = it
            }
        }

        private fun createDatabase(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            Notedatabase::class.java,
            "note_db"
        ).build()
    }
}