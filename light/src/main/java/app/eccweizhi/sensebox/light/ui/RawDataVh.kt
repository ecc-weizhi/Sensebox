package app.eccweizhi.sensebox.light.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.eccweizhi.sensebox.light.R
import java.util.*

class RawDataVh(v: View) : RecyclerView.ViewHolder(v) {
    private val rawDataView: TextView = v.findViewById(R.id.rawDataView)

    fun bindData(rawData: Float) {
        rawDataView.text = String.format(Locale.US, "%.1f", rawData)
    }
}