package com.example.routes

import com.example.models.ToDo
import com.example.repository.TodoRepositoryImpl
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteTodoById(toDoRepository: TodoRepositoryImpl){
    delete("/todos/{id}"){
        val requestId = call.parameters["id"]?.toIntOrNull()
        requestId?.let {
            val response = toDoRepository.deleteTodoById(requestId)

            if(response){
                call.respond("Successfully deleted.")
            } else {
                call.respond("Error.")
            }
            return@delete
        }

        call.respond("Give id.")

    }
}