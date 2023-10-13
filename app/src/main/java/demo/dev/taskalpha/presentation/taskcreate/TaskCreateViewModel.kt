package demo.dev.taskalpha.presentation.taskcreate

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.dev.taskalpha.domain.model.InvalidTaskException
import demo.dev.taskalpha.domain.model.Task
import demo.dev.taskalpha.domain.usecases.TaskUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskCreateViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase
) : ViewModel() {


    private val _taskTitle = mutableStateOf(
        TaskTextFieldState(
            hint = "Enter Task Title..."
        )
    )
    val taskTitle: State<TaskTextFieldState> = _taskTitle

    private val _taskDescription = mutableStateOf(
        TaskTextFieldState(
            hint = "Enter Title Description"
        )
    )
    val taskDescription: State<TaskTextFieldState> = _taskDescription


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun onEvent(event: TaskEvents) {
        when (event) {
            is TaskEvents.EnteredTitle -> {
                _taskTitle.value = taskTitle.value.copy(
                    text = event.value
                )
            }

            is TaskEvents.EnteredDescription -> {
                _taskDescription.value = _taskDescription.value.copy(
                    text = event.value
                )
            }

            TaskEvents.SaveTask -> {
                viewModelScope.launch {
                    try {
                        val timeStamp = System.currentTimeMillis()

                        taskUseCase.createNewTaskUseCase(
                            Task(
                                id = "${taskTitle.value.text}-$timeStamp",
                                taskTitle = taskTitle.value.text,
                                taskDescription = taskDescription.value.text,
                                taskUpdatedAt = timeStamp,
                                taskStatus = false,
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTask)
                    } catch (e: InvalidTaskException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveTask : UiEvent()
        object UpdateTask : UiEvent()
    }
}