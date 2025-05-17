package com.example.todo_list.ui.ToDoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_list.data.model.ToDo
import com.example.todo_list.data.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    private val _todos = MutableLiveData<List<ToDo>>()
    val todos: LiveData<List<ToDo>> get() = _todos

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    init {
        loadTodos()
    }

    fun loadTodos() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val todoList = repository.getTodos()
                _todos.value = todoList
            } catch (e: IOException) {
                _errorMessage.value = "Network error. Displaying cached data."
            } finally {
                _isLoading.value = false
            }
        }
    }
}