package app.eccweizhi.sensebox.ambienttemperature.model

import android.content.Context
import android.hardware.Sensor
import app.eccweizhi.sensebox.ambienttemperature.R
import app.eccweizhi.sensebox.appcore.model.SensorType

class AmbientTemperatureType(context: Context) : SensorType(context) {
    override fun getAndroidIdentifier(): Int = Sensor.TYPE_AMBIENT_TEMPERATURE

    override fun getIconResId(): Int = R.drawable.ambienttemperature_main_icon

    override fun getDisplayNameResId(): Int =
        R.string.ambienttemperature_display_name
}