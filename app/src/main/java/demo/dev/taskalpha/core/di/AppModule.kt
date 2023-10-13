package demo.dev.taskalpha.core.di

import android.content.Context
import demo.dev.taskalpha.core.utils.NetworkUtils
import demo.dev.taskalpha.data.local.TaskDB
import demo.dev.taskalpha.data.remote.api.TaskApi
import demo.dev.taskalpha.domain.repository.TaskRepository
import demo.dev.taskalpha.domain.usecases.GetTaskDetailUseCase
import demo.dev.taskalpha.domain.usecases.GetTaskListUseCase
import demo.dev.taskalpha.domain.usecases.TaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import demo.dev.taskalpha.data.repository.TaskRepositoryImpl
import demo.dev.taskalpha.domain.usecases.CreateNewTaskUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTaskRepository(taskApi: TaskApi, taskDB: TaskDB): TaskRepository =
        TaskRepositoryImpl(taskApi = taskApi, taskDB = taskDB)

    @Provides
    @Singleton
    fun provideTaskUseCases(taskRepository: TaskRepository) = TaskUseCase(
        getTaskListUseCase = GetTaskListUseCase(taskRepository = taskRepository),
        getTaskDetailUseCase = GetTaskDetailUseCase(taskRepository = taskRepository),
        createNewTaskUseCase = CreateNewTaskUseCase(taskRepository = taskRepository)
    )

    @Provides
    @Singleton
    fun provideNetworkUtils(@ApplicationContext context: Context): NetworkUtils {
        return NetworkUtils(context)
    }
}