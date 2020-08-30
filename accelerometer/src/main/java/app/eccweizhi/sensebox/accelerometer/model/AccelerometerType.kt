package app.eccweizhi.sensebox.accelerometer.model

import android.content.Context
import android.hardware.Sensor
import app.eccweizhi.sensebox.accelerometer.R
import app.eccweizhi.sensebox.appcore.model.SensorType

class AccelerometerType(context: Context) : SensorType(context) {
    override fun getIconResId(): Int = R.drawable.accelerometer_main_icon

    override fun getDisplayNameResId(): Int = R.string.accelerometer_display_name

    override fun getAndroidIdentifier(): Int = Sensor.TYPE_ACCELEROMETER
}