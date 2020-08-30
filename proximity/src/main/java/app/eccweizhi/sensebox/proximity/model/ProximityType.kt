package app.eccweizhi.sensebox.proximity.model

import android.content.Context
import android.hardware.Sensor
import app.eccweizhi.sensebox.appcore.model.SensorType
import app.eccweizhi.sensebox.proximity.R

class ProximityType(context: Context) : SensorType(context) {
    override fun getAndroidIdentifier(): Int = Sensor.TYPE_PROXIMITY

    override fun getIconResId(): Int = R.drawable.proximity_main_icon

    override fun getDisplayNameResId(): Int = R.string.proximity_display_name
}