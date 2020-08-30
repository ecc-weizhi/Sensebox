package app.eccweizhi.sensebox.appcore.ext

import android.content.Context
import app.eccweizhi.sensebox.appcore.AppCoreComponent
import app.eccweizhi.sensebox.appcore.AppCoreComponentProvider

fun Context.provideAppCoreComponent(): AppCoreComponent {
    val appContext = applicationContext
    return if (appContext is AppCoreComponentProvider) {
        (appContext as AppCoreComponentProvider).provideAppCoreComponent()
    } else {
        throw IllegalStateException("$appContext must implement AppCoreComponentProvider")
    }
}