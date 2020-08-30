package app.eccweizhi.sensebox.appcore

import android.content.Context
import android.util.DisplayMetrics

class UiUtil {
    companion object {
        @JvmStatic
        fun dpToPx(context: Context, dp: Int): Int {
            return dp * context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
        }

        @JvmStatic
        fun pxToDp(context: Context, px: Int): Int {
            return px * DisplayMetrics.DENSITY_DEFAULT / context.resources.displayMetrics.densityDpi
        }
    }
}