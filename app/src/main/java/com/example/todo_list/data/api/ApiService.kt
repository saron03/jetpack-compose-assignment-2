package com.example.todo_list.data.api

import com.example.todo_list.data.model.ToDo
import retrofit2.http.GET

interface ApiService {
    @GET("todos")
    suspend fun getTodos(): List<ToDo>
}