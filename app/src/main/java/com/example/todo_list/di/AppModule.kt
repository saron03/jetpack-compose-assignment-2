package com.example.todo_list.di

import android.content.Context
import androidx.room.Room
import com.example.todo_list.data.database.AppDatabase
import com.example.todo_list.data.database.ToDoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "todo_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideToDoDao(appDatabase: AppDatabase): ToDoDao {
        return appDatabase.todoDao()
    }
}