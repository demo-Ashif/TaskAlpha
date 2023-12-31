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
    suspend fun insertTasks(tasksEntities: List<TasksEntity?>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTask(tasksEntity: TasksEntity)

    @Query("SELECT * FROM task_table")
//    fun getAllTasks(): PagingSource<Int, TasksEntity>
    fun getAllTasks(): Flow<List<TasksEntity>>

    @Query("SELECT * FROM task_table WHERE id =:id")
    fun getTaskDetail(id: String): TasksEntity

    @Query("UPDATE task_table SET taskStatus = NOT taskStatus WHERE id = :taskId")
    suspend fun updateTaskById(taskId: String)

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTasks()
}