package com.example.todo_list.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todo_list.data.model.ToDo

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todos")
    suspend fun getAllTodos(): List<ToDo>

    @Query("SELECT * FROM todos WHERE id = :id")
    suspend fun getTodoById(id: Int): ToDo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodos(todos: List<ToDo>)

    @Query("DELETE FROM todos")
    suspend fun clearTodos()
}