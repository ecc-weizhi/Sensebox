package app.eccweizhi.sensebox.appcore

import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView

class PageTitleVh(v: View) : RecyclerView.ViewHolder(v) {
    private val titleView: TextView = v as TextView

    fun bindData(title: String) {
        titleView.text = title
    }

    fun bindData(@StringRes titleResId: Int) {
        titleView.setText(titleResId)
    }
}