package demo.dev.taskalpha.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import demo.dev.taskalpha.data.local.entities.TasksEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(taskEntityList: List<TasksEntity>)

    @Query("SELECT * FROM task_table")
//    fun getAllTasks(): PagingSource<Int, TasksEntity>
    fun getAllTasks(): List<TasksEntity>

    @Query("SELECT * FROM task_table WHERE id =:id")
    fun getTaskDetail(id: String): Flow<TasksEntity>

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTasks()
}