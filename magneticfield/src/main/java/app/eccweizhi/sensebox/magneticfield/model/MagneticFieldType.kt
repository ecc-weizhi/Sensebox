package app.eccweizhi.sensebox.magneticfield.model

import android.content.Context
import android.hardware.Sensor
import app.eccweizhi.sensebox.appcore.model.SensorType
import app.eccweizhi.sensebox.magneticfield.R

class MagneticFieldType(context: Context) : SensorType(context) {
    override fun getAndroidIdentifier(): Int = Sensor.TYPE_MAGNETIC_FIELD

    override fun getIconResId(): Int = R.drawable.magneticfield_main_icon

    override fun getDisplayNameResId(): Int = R.string.magneticfield_display_name
}