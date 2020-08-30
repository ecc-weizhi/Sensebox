package app.eccweizhi.sensebox.relativehumidity.model

import android.content.Context
import android.hardware.Sensor
import app.eccweizhi.sensebox.appcore.model.SensorType
import app.eccweizhi.sensebox.relativehumidity.R

class RelativeHumidityType(context: Context) : SensorType(context) {
    override fun getAndroidIdentifier(): Int = Sensor.TYPE_RELATIVE_HUMIDITY

    override fun getIconResId(): Int = R.drawable.relativehumidity_main_icon

    override fun getDisplayNameResId(): Int = R.string.relativehumidity_display_name
}