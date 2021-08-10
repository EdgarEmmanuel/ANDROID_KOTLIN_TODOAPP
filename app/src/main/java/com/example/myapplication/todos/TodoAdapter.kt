package com.example.myapplication.todos

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R


class TodoAdapter(private val todos: MutableList<Todo>)
    : RecyclerView.Adapter<TodoAdapter.ViewHolder>(){

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo_details,
                parent,
                false
            )
        )
    }

    fun addOneTodo(todo : Todo){
        todos.add(todo);
        var atThelastPosition = todos.size - 1 ;
        notifyItemInserted(atThelastPosition);
    }

    fun deleteOneTodo(){
        todos.removeAll { todo -> todo.isFinished }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTodo = todos[position]
        val elementTitle = holder.itemView.findViewById<TextView>(R.id.TEXT_VIEW_elementTitle);
        val isElementTaskFinished = holder.itemView.findViewById<CheckBox>(R.id.CHECKBOX_elementIsFinished) ;

        elementTitle.setText(currentTodo.title);
        isElementTaskFinished.isChecked = currentTodo.isFinished ;

        holder.itemView.apply {

            changeTextDisplayByTaskStatus(
                elementTitle,
                currentTodo.isFinished
            )

            isElementTaskFinished.setOnCheckedChangeListener{ checkbox,
              isChecked -> changeTextDisplayByTaskStatus(elementTitle, isChecked)
                currentTodo.isFinished = ! currentTodo.isFinished ;
            }

        }
    }

    private fun changeTextDisplayByTaskStatus(text_title : TextView, isChecked: Boolean){
        if(isChecked){
            text_title.paintFlags = text_title.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG;
        } else {
            text_title.paintFlags = text_title.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun getItemCount(): Int {
        return todos.size;
    }
}