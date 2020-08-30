package app.eccweizhi.sensebox.orientation.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.eccweizhi.sensebox.orientation.R
import app.eccweizhi.sensebox.orientation.model.RawDataModel
import java.util.*


class RawDataVh(v: View) : RecyclerView.ViewHolder(v) {
    private val azimuthRawDataView: TextView = v.findViewById(R.id.azimuthRawDataView)
    private val pitchRawDataView: TextView = v.findViewById(R.id.pitchRawDataView)
    private val rollRawDataView: TextView = v.findViewById(R.id.rollRawDataView)

    fun bindData(rawData: RawDataModel) {
        azimuthRawDataView.text = String.format(Locale.US, "%.2f", rawData.azimuth)
        pitchRawDataView.text = String.format(Locale.US, "%.2f", rawData.pitch)
        rollRawDataView.text = String.format(Locale.US, "%.2f", rawData.roll)
    }
}