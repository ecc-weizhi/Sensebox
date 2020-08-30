package app.eccweizhi.sensebox.linearacceleration.model

import android.content.Context
import android.hardware.Sensor
import app.eccweizhi.sensebox.appcore.model.SensorType
import app.eccweizhi.sensebox.linearacceleration.R

class LinearAccelerationType(context: Context) : SensorType(context) {
    override fun getAndroidIdentifier(): Int = Sensor.TYPE_LINEAR_ACCELERATION

    override fun getIconResId(): Int = R.drawable.linearacceleration_main_icon

    override fun getDisplayNameResId(): Int = R.string.linearacceleration_display_name
}