package org.inu.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class MainAdapter: RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private val asyncListDiffer = AsyncListDiffer(this, AsyncDiffCallback)
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val todo:TextView = view.findViewById(R.id.itemTextView)
        val done:CheckBox = view.findViewById(R.id.checkBox)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println(position)
        val content = asyncListDiffer.currentList[position]
        holder.todo.text = content.todo
        holder.done.isChecked = content.done
        doneClick(holder.done,content)
    }

    override fun getItemCount() = asyncListDiffer.currentList.size

    private fun doneClick(done:CheckBox,content:TodoData){
        done.setOnClickListener {
            content.done = done.isChecked
        }
    }

    fun update(newData: MutableList<TodoData>) = asyncListDiffer.submitList(newData)


    object AsyncDiffCallback : DiffUtil.ItemCallback<TodoData>() {
        override fun areContentsTheSame(oldItem: TodoData, newItem: TodoData): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: TodoData, newItem: TodoData): Boolean {
            return oldItem.todo == newItem.todo
        }
    }
}