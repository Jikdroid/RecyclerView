package org.inu.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(private val data: MutableList<TodoData>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {
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
        doneClick(holder.done,holder.bindingAdapterPosition)
    }

    override fun getItemCount() = data.size

    private fun doneClick(done:CheckBox,position:Int){
        done.setOnClickListener {
            data[position].done = done.isChecked
        }
    }

    fun update(newData: MutableList<TodoData>) {
        val diffResult = DiffUtil.calculateDiff(ContentDiffUtil(data,newData))
        data.clear()
        data.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }


    class ContentDiffUtil(private val oldList: List<TodoData>, private val currentList: List<TodoData>) : DiffUtil.Callback() {
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == currentList[newItemPosition]
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].todo == currentList[newItemPosition].todo
        }

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = currentList.size
    }
}