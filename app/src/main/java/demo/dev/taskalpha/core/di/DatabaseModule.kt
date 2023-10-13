package demo.dev.taskalpha.core.di

import android.content.Context
import androidx.room.Room
import demo.dev.taskalpha.core.app.Constants
import demo.dev.taskalpha.data.local.TaskDB
import demo.dev.taskalpha.data.local.TaskDao
import demo.dev.taskalpha.data.local.TaskRemoteKeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TaskDB =
        Room.databaseBuilder(context, TaskDB::class.java, Constants.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideTaskDao(taskDB: TaskDB): TaskDao = taskDB.taskDao()

    @Provides
    fun provideRemoteKeyDao(taskDB: TaskDB): TaskRemoteKeyDao = taskDB.remoteKeyDao()
}