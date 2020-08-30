package app.eccweizhi.sensebox.app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.eccweizhi.sensebox.app.R
import app.eccweizhi.sensebox.appcore.model.SensorType

class SensorAdapter(private val listener: Listener) : RecyclerView.Adapter<SensorTileVh>(),
    Filterable {
    private val originalSensorList: ArrayList<SensorType> = arrayListOf()
    private var filteredSensorList: List<SensorType> = listOf()
    private val viewHolderListener: SensorTileVh.Listener = object : SensorTileVh.Listener {
        override fun onItemClick(adapterPosition: Int) {
            listener.onItemSelected(filteredSensorList[adapterPosition])
        }
    }

    override fun getFilter(): Filter {
        return SensorSearchFilter()
    }

    fun updateData(newSensorList: List<SensorType>) {
        originalSensorList.clear()
        originalSensorList.addAll(newSensorList)

        val oldList = ArrayList(filteredSensorList)
        filteredSensorList = ArrayList(originalSensorList)

        val diffResult =
            DiffUtil.calculateDiff(SensorDiffUtilCallback(oldList, filteredSensorList), false)
        diffResult.dispatchUpdatesTo(this@SensorAdapter)
    }

    fun resetFilter() {
        val oldList = ArrayList(filteredSensorList)
        filteredSensorList = ArrayList(originalSensorList)

        val diffResult =
            DiffUtil.calculateDiff(SensorDiffUtilCallback(oldList, filteredSensorList), false)
        diffResult.dispatchUpdatesTo(this@SensorAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorTileVh {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_sensor_tile, parent, false)
        return SensorTileVh(itemView, viewHolderListener)
    }

    override fun getItemCount(): Int {
        return filteredSensorList.size
    }

    override fun onBindViewHolder(holder: SensorTileVh, position: Int) {
        val item = filteredSensorList[position]
        holder.bind(item)
    }

    private inner class SensorSearchFilter : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val result = FilterResults()

            val filteredList = if (constraint == null) {
                originalSensorList
            } else {
                originalSensorList.filter { it.getDisplayName().contains(constraint, true) }
            }

            result.values = filteredList
            result.count = filteredList.size

            return result
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            val oldList = ArrayList(filteredSensorList)
            filteredSensorList = results.values as List<SensorType>

            val diffResult =
                DiffUtil.calculateDiff(SensorDiffUtilCallback(oldList, filteredSensorList), false)
            diffResult.dispatchUpdatesTo(this@SensorAdapter)
        }
    }

    interface Listener {
        fun onItemSelected(sensorType: SensorType)
    }
}