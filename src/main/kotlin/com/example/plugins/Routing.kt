package com.example.plugins

import com.example.models.ToDo
import com.example.repository.TodoRepositoryImpl
import com.example.routes.*
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    install(StatusPages) {
        exception<AuthenticationException> { call, _ ->
            call.respond(HttpStatusCode.Unauthorized)
        }
        exception<AuthorizationException> { call, _ ->
            call.respond(HttpStatusCode.Forbidden)
        }
    
    }
    

    routing {

        val todoRepository = TodoRepositoryImpl()

        root()
        getAllTodoList(todoRepository)
        getTodoById(todoRepository)
        updateTodoById(todoRepository)
        deleteTodoById(todoRepository)
        postTodoList(todoRepository)


        static {
            resources("static")
        }
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
