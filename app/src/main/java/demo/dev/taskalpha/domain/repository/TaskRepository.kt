package demo.dev.taskalpha.domain.repository

import androidx.paging.PagingData
import demo.dev.taskalpha.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTask(): Flow<List<Task>>
    fun updateTaskDetail(id: String): Flow<Task>
}