package org.inu.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainAdapter: ListAdapter<TodoData, MainAdapter.ViewHolder>(AsyncDiffCallback) {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val todo:TextView = view.findViewById(R.id.itemTextView)
        val done:CheckBox = view.findViewById(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context,R.anim.translate)
        val content = currentList[position]
        holder.todo.text = content.todo
        holder.done.isChecked = content.done
        doneClick(holder.done,content)
    }

    override fun getItemCount() = currentList.size

    private fun doneClick(done:CheckBox,content:TodoData){
        done.setOnClickListener {
            content.done = done.isChecked
        }
    }

    // 현재 선택된 데이터와 드래그한 위치에 있는 데이터를 교환
    fun swapData(fromPos: Int, toPos: Int) {
        var swapList = listOf<TodoData>()
        swapList = swapList.plus(currentList)
        Collections.swap(swapList, fromPos, toPos)
        submitList(swapList)
    }

    object AsyncDiffCallback : DiffUtil.ItemCallback<TodoData>() {
        override fun areContentsTheSame(oldItem: TodoData, newItem: TodoData): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: TodoData, newItem: TodoData): Boolean {
            return oldItem.todo == newItem.todo
        }
    }
}