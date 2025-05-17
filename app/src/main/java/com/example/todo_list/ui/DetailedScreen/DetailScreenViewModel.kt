package com.example.todo_list.ui.DetailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_list.data.model.ToDo
import com.example.todo_list.data.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    private val _todo = MutableStateFlow<ToDo?>(null)
    val todo: StateFlow<ToDo?> = _todo

    fun loadTodoById(id: Int) {
        viewModelScope.launch {
            val item = repository.getTodoById(id)
            _todo.value = item
        }
    }
}
