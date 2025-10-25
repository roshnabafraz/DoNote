package com.roshnab.donote.ViewModel

import com.roshnab.donote.Backend.Todo
import com.roshnab.donote.Backend.Todo_Dao
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: Todo_Dao) {

    fun getAllTodos(): Flow<List<Todo>> = todoDao.GetAllTodos()

    val undoneTasks: Flow<List<Todo>> = todoDao.getUndoneTasks()
    val doneTasks: Flow<List<Todo>> = todoDao.getDoneTasks()

    val AllTasksCount : Flow<Int> = todoDao.AllTasksCount()
    val DoneTasksCount : Flow<Int> = todoDao.getDoneTasksCount()

    suspend fun insertTodo(todo: Todo) {
        todoDao.InsertTodo(todo)
    }

    suspend fun deleteTodo(id: Int) {
        todoDao.DeleteTodo(id)
    }

    suspend fun updateisDone(id: Int, isDone: Boolean) {
        todoDao.UpdateisDone(isDone, id)
    }


}