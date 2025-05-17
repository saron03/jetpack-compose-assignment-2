package com.example.todo_list.ui.ToDoList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todo_list.data.model.ToDo

private val PageBackground = Color.White
private val CardBackground = Color(0xFFB0B0B0)
private val TitleBackground = Color(0xFF4A4A4A)
private val TitleMargin = 12.dp

@Composable
fun ToDoListScreen(
    navController: NavController,
    viewModel: ToDoListViewModel = hiltViewModel()
) {
    val todos by viewModel.todos.observeAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.observeAsState(initial = true)
    val errorMessage by viewModel.errorMessage.observeAsState(initial = null)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = PageBackground
    ) {
        Column {
            // Title with margin below
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TitleBackground)
                    .padding(vertical = 20.dp)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "All Tasks",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.height(TitleMargin))

            Box(modifier = Modifier.fillMaxSize()) {
                when {
                    isLoading && errorMessage == null -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = TitleBackground
                        )
                    }
                    errorMessage != null -> {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = errorMessage ?: "Unknown error",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = { viewModel.loadTodos() }) {
                                Text("Retry")
                            }
                        }
                    }
                    else -> {
                        if (todos.isEmpty() && !isLoading) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "No todos found.",
                                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(onClick = { viewModel.loadTodos() }) {
                                    Text("Refresh")
                                }
                            }
                        } else {
                            ToDoList(
                                todos = todos,
                                onItemClick = { todo ->
                                    navController.navigate("todo_detail/${todo.id}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ToDoList(
    todos: List<ToDo>,
    onItemClick: (ToDo) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(todos, key = { it.id }) { todo ->
            ToDoItem(todo = todo, onClick = { onItemClick(todo) })
        }
    }
}

@Composable
fun ToDoItem(
    todo: ToDo,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = todo.title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
