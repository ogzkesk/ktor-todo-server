package com.example.repository

import com.example.database.DatabaseConnection
import com.example.models.SetTodo
import com.example.models.ToDo

class TodoRepositoryImpl : TodoRepository {

    private val database = DatabaseConnection

    override suspend fun getAllTodos(): List<ToDo> {
        return database.getAllTodos()
            .map { ToDo(it.id,it.title,it.done) }
    }

    override suspend fun getTodoById(id: Int): ToDo? {
        return database.getTodoById(id)?.let {
            ToDo(it.id,it.title,it.done)
        }
    }

    override suspend fun addTodo(setTodo: SetTodo): ToDo {

        return database.addTodo(setTodo)
    }

    override suspend fun updateTodo(id: Int, setTodo: SetTodo): Boolean {
        return database.updateTodoById(id,setTodo)
    }

    override suspend fun deleteTodoById(id: Int): Boolean {
        return database.deleteTodoById(id)
    }
}