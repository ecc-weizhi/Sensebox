package app.eccweizhi.sensebox.pressure.model

import android.content.Context
import android.hardware.Sensor
import app.eccweizhi.sensebox.appcore.model.SensorType
import app.eccweizhi.sensebox.pressure.R

class PressureType(context: Context) : SensorType(context) {
    override fun getAndroidIdentifier(): Int = Sensor.TYPE_PRESSURE

    override fun getIconResId(): Int = R.drawable.pressure_main_icon

    override fun getDisplayNameResId(): Int = R.string.pressure_display_name
}