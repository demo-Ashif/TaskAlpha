package demo.dev.taskalpha.data.remote.api

import demo.dev.taskalpha.data.remote.dto.TaskDto
import demo.dev.taskalpha.data.remote.dto.TaskListDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TaskApi {
    @GET("api/all-task-list")
    suspend fun getAllTasks(
        @Query("apiKey") apiKey: String="dummy key",
        @Query("page") page: Int=1,
        @Query("pageSize") pageSize: Int=100,
    ): Response<TaskListDto>

    @POST("api/update-task")
    suspend fun updateTask(
        @Query("task_id") taskId: String,
    ): Response<TaskListDto>

    @POST("api/create-task")
    suspend fun createTask(
        @Body task: TaskDto,
    ): Response<TaskListDto>
}