package hu.ait.todorecyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.ait.todorecyclerview.R
import hu.ait.todorecyclerview.ScrollingActivity
import hu.ait.todorecyclerview.data.Todo
import hu.ait.todorecyclerview.touch.TouchHelper
import kotlinx.android.synthetic.main.todo_row.view.*
import java.util.*

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>, TouchHelper {

    var todoItems = mutableListOf<Todo>(
        Todo("2021. 03. 10.", false, "Todo1"),
        Todo("2021. 03. 11.", false, "Todo2"),
    )
    val context: Context

    constructor(context: Context) : super() {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.todo_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todoItems[position]

        holder.cbDone.text = todo.todoText
        holder.cbDone.isChecked = todo.done
        holder.tvDate.text = todo.createDate

        holder.btnDelete.setOnClickListener {
            deleteTodo(holder.adapterPosition)
        }
    }

    private var deletedTodo: Todo? = null
    private var deleteIndex = -1

    fun deleteTodo(position: Int) {
        deletedTodo = todoItems[position]
        deleteIndex = position

        todoItems.removeAt(position)
        notifyItemRemoved(position)

        (context as ScrollingActivity).showDeleteMessage()
    }

    fun restoreTodo() {
        todoItems.add(deleteIndex, deletedTodo!!)
        notifyItemInserted(deleteIndex)
    }


    fun addTodo(todo: Todo) {
        todoItems.add(todo)

        //notifyDataSetChanged() // redraw the whole RecyclerView
        notifyItemInserted(todoItems.lastIndex)
    }


    override fun onDismissed(position: Int) {
        deleteTodo(position)
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(todoItems, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDate = itemView.tvDate
        val cbDone = itemView.cbDone
        val btnDelete = itemView.btnDelete
    }

}