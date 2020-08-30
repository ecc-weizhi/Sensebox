package app.eccweizhi.sensebox.relativehumidity.ui

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.eccweizhi.sensebox.appcore.ErrorAdapter
import app.eccweizhi.sensebox.appcore.LinearSpaceItemDecoration
import app.eccweizhi.sensebox.appcore.ext.doOnApplyWindowInsets
import app.eccweizhi.sensebox.appcore.ext.requestApplyInsetsWhenAttached
import app.eccweizhi.sensebox.relativehumidity.R
import kotlinx.android.synthetic.main.relativehumidity_fragment_relative_humidity.*
import timber.log.Timber

class RelativeHumidityFragment : Fragment() {
    private var listener: FragmentListener? = null
    private val adapter = RelativeHumidityAdapter()
    private val errorAdapter = ErrorAdapter(R.string.relativehumidity_display_name)
    private var sensorManager: SensorManager? = null
    private val sensorEventListener: SensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            Timber.d("onAccuracyChanged: %d", accuracy)
        }

        override fun onSensorChanged(event: SensorEvent) {
            adapter.updateRawData(event.values[0])
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.relativehumidity_fragment_relative_humidity,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.doOnApplyWindowInsets { v, windowInsets, initialPadding ->
            // initialPadding contains the original padding values after inflation
            v.setPadding(
                v.paddingLeft,
                initialPadding.top + windowInsets.systemWindowInsetTop,
                v.paddingRight,
                v.paddingBottom
            )
        }
        recyclerView.requestApplyInsetsWhenAttached()

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(
            LinearSpaceItemDecoration(
                requireContext(),
                R.dimen.appcore_margin_normal
            )
        )
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

        sensorManager = ContextCompat.getSystemService(requireContext(), SensorManager::class.java)
        val sensor = sensorManager?.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
        sensor?.let {
            recyclerView.adapter = adapter
            sensorManager?.registerListener(
                sensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        } ?: run {
            // no sensor
            Timber.e("No such sensor")
            recyclerView.adapter = errorAdapter
        }
    }

    override fun onStop() {
        sensorManager?.unregisterListener(sensorEventListener)
        sensorManager = null
        super.onStop()
    }

    companion object {
        @JvmStatic
        fun newInstance(): RelativeHumidityFragment = RelativeHumidityFragment()
    }

    interface FragmentListener {

    }
}