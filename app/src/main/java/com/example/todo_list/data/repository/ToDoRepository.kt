package com.example.todo_list.data.repository

import com.example.todo_list.data.api.ApiService
import com.example.todo_list.data.database.ToDoDao
import com.example.todo_list.data.model.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class ToDoRepository @Inject constructor(
    private val apiService: ApiService,
    private val toDoDao: ToDoDao
) {
    suspend fun getTodos(): List<ToDo> = withContext(Dispatchers.IO) {
        val cachedTodos = toDoDao.getAllTodos()

        try {
            val networkTodos = apiService.getTodos()
            toDoDao.clearTodos()
            toDoDao.insertTodos(networkTodos)
            networkTodos
        } catch (e: IOException) {
            if (cachedTodos.isNotEmpty()) {
                cachedTodos
            } else {
                throw e
            }
        }
    }

    suspend fun getTodoById(id: Int): ToDo? = withContext(Dispatchers.IO) {
        toDoDao.getTodoById(id)
    }
}