package demo.dev.taskalpha.presentation.viewmodels

sealed class TaskEvents {
    data class EnteredTitle(val value: String) : TaskEvents()
    data class EnteredDescription(val value: String) : TaskEvents()
    object SaveTask : TaskEvents()
}
