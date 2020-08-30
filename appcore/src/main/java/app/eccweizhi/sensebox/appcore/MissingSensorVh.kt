package app.eccweizhi.sensebox.appcore

import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView

class MissingSensorVh(v: View) : RecyclerView.ViewHolder(v) {
    private val errorTextView: TextView = v.findViewById(R.id.errorTextView)

    fun bindData(sensorName: String) {
        val msg =
            errorTextView.context.getString(R.string.appcore_missing_sensor_message, sensorName)
        errorTextView.text = msg
    }

    fun bindData(@StringRes sensorNameResId: Int) {
        val sensorName = errorTextView.context.getString(sensorNameResId)
        val msg =
            errorTextView.context.getString(R.string.appcore_missing_sensor_message, sensorName)
        errorTextView.text = msg
    }
}