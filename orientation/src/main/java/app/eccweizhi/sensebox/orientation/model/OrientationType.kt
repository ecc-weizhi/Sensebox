package app.eccweizhi.sensebox.orientation.model

import android.content.Context
import android.hardware.Sensor
import app.eccweizhi.sensebox.appcore.model.SensorType
import app.eccweizhi.sensebox.orientation.R

class OrientationType(context: Context) : SensorType(context) {
    override fun getAndroidIdentifier(): Int = Sensor.TYPE_ORIENTATION

    override fun getIconResId(): Int = R.drawable.orientation_main_icon

    override fun getDisplayNameResId(): Int = R.string.orientation_display_name
}