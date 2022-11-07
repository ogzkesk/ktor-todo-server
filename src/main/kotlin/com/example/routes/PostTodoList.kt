package com.example.routes

import com.example.models.SetTodo
import com.example.models.ToDo
import com.example.repository.TodoRepositoryImpl
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.postTodoList(toDoRepository: TodoRepositoryImpl){
    post("/todos"){
        val requestTodo = call.receive<SetTodo>()

        val response = toDoRepository.addTodo(requestTodo)

        call.respond(response)
    }
}