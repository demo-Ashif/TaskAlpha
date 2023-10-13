package demo.dev.taskalpha.data.remote.api

import demo.dev.taskalpha.data.remote.dto.TaskListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TaskApi {
    @GET("api/all-task-list")
    suspend fun getAllTasks(
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): Response<TaskListDto>

    @POST("api/update-task")
    suspend fun updateTask(
        @Query("taskId") taskId: String,
    ): Response<TaskListDto>
}