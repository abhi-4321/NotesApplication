package com.abhinav.notesapplication.di


import android.content.Context
import android.database.DatabaseUtils
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abhinav.notesapplication.database.Dao
import com.abhinav.notesapplication.database.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesNotesDatabase(@ApplicationContext context: Context): NotesDatabase {
        return Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            "NotesDb"
        ).build()
    }
}