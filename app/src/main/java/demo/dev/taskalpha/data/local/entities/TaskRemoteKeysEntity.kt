package demo.dev.taskalpha.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import demo.dev.taskalpha.core.app.Constants

@Entity(tableName = Constants.DB_TABLE_TASKS_REMOTE_KEY)
data class TaskRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)