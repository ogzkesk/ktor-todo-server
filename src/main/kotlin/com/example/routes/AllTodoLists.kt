package com.example.routes

import com.example.models.ToDo
import com.example.repository.TodoRepositoryImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllTodoList(toDoRepository: TodoRepositoryImpl){
    get("/todos"){
        call.respond(status = HttpStatusCode.OK,message = toDoRepository.getAllTodos())
    }
}