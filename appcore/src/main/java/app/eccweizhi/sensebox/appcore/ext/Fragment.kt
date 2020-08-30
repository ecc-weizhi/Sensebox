package app.eccweizhi.sensebox.appcore.ext

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

/**
 * @param v The currently focused view, which would like to receive
 * soft keyboard input.
 */
fun Fragment.showKeyboard(context: Context, v: View) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT)
}

/**
 * @param v Any [View] in the same [Window] where soft keyboard appears.
 */
fun Fragment.hideKeyboard(context: Context, v: View) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(v.windowToken, 0)
}