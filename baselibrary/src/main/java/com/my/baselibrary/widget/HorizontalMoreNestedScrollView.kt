package com.my.baselibrary.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.NestedScrollingParent
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my.baselibrary.R

/**
 * Create by xk
 * 2021/02/14
 * description: 简单的加载更多View，基于NestedScrollingParent实现
 * 使用方法，将RecycleView和加载更多布局作为子View即可
 * xml:
 * <HorizontalMoreNestedScrollView
        android:id="@+id/view_more"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        android:background="#A7FFEB"
        android:orientation="horizontal">

        <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/recycler_view_h"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_constraintTop_toTopOf="parent" />

        <androidx.constraintlayout.widget.ConstraintLayout
            android:id="@+id/view_end"
            android:layout_width="60dp"
            android:layout_height="match_parent"
            android:background="#FF80AB">

            <TextView
                app:layout_constraintHorizontal_bias="0.4"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                android:id="@+id/tv_more"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:ems="1"
                android:gravity="center_horizontal" />
        </androidx.constraintlayout.widget.ConstraintLayout>

    </HorizontalMoreNestedScrollView>
 */
class HorizontalMoreNestedScrollView : LinearLayout, NestedScrollingParent {
    private val TAG = this.javaClass.simpleName

    private lateinit var endView: View
    private lateinit var txtMoreView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var mLayoutManager: LinearLayoutManager
    private val animator = ObjectAnimator.ofFloat(this, "mScrollX2", scrollX.toFloat(), 0f)
    private var mEndViewWidth = 0
    private var mOnMoreListener: (() -> Unit)? = null
    fun setOnMoreListener(listener: (() -> Unit)?) {
        this.mOnMoreListener = listener
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    var mScrollX2: Float = 0f
        set(value) {
            Log.d(TAG, "set mScrollX2: $value")
            this.scrollX = value.toInt()
            field = value
        }

    override fun onFinishInflate() {
        super.onFinishInflate()

        endView = findViewById(R.id.view_end)
        txtMoreView = findViewById(R.id.tv_more)
        recyclerView = findViewById(R.id.recycler_view_h)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mEndViewWidth = endView.measuredWidth
        mLayoutManager = recyclerView.layoutManager as LinearLayoutManager

    }


    override fun scrollTo(x: Int, y: Int) {
        var mScrollToX = x

        if (mScrollToX > mEndViewWidth) {
            mScrollToX = mEndViewWidth
        }

        if (mScrollToX != scrollX) {
            Log.d(TAG, "scrollTo: $mScrollToX")
            super.scrollTo(mScrollToX, y)
        }
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_HORIZONTAL
    }


    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        val showEnd = dx > 0 && !target.canScrollHorizontally(1)
        val hiddenEnd = dx < 0 && scrollX <= mEndViewWidth && scrollX > 0

        if (showEnd || hiddenEnd && mLayoutManager.findLastCompletelyVisibleItemPosition() + 1 == mLayoutManager.itemCount) {
            if (animator.isRunning) {
                animator.cancel()
            }

            scrollBy(dx / 2, 0)
            consumed[0] = dx

            if (scrollX <= mEndViewWidth * 0.7) {
                txtMoreView.text = "滑动查看更多"
            } else {
                txtMoreView.text = "松开查看更多"
            }
        }
    }

    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
        return false
    }

    override fun onStopNestedScroll(target: View) {
        if (mLayoutManager.findLastCompletelyVisibleItemPosition() + 1 == mLayoutManager.itemCount) {
            if (scrollX > mEndViewWidth * 0.7) {
                mOnMoreListener?.invoke()
            }

            animator.setFloatValues(scrollX.toFloat(),0f)
            animator.start()
        }
    }
}