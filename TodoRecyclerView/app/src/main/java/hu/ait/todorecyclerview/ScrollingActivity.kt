package hu.ait.todorecyclerview

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import hu.ait.todorecyclerview.adapter.TodoAdapter
import hu.ait.todorecyclerview.data.AppDatabase
import hu.ait.todorecyclerview.data.Todo
import hu.ait.todorecyclerview.touch.TouchCallback
import kotlinx.android.synthetic.main.activity_scrolling.*
import java.util.*
import kotlin.concurrent.thread

import androidx.lifecycle.Observer

class ScrollingActivity : AppCompatActivity(), TodoDialog.TodoHandler {

    lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title


        initRecyclerView()



        fab.setOnClickListener {
            TodoDialog().show(supportFragmentManager, "TAG_TODO_DIALOG")
        }
    }

    private fun initRecyclerView() {
        todoAdapter = TodoAdapter(this)
        recyclerTodo.adapter = todoAdapter

        AppDatabase.getInstance(this).todoDao().getAllTodo()
            .observe(this, Observer { items ->
                todoAdapter.submitList(items)
            })

        val touchCallbakList = TouchCallback(todoAdapter)
        val itemTouchHelper = ItemTouchHelper(touchCallbakList)
        itemTouchHelper.attachToRecyclerView(recyclerTodo)
    }


    override fun todoCreated(todo: Todo) {
        thread {
            AppDatabase.getInstance(this).todoDao().addTodo(todo)
        }
    }


    public fun showDeleteMessage() {
        Snackbar.make(recyclerTodo, "Todo removed", Snackbar.LENGTH_LONG)
            .setAction("Undo", object: View.OnClickListener {
                override fun onClick(v: View?) {
                    todoAdapter.restoreTodo()
                }
            })
            .show()
    }

}