package demo.dev.taskalpha.presentation.taskcreate


import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import demo.dev.taskalpha.presentation.theme.Purple40
import demo.dev.taskalpha.presentation.theme.Purple80
import demo.dev.taskalpha.presentation.theme.TaskAlphaTheme
import demo.dev.taskalpha.presentation.viewmodels.TaskEvents
import demo.dev.taskalpha.presentation.viewmodels.TaskViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TaskCreateScreen(
    viewModel: TaskViewModel = hiltViewModel(),
    backPress: () -> Unit,
    navController: NavHostController
) {

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is TaskViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is TaskViewModel.UiEvent.SaveTask -> {
                    navController.navigateUp()
                }

                TaskViewModel.UiEvent.UpdateTask -> {
                    navController.navigateUp()
                }
            }
        }
    }

    TaskAlphaTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Sharp.ArrowBack,
                        tint = Purple40,
                        contentDescription = "back-button",
                        modifier = Modifier
                            .padding(end = 6.dp, top = 8.dp)
                            .size(30.dp)
                            .clickable {
                                navController.navigateUp()
                            }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = "📝 Create New Task",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 24.sp,
                    )
                }
            }
        ) { paddingValues ->
            val contentModifier = Modifier
                .padding(paddingValues)

            AddTask(viewModel = viewModel)
        }
    }

}

@Composable
fun AddTask(viewModel: TaskViewModel) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .defaultMinSize(minHeight = 5000.dp)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = viewModel.taskTitle.value.text,
            onValueChange = {
                viewModel.onEvent(TaskEvents.EnteredTitle(it))
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = " Task Title ") },

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Purple40,
                unfocusedBorderColor = Purple80,
            ),
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 90.dp),
            value = viewModel.taskDescription.value.text,
            onValueChange = {
                viewModel.onEvent(TaskEvents.EnteredDescription(it))
            },
            label = { Text(text = "Task Description ") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Purple40,
                unfocusedBorderColor = Purple80,
            ),
        )
        Spacer(modifier = Modifier.height(50.dp))
        Row {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Purple80),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Purple40)
                    .width(100.dp),
                onClick = {
                    viewModel.onEvent(TaskEvents.SaveTask)
                }) {
                Text(
                    modifier = Modifier.padding(bottom = 4.dp),
                    text = "Create Task",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                )
            }
        }
    }
}

@Preview("Simple task create")
@Preview("Simple task create (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TaskCreatePreview() {
    AddTask(viewModel = hiltViewModel())
}

fun Context.makeShortToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.makeLongToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}