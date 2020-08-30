package app.eccweizhi.sensebox.gravity.model

import android.content.Context
import android.hardware.Sensor
import app.eccweizhi.sensebox.appcore.model.SensorType
import app.eccweizhi.sensebox.gravity.R

class GravityType(context: Context) : SensorType(context) {
    override fun getAndroidIdentifier(): Int = Sensor.TYPE_GRAVITY

    override fun getIconResId(): Int = R.drawable.gravity_main_icon

    override fun getDisplayNameResId(): Int = R.string.gravity_display_name
}