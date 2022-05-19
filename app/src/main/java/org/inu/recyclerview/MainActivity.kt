package org.inu.recyclerview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val recyclerView : RecyclerView by lazy {
        findViewById(R.id.recyclerView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = listOf(
            TodoData("운동하기",false),
            TodoData("샤워하기",false),
            TodoData("백준 문제 풀기",false),
            TodoData("RecyclerView 공부하기",false),
            TodoData("운동하기",false),
            TodoData("샤워하기",false),
            TodoData("백준 문제 풀기",false),
            TodoData("RecyclerView 공부하기",false),
            TodoData("운동하기",false),
            TodoData("샤워하기",false),
            TodoData("백준 문제 풀기",false),
            TodoData("RecyclerView 공부하기",false),
            TodoData("운동하기",false),
            TodoData("샤워하기",false),
            TodoData("백준 문제 풀기",false),
            TodoData("RecyclerView 공부하기",false),
        )
        val customDecoration = MainDecoration(10f, 30f, Color.GRAY)
        recyclerView.addItemDecoration(customDecoration)


        recyclerView.adapter = MainAdapter(data)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}