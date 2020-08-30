package app.eccweizhi.sensebox.app.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import app.eccweizhi.sensebox.accelerometer.ui.AccelerometerFragment
import app.eccweizhi.sensebox.ambienttemperature.ui.AmbientTemperatureFragment
import app.eccweizhi.sensebox.app.R
import app.eccweizhi.sensebox.app.ui.about.AboutFragment
import app.eccweizhi.sensebox.app.ui.home.HomeFragment
import app.eccweizhi.sensebox.app.ui.search.SearchBarView
import app.eccweizhi.sensebox.app.ui.settings.SettingsFragment
import app.eccweizhi.sensebox.appcore.ext.doOnApplyWindowInsets
import app.eccweizhi.sensebox.gravity.ui.GravityFragment
import app.eccweizhi.sensebox.gyroscope.ui.GyroscopeFragment
import app.eccweizhi.sensebox.light.ui.LightFragment
import app.eccweizhi.sensebox.linearacceleration.ui.LinearAccelerationFragment
import app.eccweizhi.sensebox.magneticfield.ui.MagneticFieldFragment
import app.eccweizhi.sensebox.orientation.ui.OrientationFragment
import app.eccweizhi.sensebox.pressure.ui.PressureFragment
import app.eccweizhi.sensebox.proximity.ui.ProximityFragment
import app.eccweizhi.sensebox.relativehumidity.ui.RelativeHumidityFragment
import app.eccweizhi.sensebox.rotationvector.ui.RotationVectorFragment
import app.eccweizhi.sensebox.temperature.ui.TemperatureFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity(),
    View.OnClickListener,
    HomeFragment.FragmentListener,
    AboutFragment.FragmentListener,
    SettingsFragment.FragmentListener,
    AccelerometerFragment.FragmentListener,
    AmbientTemperatureFragment.FragmentListener,
    GravityFragment.FragmentListener,
    GyroscopeFragment.FragmentListener,
    LightFragment.FragmentListener,
    LinearAccelerationFragment.FragmentListener,
    MagneticFieldFragment.FragmentListener,
    OrientationFragment.FragmentListener,
    PressureFragment.FragmentListener,
    ProximityFragment.FragmentListener,
    RelativeHumidityFragment.FragmentListener,
    RotationVectorFragment.FragmentListener,
    TemperatureFragment.FragmentListener {

    private lateinit var navigationHome: View
    private lateinit var navigationSettings: View
    private lateinit var navigationAbout: View

    private lateinit var drawerAndSearchCoordinator: DrawerAndSearchCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationHome = findViewById(R.id.homeNavItemView)
        navigationSettings = findViewById(R.id.settingsNavItemView)
        navigationAbout = findViewById(R.id.aboutNavItemView)

        setupNavigationDrawerAndSearchBar()

        findNavController(R.id.navHostFragment).addOnDestinationChangedListener { controller, destination, arguments ->
            drawerAndSearchCoordinator.onNavigate(destination.id)
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerAndSearchCoordinator.syncNavigationIcon()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.homeNavItemView -> {
                findNavController(R.id.navHostFragment).navigate(R.id.action_global_homeFragment)
            }
            R.id.settingsNavItemView -> {
                val extras = FragmentNavigatorExtras(toolbar to "barTransit")

                findNavController(R.id.navHostFragment).navigate(
                    R.id.action_global_settingsFragment,
                    null,
                    null,
                    extras
                )
            }
            R.id.aboutNavItemView -> {
                findNavController(R.id.navHostFragment).navigate(R.id.action_global_aboutFragment)
            }
        }
    }

    override fun onSearchBoxCreate(searchBarView: SearchBarView) {
        drawerAndSearchCoordinator.attachSearchBarView(searchBarView)
    }

    override fun onSearchBoxDestroy() {
        drawerAndSearchCoordinator.detachSearchBarView()
    }

    private fun setupNavigationDrawerAndSearchBar() {
        drawerAndSearchCoordinator = DrawerAndSearchCoordinator(drawerLayout)

        // setup navigation drawer
        navigationDrawer.doOnApplyWindowInsets { v, windowInsets, initialPadding ->
            // initialPadding contains the original padding values after inflation
            v.setPadding(
                v.paddingLeft,
                initialPadding.top + windowInsets.systemWindowInsetTop,
                v.paddingRight,
                v.paddingBottom
            )
        }

        navigationHome.setOnClickListener(this)
        navigationSettings.setOnClickListener(this)
        navigationAbout.setOnClickListener(this)
    }

    companion object {
        private const val SEARCH_TEXT_VIEW = 0
        private const val SEARCH_EDIT_TEXT = 1
    }
}
