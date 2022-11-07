package com.example.repository

import com.example.models.SetTodo
import com.example.models.ToDo

interface TodoRepository {

    suspend fun getAllTodos() : List<ToDo>

    suspend fun getTodoById(id: Int) : ToDo?

    suspend fun addTodo(setTodo: SetTodo) : ToDo

    suspend fun updateTodo(id: Int, setTodo: SetTodo) : Boolean

    suspend fun deleteTodoById(id: Int) : Boolean
}