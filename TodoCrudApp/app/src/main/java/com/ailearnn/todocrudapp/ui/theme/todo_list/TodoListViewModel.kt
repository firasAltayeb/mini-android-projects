package com.ailearnn.todocrudapp.ui.theme.todo_list

import androidx.lifecycle.ViewModel
import com.ailearnn.todocrudapp.data.TodoRepository
import javax.inject.Inject

class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

}