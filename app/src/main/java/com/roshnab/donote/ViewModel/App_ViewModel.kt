package com.roshnab.donote.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roshnab.donote.Backend.Notes
import com.roshnab.donote.Backend.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class App_ViewModel(private val todoRepository: TodoRepository,
                    private val notesRepository: NotesRepository) : ViewModel() {

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos.asStateFlow()

    private val _notes = MutableStateFlow<List<Notes>>(emptyList())
    val notes: StateFlow<List<Notes>> = _notes.asStateFlow()

    val AllTasksCount = todoRepository.AllTasksCount
    val DoneTasksCount = todoRepository.DoneTasksCount

    init {
        viewModelScope.launch {
            todoRepository.getAllTodos().collect { _todos.value = it }
        }
        viewModelScope.launch {
            notesRepository.getAllNotes().collect { _notes.value = it }
        }
    }


    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.insertTodo(todo)
        }
    }

    fun addNote(title: String, description: String) {
        viewModelScope.launch {
            notesRepository.addNote(Notes(title, description))
        }
    }

    fun deleteTodo(id: Int) {
        viewModelScope.launch {
            todoRepository.deleteTodo(id)
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            notesRepository.deleteNote(id)
        }
    }

    fun updateisDone(id: Int, isDone: Boolean) {
        viewModelScope.launch {
            todoRepository.updateisDone(id, isDone)
        }
    }
    val undoneTasks = todoRepository.undoneTasks.stateIn(
        viewModelScope, SharingStarted.Lazily, emptyList()
    )
    val doneTasks = todoRepository.doneTasks.stateIn(
        viewModelScope, SharingStarted.Lazily, emptyList()
    )

    fun updateNote(title: String, description: String) {
        viewModelScope.launch {
            notesRepository.updateNote(title, description)
        }
    }
}