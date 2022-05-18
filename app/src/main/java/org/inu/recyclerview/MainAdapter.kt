package org.inu.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(private val data: List<TodoData>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val todo:TextView = view.findViewById(R.id.itemTextView)
        val done:CheckBox = view.findViewById(R.id.checkBox)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.todo.text = data[position].todo
        holder.done.isChecked = data[position].done
        doneClick(holder.done,position)
    }

    private fun doneClick(done:CheckBox,position:Int){
        done.setOnClickListener {
            data[position].done = done.isChecked
        }
    }

    override fun getItemCount() = data.size
}