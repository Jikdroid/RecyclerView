package org.inu.recyclerview

import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView

class MainDecoration(
    private val height: Float,
    private val padding: Float,
    @ColorInt
    private val color: Int
) : RecyclerView.ItemDecoration() {
    private val paint = Paint()
    init { paint.color = color }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingStart + padding
        val right = parent.width - parent.paddingEnd - padding
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

//            val left = params.marginStart + padding        여기의 left,right 를 하면 item 의 margin 에 따라 선이 가로가 바뀐다.
//            val right = parent.width - params.marginEnd - padding
            val top = (child.bottom + params.bottomMargin).toFloat()
            val bottom = top + height
            c. drawRect (left, top, right, bottom, paint)
        }
    }
}

