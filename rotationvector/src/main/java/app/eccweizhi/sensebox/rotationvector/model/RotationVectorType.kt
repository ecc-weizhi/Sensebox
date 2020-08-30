package app.eccweizhi.sensebox.rotationvector.model

import android.content.Context
import android.hardware.Sensor
import app.eccweizhi.sensebox.appcore.model.SensorType
import app.eccweizhi.sensebox.rotationvector.R

class RotationVectorType(context: Context) : SensorType(context) {
    override fun getAndroidIdentifier(): Int = Sensor.TYPE_ROTATION_VECTOR

    override fun getIconResId(): Int = R.drawable.rotationvector_main_icon

    override fun getDisplayNameResId(): Int = R.string.rotationvector_display_name
}