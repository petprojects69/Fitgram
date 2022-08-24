package ru.petprojects69.fitgram.ui

import android.content.res.Resources
import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

private const val WIDTH_SCROLL_X = 68f

class ItemTouchHelperCallback(resources: Resources) : ItemTouchHelper.Callback() {

    private val limitScrollX = dpTpPx(WIDTH_SCROLL_X, resources)
    private var currentScrollX = 0
    private var currentScrollXWhenIsActive = 0
    private var initXWhenIsActive = 0f
    private var firstInActive = false

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = 0
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END

        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return Integer.MAX_VALUE.toFloat()
    }

    override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
        return Integer.MAX_VALUE.toFloat()
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if (dX == 0f) {
                currentScrollX = viewHolder.itemView.scrollX
                firstInActive = true
            }

            if (isCurrentlyActive) {
                /** здесь отрисовывается swipe когда пользователь держит палец на экране*/
                var scrollOffset = currentScrollX + (-dX).toInt()
                if (scrollOffset > limitScrollX) {
                    scrollOffset = limitScrollX
                } else if (scrollOffset < -limitScrollX) {
                    scrollOffset = -limitScrollX
                }
                viewHolder.itemView.scrollTo(scrollOffset, 0)

            } else {
                /** здесь происходит отрисовка item когда пользователь убрал палец*/
                if (firstInActive) {
                    firstInActive = false
                    currentScrollXWhenIsActive = viewHolder.itemView.scrollX
                    initXWhenIsActive = dX
                }

                /** если swipe left недостаточной длины, возвращаем item в исходное положение */
                if (viewHolder.itemView.scrollX in 1 until limitScrollX) {
                    viewHolder.itemView.scrollTo(
                        (currentScrollXWhenIsActive * dX / initXWhenIsActive).toInt(),
                        0
                    )
                }
                /** если swipe right недостаточной длины, возвращаем item в исходное положение */
                if (viewHolder.itemView.scrollX in (-limitScrollX + 1) until -1) {
                    viewHolder.itemView.scrollTo(
                        (currentScrollXWhenIsActive * dX / initXWhenIsActive).toInt(),
                        0
                    )
                }
            }
        }
    }

    private fun dpTpPx(dpValue: Float, resources: Resources): Int {
        return (dpValue * resources.displayMetrics.density).toInt()
    }
}