package app.eccweizhi.sensebox.gyroscope.model

import android.content.Context
import android.hardware.Sensor
import app.eccweizhi.sensebox.appcore.model.SensorType
import app.eccweizhi.sensebox.gyroscope.R

class GyroscopeType(context: Context) : SensorType(context) {
    override fun getAndroidIdentifier(): Int = Sensor.TYPE_GYROSCOPE

    override fun getIconResId(): Int = R.drawable.gyroscope_main_icon

    override fun getDisplayNameResId(): Int = R.string.gyroscope_display_name
}