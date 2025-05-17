package com.example.todo_list.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todo_list.ui.DetailScreen.DetailScreen
import com.example.todo_list.ui.ToDoList.ToDoListScreen

@Composable
fun TodoNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "todo_list"
    ) {
        composable("todo_list") {
            ToDoListScreen(navController)
        }

        composable(
            route = "todo_detail/{todoId}",
            arguments = listOf(navArgument("todoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val todoId = backStackEntry.arguments?.getInt("todoId") ?: -1
            DetailScreen(navController = navController, todoId = todoId)
        }
    }
}
