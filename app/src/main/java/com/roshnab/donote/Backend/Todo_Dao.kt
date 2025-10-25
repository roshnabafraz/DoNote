package com.roshnab.donote.Backend

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract interface Todo_Dao {

    @Query("SELECT * FROM Todo")
    fun GetAllTodos(): Flow<List<Todo>>

    @Insert
    suspend fun InsertTodo(todo: Todo)

    @Query("DELETE FROM Todo WHERE id = :id")
    suspend fun DeleteTodo(id: Int)
    @Query("UPDATE Todo SET isDone = :isDone WHERE id = :id")
    suspend fun UpdateisDone(isDone: Boolean, id: Int)

    @Query("SELECT * FROM todo WHERE isDone = 0")
    fun getUndoneTasks(): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE isDone = 1")
    fun getDoneTasks(): Flow<List<Todo>>

    @Query("SELECT COUNT(*) FROM todo WHERE isDone = 1")
    fun getDoneTasksCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM todo")
    fun AllTasksCount(): Flow<Int>

}