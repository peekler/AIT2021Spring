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
import hu.ait.todorecyclerview.data.Todo
import hu.ait.todorecyclerview.touch.TouchCallback
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity(), TodoDialog.TodoHandler {

    lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title


        todoAdapter = TodoAdapter(this)
        recyclerTodo.adapter = todoAdapter

        val touchCallbakList = TouchCallback(todoAdapter)
        val itemTouchHelper = ItemTouchHelper(touchCallbakList)
        itemTouchHelper.attachToRecyclerView(recyclerTodo)



        //val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        //recyclerTodo.addItemDecoration(itemDecoration)
        //recyclerTodo.layoutManager = GridLayoutManager(this, 2)
        //recyclerTodo.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        fab.setOnClickListener {
            TodoDialog().show(supportFragmentManager, "TAG_TODO_DIALOG")
        }
    }


    override fun todoCreated(todo: Todo) {
        todoAdapter.addTodo(todo)
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