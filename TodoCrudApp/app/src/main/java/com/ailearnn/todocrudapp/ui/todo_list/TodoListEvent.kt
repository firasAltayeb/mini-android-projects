package com.ailearnn.todocrudapp.ui.todo_list

import com.ailearnn.todocrudapp.data.Todo

sealed class TodoListEvent {
    data class OnDeleteTodoClick(val todo: Todo) : TodoListEvent()
    data class OnDoneChange(val todo: Todo, val isDone: Boolean) : TodoListEvent()
    data object OnUndoDeleteClick : TodoListEvent()
    data class OnTodoClick(val todo: Todo) : TodoListEvent()
    data object OnAddTodoClick : TodoListEvent()
}