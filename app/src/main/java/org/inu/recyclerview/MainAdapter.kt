package org.inu.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainAdapter: ListAdapter<TodoData, MainAdapter.ViewHolder>(AsyncDiffCallback) {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val todo:TextView = view.findViewById(R.id.itemTextView)
        val done:CheckBox = view.findViewById(R.id.checkBox)
        val remove:TextView = view.findViewById(R.id.tvRemove)
        val swipeView:ConstraintLayout = view.findViewById(R.id.swipe_view)
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
        holder.remove.setOnClickListener {
            holder.swipeView.animate().x(0f).setDuration(100L).start()
            removeData(holder.bindingAdapterPosition)   // adapter 에서의 position
        }
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

    private fun removeData(position:Int){
        var removedList = mutableListOf<TodoData>()
        removedList = removedList.plus(currentList) as MutableList<TodoData>
        removedList.removeAt(position)
        submitList(removedList)
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