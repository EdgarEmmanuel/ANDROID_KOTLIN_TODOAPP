package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.todos.Todo
import com.example.myapplication.todos.TodoAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter : TodoAdapter ;
    private lateinit var deleteButton : Button;
    private lateinit var saveButton : Button ;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var todos = mutableListOf<Todo>()
        todoAdapter = TodoAdapter(todos);
        val recyclerViewTodos : RecyclerView = findViewById(R.id.RECYCLER_VIEW_todoElements);
        recyclerViewTodos.adapter = todoAdapter;

        recyclerViewTodos.layoutManager = LinearLayoutManager(this)

        deleteButton = findViewById(R.id.BUTTON_deleteElement);
        saveButton = findViewById(R.id.BUTTON_saveElement);

        saveButton.setOnClickListener {
            val todoTitle : EditText = findViewById<EditText>(R.id.EDIT_TEXT_inputElement)
            val todo = Todo(todoTitle.text.toString().trim())
            todoAdapter.addOneTodo(todo);
            todoTitle.text.clear()
        };

        deleteButton.setOnClickListener{
            todoAdapter.deleteOneTodo()
        }
    }
}