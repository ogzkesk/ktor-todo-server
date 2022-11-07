package com.example.routes

import com.example.models.SetTodo
import com.example.models.ToDo
import com.example.repository.TodoRepositoryImpl
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.updateTodoById(toDoRepository: TodoRepositoryImpl){
    put("/todos/{id}"){
        val requestId = call.parameters["id"]?.toInt() ?: -1
        val requestTodo = call.receive<SetTodo>()

        val response = toDoRepository.updateTodo(requestId,requestTodo)

        if(response){
            call.respond("Successfully updated\n$requestTodo")
        } else {
            call.respondText("Error")
        }
    }
}