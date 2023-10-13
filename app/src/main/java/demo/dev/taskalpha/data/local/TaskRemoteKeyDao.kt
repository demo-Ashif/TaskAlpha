package demo.dev.taskalpha.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import demo.dev.taskalpha.data.local.entities.TaskRemoteKeysEntity

@Dao
interface TaskRemoteKeyDao {
    @Query("SELECT * FROM tasks_remote_keys WHERE id =:id")
    suspend fun getRemoteKeys(id: String): TaskRemoteKeysEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<TaskRemoteKeysEntity>)

    @Query("DELETE FROM tasks_remote_keys")
    suspend fun deleteAllRemoteKeys()
}