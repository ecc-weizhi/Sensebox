package app.eccweizhi.sensebox.appcore

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class LinearSpaceItemDecoration(
    context: Context,
    @DimenRes private val resId: Int
) : RecyclerView.ItemDecoration() {
    private val marginPx: Float = context.resources.getDimension(resId)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = marginPx.toInt()
        outRect.left = marginPx.toInt()
        outRect.right = marginPx.toInt()
    }
}