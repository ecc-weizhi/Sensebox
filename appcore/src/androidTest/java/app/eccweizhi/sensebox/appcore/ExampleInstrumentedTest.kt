package app.eccweizhi.sensebox.appcore

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("app.eccweizhi.sensebox.appcore.test", appContext.packageName)
    }
}
