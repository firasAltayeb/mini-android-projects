package com.ailearnn.todocrudapp.di

import android.app.Application
import androidx.room.Room
import com.ailearnn.todocrudapp.data.TodoDatabase
import com.ailearnn.todocrudapp.data.TodoRepository
import com.ailearnn.todocrudapp.data.TodoRepositoryImpl
import com.ailearnn.todocrudapp.util.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): TodoRepository {
        return TodoRepositoryImpl(db.getTodoDao)
    }
}