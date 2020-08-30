package app.eccweizhi.sensebox.gyroscope.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.eccweizhi.sensebox.gyroscope.R
import app.eccweizhi.sensebox.gyroscope.model.RawDataModel
import java.util.*

class RawDataVh(v: View) : RecyclerView.ViewHolder(v) {
    private val xAxisRawDataView: TextView = v.findViewById(R.id.xAxisRawDataView)
    private val yAxisRawDataView: TextView = v.findViewById(R.id.yAxisRawDataView)
    private val zAxisRawDataView: TextView = v.findViewById(R.id.zAxisRawDataView)

    fun bindData(rawData: RawDataModel) {
        xAxisRawDataView.text = String.format(Locale.US, "%.2f", rawData.x)
        yAxisRawDataView.text = String.format(Locale.US, "%.2f", rawData.y)
        zAxisRawDataView.text = String.format(Locale.US, "%.2f", rawData.z)
    }
}