package demo.dev.taskalpha.presentation.tasklist


import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import demo.dev.taskalpha.presentation.navigation.Screen
import demo.dev.taskalpha.presentation.theme.ButtonBlue
import demo.dev.taskalpha.presentation.theme.LightRed
import demo.dev.taskalpha.presentation.theme.TextWhite

@Composable
fun TaskListScreen(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column {
            Text(text = "There will be Row")
            TaskList()
        }
        FabCreateTask(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {
                navController.navigate(Screen.TaskCreate.route)
            })
    }
}

@Composable
fun FabCreateTask(onClick: () -> Unit, modifier: Modifier) {
    LargeFloatingActionButton(
        modifier = modifier,
        onClick = { onClick() },
        shape = CircleShape,
    ) {
        Icon(Icons.Filled.Add,
            modifier = Modifier.size(50.dp), contentDescription = "Large floating action button")
    }
}


@Composable
fun TaskList() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        val tasks = listOf(
            "Create new project", "Working call", "Meet with doctor",
            "Go to the shop", "Take the kids to school", "Finish dribble marathon",
            "Walk with dog", "Meet with mother","Create new project", "Working call", "Meet with doctor",
            "Go to the shop", "Take the kids to school", "Finish dribble marathon",
            "Walk with dog", "Meet with mother"
        )

        items(tasks) { task ->
            TaskItem()
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun TaskItem(
    color: Color = Color.White
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
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Daily Thought",
                style = MaterialTheme.typography.bodyLarge
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(ButtonBlue)
                    .padding(10.dp)
            ) {

            }
        }
    }
}

@Preview("Simple task card")
@Preview("Simple task card (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TaskListPreview() {
    TaskItem()
}
