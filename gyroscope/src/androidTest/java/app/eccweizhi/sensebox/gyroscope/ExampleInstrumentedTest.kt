package app.eccweizhi.sensebox.gyroscope

import org.junit.Assert
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext =
            androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("app.eccweizhi.sensebox.gyroscope.test", appContext.packageName)
    }
}
