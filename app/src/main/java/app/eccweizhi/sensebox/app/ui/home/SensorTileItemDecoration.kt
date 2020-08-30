package app.eccweizhi.sensebox.app.ui.home

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class SensorTileItemDecoration(
    context: Context,
    @DimenRes private val resId: Int
) : RecyclerView.ItemDecoration() {
    private val marginPx: Float = context.resources.getDimension(resId)
    private val halfMarginPx = marginPx / 2

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = marginPx.toInt()
        outRect.left = halfMarginPx.toInt()
        outRect.right = halfMarginPx.toInt()
    }

}