package app.eccweizhi.sensebox.gravity.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.eccweizhi.sensebox.gravity.R
import app.eccweizhi.sensebox.gravity.model.RawDataModel
import java.util.*

class RawDataVh(v: View) : RecyclerView.ViewHolder(v) {
    private val xAxisRawDataView: TextView = v.findViewById(R.id.xAxisRawDataView)
    private val yAxisRawDataView: TextView = v.findViewById(R.id.yAxisRawDataView)
    private val zAxisRawDataView: TextView = v.findViewById(R.id.zAxisRawDataView)

    fun bindData(rawData: RawDataModel) {
        xAxisRawDataView.text = String.format(Locale.US, "%.1f", rawData.x)
        yAxisRawDataView.text = String.format(Locale.US, "%.1f", rawData.y)
        zAxisRawDataView.text = String.format(Locale.US, "%.1f", rawData.z)
    }
}