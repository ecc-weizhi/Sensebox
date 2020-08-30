package app.eccweizhi.sensebox.rotationvector.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.eccweizhi.sensebox.rotationvector.R
import app.eccweizhi.sensebox.rotationvector.model.RawDataModel
import java.util.*

class RawDataVh(v: View) : RecyclerView.ViewHolder(v) {
    private val xAxisRawDataView: TextView = v.findViewById(R.id.xAxisRawDataView)
    private val yAxisRawDataView: TextView = v.findViewById(R.id.yAxisRawDataView)
    private val zAxisRawDataView: TextView = v.findViewById(R.id.zAxisRawDataView)
    private val scalarRawDataView: TextView = v.findViewById(R.id.scalarRawDataView)

    fun bindData(rawData: RawDataModel) {
        xAxisRawDataView.text = String.format(Locale.US, "%.2f", rawData.x)
        yAxisRawDataView.text = String.format(Locale.US, "%.2f", rawData.y)
        zAxisRawDataView.text = String.format(Locale.US, "%.2f", rawData.z)
        scalarRawDataView.text = String.format(Locale.US, "%.2f", rawData.scalar)
    }
}