package com.example.database

import com.example.database.entities.TodoEntity
import com.example.database.entities.TodoTable
import com.example.models.SetTodo
import com.example.models.ToDo
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.filter
import org.ktorm.entity.firstOrNull
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

object DatabaseConnection {

    private const val url = "jdbc:mysql://localhost:3306/ktortodo"
    private const val driver = "com.mysql.cj.jdbc.Driver"
    private const val user = "root"
    private const val password = "test-password"

    private var database : Database = Database.connect(
        url = url,
        driver = driver,
        user = user,
        password = password
    )

    fun getAllTodos(): List<TodoEntity> {
        return database.sequenceOf(TodoTable).toList()
    }

    fun getTodoById(id: Int): TodoEntity? {
        return database.sequenceOf(TodoTable)
            .firstOrNull { it.id eq id }
    }

    fun addTodo(setTodo: SetTodo) : ToDo {
        val insertedId = database.insertAndGenerateKey(TodoTable) {
            set(it.title,setTodo.title)
            set(it.done,setTodo.done)
        } as Int

        return ToDo(insertedId,setTodo.title,setTodo.done)
    }

    fun updateTodoById(id: Int, setTodo: SetTodo) : Boolean {
        val updatedRows = database.update(TodoTable){table ->
            set(table.title,setTodo.title)
            set(table.done,setTodo.done)
            where {
                table.id eq id
            }
        }

        return updatedRows > 0
    }

    fun deleteTodoById(id:Int) : Boolean {
        val deletedRows = database.delete(TodoTable) {
            it.id eq id
        }

        return deletedRows > 0
    }

}