package app.eccweizhi.sensebox.appcore.model

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

abstract class SensorType(context: Context) {
    private val applicationContext = context.applicationContext

    abstract fun getAndroidIdentifier(): Int

    @DrawableRes
    abstract fun getIconResId(): Int

    @StringRes
    abstract fun getDisplayNameResId(): Int

    fun getDisplayName(): String {
        return applicationContext.getString(getDisplayNameResId())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SensorType

        if (getAndroidIdentifier() != other.getAndroidIdentifier()) return false

        return true
    }

    override fun hashCode(): Int {
        return getAndroidIdentifier()
    }
}