package demo.dev.taskalpha.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import demo.dev.taskalpha.core.app.Constants
import demo.dev.taskalpha.data.local.TaskDB
import demo.dev.taskalpha.data.local.entities.TasksEntity
import demo.dev.taskalpha.data.local.entities.TaskRemoteKeysEntity
import demo.dev.taskalpha.data.remote.api.TaskApi
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class TaskRemoteMediator(
    private val taskApi: TaskApi,
    private val taskDB: TaskDB
) :
    RemoteMediator<Int, TasksEntity>() {
    private val taskDao = taskDB.taskDao()
    private val remoteKeysDao = taskDB.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TasksEntity>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }


            val response = taskApi.getAllTasks(
                page = currentPage,
                pageSize = state.config.pageSize,
                apiKey = ""
            )
            val endOfPaginationReached = Constants.MAX_PAGE_COUNT == currentPage

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            taskDB.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    taskDao.deleteAllTasks()
                    remoteKeysDao.deleteAllRemoteKeys()
                }

                response.body()?.taskListDto?.map { it.toTaskEntity() }
                    ?.let { taskDao.insertTasks(it) }

                val keys = response.body()?.taskListDto?.map { task ->
                    TaskRemoteKeysEntity(
                        id = task.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                keys?.let { remoteKeysDao.addAllRemoteKeys(it) }
            }
            MediatorResult.Success(endOfPaginationReached)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, TasksEntity>
    ): TaskRemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, TasksEntity>
    ): TaskRemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { task ->
                remoteKeysDao.getRemoteKeys(id = task.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, TasksEntity>
    ): TaskRemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { task ->
                remoteKeysDao.getRemoteKeys(id = task.id)
            }
    }


}