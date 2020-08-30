package app.eccweizhi.sensebox.temperature.model

import android.content.Context
import android.hardware.Sensor
import app.eccweizhi.sensebox.appcore.model.SensorType
import app.eccweizhi.sensebox.temperature.R

class TemperatureType(context: Context) : SensorType(context) {
    override fun getAndroidIdentifier(): Int = Sensor.TYPE_TEMPERATURE

    override fun getIconResId(): Int = R.drawable.temperature_main_icon

    override fun getDisplayNameResId(): Int = R.string.temperature_display_name
}