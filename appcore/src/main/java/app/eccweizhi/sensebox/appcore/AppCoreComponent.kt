package app.eccweizhi.sensebox.appcore

import dagger.Component

@ApplicationScope
@Component(modules = [AppCoreModule::class])
interface AppCoreComponent {
//    fun repository(): Repository
}