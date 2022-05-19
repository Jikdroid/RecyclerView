package org.inu.recyclerview

import android.graphics.Color
import android.os.Bundle
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recyclerView)
    }

    private val addButton: AppCompatButton by lazy {
        findViewById(R.id.addButton)
    }

    private val removeButton: AppCompatButton by lazy {
        findViewById(R.id.removeButton)
    }


    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setData().let {
            setRecyclerView(it)
            setAddButton(it)
            setRemoveButton(it)
        }
    }


    private fun setData(): MutableList<TodoData> {
        return mutableListOf(
            TodoData("운동하기", false),
            TodoData("샤워하기", false),
            TodoData("백준 문제 풀기", false),
            TodoData("RecyclerView 공부하기", false),
            TodoData("운동하기", false),
            TodoData("샤워하기", false),
            TodoData("백준 문제 풀기", false),
            TodoData("RecyclerView 공부하기", false),
            TodoData("운동하기", false),
            TodoData("샤워하기", false),
            TodoData("백준 문제 풀기", false),
            TodoData("RecyclerView 공부하기", false),
            TodoData("운동하기", false),
            TodoData("샤워하기", false),
            TodoData("백준 문제 풀기", false),
            TodoData("RecyclerView 공부하기", false),
        )
    }

    private fun setRecyclerView(data: MutableList<TodoData>) {
        val customDecoration = MainDecoration(10f, 30f, Color.GRAY)
        mainAdapter = MainAdapter(data)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(customDecoration)
            adapter = mainAdapter
        }
    }

    private fun setAddButton(data: MutableList<TodoData>) {
        addButton.setOnClickListener {
            val newData = mutableListOf<TodoData>().apply {
                data.forEach{
                    this.add(it)
                }
                    add(TodoData("새롭게 추가된 데이터!",false))
            }
            mainAdapter.update(newData)
        }
    }

    private fun setRemoveButton(data: MutableList<TodoData>) {
        removeButton.setOnClickListener {
            val newData = mutableListOf<TodoData>().apply {
                data.forEach{
                    if (!it.done) {
                        this.add(it)
                    }
                }
            }
            mainAdapter.update(newData)
        }
    }
}