package hu.ait.todorecyclerview.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun getAllTodo() : LiveData<List<Todo>>

    @Insert
    fun addTodo(todo: Todo) : Long

    @Delete
    fun deleteTodo(todo: Todo)

}