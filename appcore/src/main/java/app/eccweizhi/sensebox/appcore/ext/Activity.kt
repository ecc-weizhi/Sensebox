package app.eccweizhi.sensebox.appcore.ext

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager

/**
 * @param v The currently focused view, which would like to receive
 * soft keyboard input.
 */
fun Activity.showKeyboard(v: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT)
}

/**
 * @param v Any [View] in the same [Window] where soft keyboard appears.
 */
fun Activity.hideKeyboard(v: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(v.windowToken, 0)
}