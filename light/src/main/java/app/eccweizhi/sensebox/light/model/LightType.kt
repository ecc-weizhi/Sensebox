package app.eccweizhi.sensebox.light.model

import android.content.Context
import android.hardware.Sensor
import app.eccweizhi.sensebox.appcore.model.SensorType
import app.eccweizhi.sensebox.light.R

class LightType(context: Context) : SensorType(context) {
    override fun getAndroidIdentifier(): Int = Sensor.TYPE_LIGHT

    override fun getIconResId(): Int = R.drawable.light_main_icon

    override fun getDisplayNameResId(): Int = R.string.light_display_name
}