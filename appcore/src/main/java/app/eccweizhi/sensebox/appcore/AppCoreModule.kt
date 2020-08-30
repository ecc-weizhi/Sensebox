package app.eccweizhi.sensebox.appcore

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppCoreModule(context: Context) {
    private val applicationContext = context.applicationContext

    @ApplicationScope
    @Provides
    fun providesContext(): Context {
        return applicationContext
    }

//    @ApplicationScope
//    @Provides
//    fun providesRepository(): Repository {
//        return RepositoryImpl(applicationContext)
//    }
}