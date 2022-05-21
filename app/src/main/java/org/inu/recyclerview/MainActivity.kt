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

    private lateinit var data:MutableList<TodoData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setData()
        setRecyclerView()
        setAddButton()
        setRemoveButton()

    }


    private fun setData() {
        data = mutableListOf(
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

    private fun setRecyclerView() {
        val customDecoration = MainDecoration(10f, 30f, Color.GRAY)
        mainAdapter = MainAdapter().apply {
            submitList(data)
        }
        with(recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(customDecoration)
            adapter = mainAdapter
        }
    }

    private fun setAddButton() {
        addButton.setOnClickListener {
            val newData = mutableListOf<TodoData>().apply {
                data.forEach{
                    this.add(it)
                }
                    add(TodoData("새롭게 추가된 데이터!",false))
            }
            data = newData
            mainAdapter.submitList(newData)
        }
    }

    private fun setRemoveButton() {
        removeButton.setOnClickListener {
            val newData = mutableListOf<TodoData>().apply {
                data.forEach{
                    if (!it.done) {
                        this.add(it)
                    }
                }
            }
            data = newData
            mainAdapter.submitList(newData)
        }
    }
}