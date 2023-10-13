package demo.dev.taskalpha.presentation.tasklist


import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import demo.dev.taskalpha.domain.model.Task
import demo.dev.taskalpha.presentation.navigation.Screen
import demo.dev.taskalpha.presentation.theme.TaskAlphaTheme

@Composable
fun TaskListScreen(
    navController: NavHostController,
    viewModel: TaskListViewModel = hiltViewModel()
) {

    TaskAlphaTheme {
        Scaffold {
            paddingValues ->
            val contentModifier = Modifier
                .padding(paddingValues)
            Box(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                Column {
                    Row (
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 24.dp)
                    ){
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = "Task Icon",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(30.dp)
                        )

                        Text(
                            text = "All Tasks",
                            style = TextStyle(
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier.wrapContentSize(Alignment.Center)
                        )
                        Icon(
                            imageVector = Icons.Outlined.CheckCircle,
                            contentDescription = "Task Icon",
                            tint = Color.Transparent,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    TaskList(viewModel)
                }
                FabCreateTask(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    onClick = {
                        navController.navigate(Screen.TaskCreate.route)
                    })
            }
        }
    }
}

@Composable
fun FabCreateTask(onClick: () -> Unit, modifier: Modifier) {
    LargeFloatingActionButton(

        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary,
        onClick = { onClick() },
        shape = CircleShape,
    ) {
        Icon(
            Icons.Outlined.Add,
            modifier = Modifier.size(60.dp),
            contentDescription = "Large floating action button"
        )
    }
}


@Composable
fun TaskList(viewModel: TaskListViewModel = hiltViewModel()) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        val tasks = viewModel.state.value.tasks

        items(tasks) { task ->
            Spacer(modifier = Modifier.height(8.dp))
            TaskItem(task = task, onTaskUpdate = {
                viewModel.onEvent(TaskListEvent.ToggleTaskStatus(task.id))
            })
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun TaskItem(
    color: Color = Color.White,
    task: Task,
    onTaskUpdate: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            //.background(Color.White)
            .padding(0.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(50.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .background(color)
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .fillMaxWidth()
                .clickable {
                    onTaskUpdate()
                }
        ) {
            Text(
                text = task.taskTitle,
                color = if (task.taskStatus) Color.Gray.copy(alpha = 0.5f) else Color.DarkGray,
                textDecoration = if (task.taskStatus) TextDecoration.LineThrough else TextDecoration.None,
                style = MaterialTheme.typography.bodyLarge
            )
            CircleCheckbox(selected = task.taskStatus, onChecked = {
                onTaskUpdate()
            })
        }
    }
}

@Composable
fun CircleCheckbox(selected: Boolean, enabled: Boolean = true, onChecked: () -> Unit) {

    val color = MaterialTheme.colorScheme
    val imageVector = if (selected) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle
    val tint = if (selected) color.secondary.copy(alpha = 0.9f) else color.primary.copy(alpha = 0.2f)
    val background = if (selected) Color.White else Color.Transparent

    IconButton(
        onClick = { onChecked() },
        modifier = Modifier.offset(x = 4.dp, y = 4.dp),
        enabled = enabled
    ) {

        Icon(
            imageVector = imageVector, tint = tint,
            modifier = Modifier
                .background(background, shape = CircleShape)
                //.shadow(8.dp, shape = CircleShape)
                .size(40.dp),
            contentDescription = "checkbox"
        )
    }
}

@Preview("Simple task card")
@Preview("Simple task card (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TaskListPreview() {
    TaskItem(
        Color.White,
        Task(
            id = "id",
            taskStatus = true,
            taskTitle = "Title",
            taskDescription = "Content",
            taskUpdatedAt = 12334
        ),
        onTaskUpdate = {}
    )
}
