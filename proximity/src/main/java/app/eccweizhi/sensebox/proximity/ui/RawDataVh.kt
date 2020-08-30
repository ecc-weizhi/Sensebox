package app.eccweizhi.sensebox.proximity.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.eccweizhi.sensebox.proximity.R
import java.util.*

class RawDataVh(v: View) : RecyclerView.ViewHolder(v) {
    private val rawDataView: TextView = v.findViewById(R.id.rawDataView)

    fun bindData(rawData: Float) {
        rawDataView.text = String.format(Locale.US, "%.2f", rawData)
    }
}