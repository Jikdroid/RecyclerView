package org.inu.recyclerview

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

// Custom recyclerView item animation
class MainItemAnimator(private val context: Context) : DefaultItemAnimator(){

    override fun animateRemove(holder: RecyclerView.ViewHolder): Boolean {  // 삭제 애니메이션
        holder.itemView.animation = AnimationUtils.loadAnimation(context,android.R.anim.fade_out).apply {
            setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationStart(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    dispatchRemoveFinished(holder)
                }
                override fun onAnimationRepeat(animation: Animation?) {
                }
            })
            start()
        }
        return false
    }

    override fun animateAdd(holder: RecyclerView.ViewHolder): Boolean {     // 추가 애니매이션
        holder.itemView.animation = AnimationUtils.loadAnimation(context,android.R.anim.fade_in).apply {
            setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationStart(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    dispatchAddFinished(holder)
                }
                override fun onAnimationRepeat(animation: Animation?) {
                }
            })
            start()
        }
        return false
    }

    // animation duration
    override fun getRemoveDuration(): Long = 1500
    override fun getAddDuration(): Long = 1500
}
