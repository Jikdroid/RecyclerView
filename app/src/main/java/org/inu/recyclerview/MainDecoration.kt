package org.inu.recyclerview

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView


class MainDecoration(
    private val height: Float,
    private val padding: Float,
    @ColorInt
    private val color: Int
) : RecyclerView.ItemDecoration() {
    private val paint = Paint()

    init {
        paint.color = color
    }


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = padding.toInt()
        outRect.top = padding.toInt()
        outRect.left = padding.toInt()
        outRect.right = padding.toInt()

        // Blog 예제 (GridLayout 에 왼쪽, 오른쪽 View 의 여백을 다르게)
//        val position: Int = parent.getChildAdapterPosition(view)
//        if (position == 0 || position == 1) {
//            // 첫번 째 줄 아이템
//            outRect.top = 10
//            outRect.bottom = 10
//        } else {
//            outRect.bottom = 10
//        }
//        // spanIndex = 0 -> 왼쪽
//        // spanIndex = 1 -> 오른쪽
//        val lp = view.layoutParams as GridLayoutManager.LayoutParams
//        val spanIndex = lp.spanIndex
//
//        if (spanIndex == 0) {
//            //왼쪽 아이템
//            outRect.left = 10
//            outRect.right = 10
//        } else if (spanIndex == 1) {
//            //오른쪽 아이템
//            outRect.left = 5
//            outRect.right = 10
//        }
    }

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
            c.drawRect(left, top, right, bottom, paint)
        }
    }
}

