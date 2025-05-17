package com.example.todo_list.ui.DetailScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    todoId: Int,
    viewModel: DetailScreenViewModel = hiltViewModel()
) {
    val todo by viewModel.todo.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTodoById(todoId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Todo Details",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
        }
    ) { innerPadding ->
        todo?.let {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "Title: ${it.title}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize * 1.1f
                    )
                )
                Text(
                    text = "User ID: ${it.userId}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize * 1.1f
                    )
                )
                Text(
                    text = "Completed: ${if (it.completed) "Yes" else "No"}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize * 1.1f
                    )
                )
            }
        } ?: Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.surfaceVariant)
        }
    }
}