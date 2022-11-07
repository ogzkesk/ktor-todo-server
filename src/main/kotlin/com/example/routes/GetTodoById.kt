package com.example.routes

import com.example.models.ToDo
import com.example.repository.TodoRepositoryImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getTodoById(toDoRepository: TodoRepositoryImpl){
    get("/todos/{id}"){
        val requestId = call.parameters["id"]?.toInt() ?: -1

        val response = toDoRepository.getTodoById(requestId)

        response?.let {
            call.respond(HttpStatusCode.OK,response)
            return@get
        }

        call.respond(HttpStatusCode.BadRequest,"Wrong id passed")
    }
}