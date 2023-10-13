package demo.dev.taskalpha.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import demo.dev.taskalpha.data.local.entities.TasksEntity
import demo.dev.taskalpha.data.local.entities.TaskRemoteKeysEntity

@Database(
    entities = [TasksEntity::class, TaskRemoteKeysEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TaskDB : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun remoteKeyDao(): TaskRemoteKeyDao
}