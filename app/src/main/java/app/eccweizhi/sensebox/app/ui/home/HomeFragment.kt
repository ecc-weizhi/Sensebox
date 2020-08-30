package app.eccweizhi.sensebox.app.ui.home

import android.content.Context
import android.hardware.Sensor
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import app.eccweizhi.sensebox.accelerometer.model.AccelerometerType
import app.eccweizhi.sensebox.ambienttemperature.model.AmbientTemperatureType
import app.eccweizhi.sensebox.app.R
import app.eccweizhi.sensebox.app.ui.search.SearchBarView
import app.eccweizhi.sensebox.appcore.SimpleTextWatcher
import app.eccweizhi.sensebox.appcore.ext.doOnApplyWindowInsets
import app.eccweizhi.sensebox.appcore.ext.requestApplyInsetsWhenAttached
import app.eccweizhi.sensebox.appcore.model.SensorType
import app.eccweizhi.sensebox.gravity.model.GravityType
import app.eccweizhi.sensebox.gyroscope.model.GyroscopeType
import app.eccweizhi.sensebox.light.model.LightType
import app.eccweizhi.sensebox.linearacceleration.model.LinearAccelerationType
import app.eccweizhi.sensebox.magneticfield.model.MagneticFieldType
import app.eccweizhi.sensebox.orientation.model.OrientationType
import app.eccweizhi.sensebox.pressure.model.PressureType
import app.eccweizhi.sensebox.proximity.model.ProximityType
import app.eccweizhi.sensebox.relativehumidity.model.RelativeHumidityType
import app.eccweizhi.sensebox.rotationvector.model.RotationVectorType
import app.eccweizhi.sensebox.temperature.model.TemperatureType
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private var listener: FragmentListener? = null
    private var adapterListener: SensorAdapter.Listener = object : SensorAdapter.Listener {
        override fun onItemSelected(sensorType: SensorType) {
            when (sensorType.getAndroidIdentifier()) {
                Sensor.TYPE_ACCELEROMETER -> {
                    findNavController().navigate(R.id.action_homeFragment_to_accelerometerFragment)
                }
                Sensor.TYPE_AMBIENT_TEMPERATURE -> {
                    findNavController().navigate(R.id.action_homeFragment_to_ambientTemperatureFragment)
                }
                Sensor.TYPE_GRAVITY -> {
                    findNavController().navigate(R.id.action_homeFragment_to_gravityFragment)
                }
                Sensor.TYPE_GYROSCOPE -> {
                    findNavController().navigate(R.id.action_homeFragment_to_gyroscopeFragment)
                }
                Sensor.TYPE_LIGHT -> {
                    findNavController().navigate(R.id.action_homeFragment_to_lightFragment)
                }
                Sensor.TYPE_LINEAR_ACCELERATION -> {
                    findNavController().navigate(R.id.action_homeFragment_to_linearAccelerationFragment)
                }
                Sensor.TYPE_MAGNETIC_FIELD -> {
                    findNavController().navigate(R.id.action_homeFragment_to_magneticFieldFragment)
                }
                Sensor.TYPE_ORIENTATION -> {
                    findNavController().navigate(R.id.action_homeFragment_to_orientationFragment)
                }
                Sensor.TYPE_PRESSURE -> {
                    findNavController().navigate(R.id.action_homeFragment_to_pressureFragment)
                }
                Sensor.TYPE_PROXIMITY -> {
                    findNavController().navigate(R.id.action_homeFragment_to_proximityFragment)
                }
                Sensor.TYPE_RELATIVE_HUMIDITY -> {
                    findNavController().navigate(R.id.action_homeFragment_to_relativeHumidityFragment)
                }
                Sensor.TYPE_ROTATION_VECTOR -> {
                    findNavController().navigate(R.id.action_homeFragment_to_rotationVectorFragment)
                }
                Sensor.TYPE_TEMPERATURE -> {
                    findNavController().navigate(R.id.action_homeFragment_to_temperatureFragment)
                }
            }
        }
    }
    private var adapter = SensorAdapter(adapterListener)
    private val searchTextWatcher = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable) {
            onSearchInput(s.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // setup search bar
        toolbar.doOnApplyWindowInsets { v, windowInsets, initialPadding ->
            // initialPadding contains the original padding values after inflation
            v.setPadding(
                v.paddingLeft,
                initialPadding.top + windowInsets.systemWindowInsetTop,
                v.paddingRight,
                v.paddingBottom
            )
        }
        listener?.onSearchBoxCreate(toolbar)

        sensorListRecyclerView.doOnApplyWindowInsets { v, windowInsets, initialPadding ->
            // initialPadding contains the original padding values after inflation
            v.setPadding(
                v.paddingLeft,
                initialPadding.top + windowInsets.systemWindowInsetTop,
                v.paddingRight,
                v.paddingBottom
            )
        }
        sensorListRecyclerView.requestApplyInsetsWhenAttached()

        sensorListRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        sensorListRecyclerView.addItemDecoration(
            SensorTileItemDecoration(
                requireContext(),
                R.dimen.appcore_margin_smaller
            )
        )
        sensorListRecyclerView.adapter = adapter

        adapter.updateData(
            listOf(
                AccelerometerType(requireContext()),
                AmbientTemperatureType(requireContext()),
                GravityType(requireContext()),
                GyroscopeType(requireContext()),
                LightType(requireContext()),
                LinearAccelerationType(requireContext()),
                MagneticFieldType(requireContext()),
                OrientationType(requireContext()),
                PressureType(requireContext()),
                ProximityType(requireContext()),
                RelativeHumidityType(requireContext()),
                RotationVectorType(requireContext()),
                TemperatureType(requireContext())
            )
        )
    }

    override fun onDestroyView() {
        listener?.onSearchBoxDestroy()
        super.onDestroyView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as FragmentListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement FragmentListener")
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    override fun onStart() {
        super.onStart()
        toolbar.addTextChangedListener(searchTextWatcher)
    }

    override fun onStop() {
        toolbar.removeTextChangedListener(searchTextWatcher)
        super.onStop()
    }

    private fun onSearchInput(searchTerm: String?) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            adapter.resetFilter()
        } else {
            adapter.filter.filter(searchTerm)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): HomeFragment = HomeFragment()
    }

    interface FragmentListener {
        fun onSearchBoxCreate(searchBarView: SearchBarView)
        fun onSearchBoxDestroy()
    }
}